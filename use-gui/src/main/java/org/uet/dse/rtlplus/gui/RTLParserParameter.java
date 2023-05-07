package org.uet.dse.rtlplus.gui;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Paths;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.tzi.use.config.Options;
import org.tzi.use.gui.main.MainWindow;
import org.tzi.use.gui.util.CloseOnEscapeKeyListener;
import org.tzi.use.gui.util.ExtFileFilter;
import org.tzi.use.gui.util.GridBagHelper;
import org.tzi.use.main.ChangeEvent;
import org.tzi.use.main.ChangeListener;
import org.tzi.use.main.Session;
import org.uet.dse.rtlplus.RTLLoader;

/**
 * RTL1.1
 * 
 * @author Khoa-Hai Nguyen
 * 
 */
@SuppressWarnings("serial")
public class RTLParserParameter extends JDialog {
	private Session fSession;
	private MainWindow mainWindow;
	private JTextField fTextModel2;
	private JTextField fTextTgg;
	private PrintWriter logWriter;
	

	public RTLParserParameter(Session session, MainWindow parent) {
		super(parent, "RTL Parser Parameter");
		mainWindow = parent;
		fSession = session;
		fSession.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				closeDialog();
			}
		});

		logWriter = parent.logWriter();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// Label for source metamodel, which is already loaded
		JLabel labelModel1 = new JLabel("Source metamodel: " + Options.getRecentFile("use").getFileName());

		// Label and text field for target metamodel
		fTextModel2 = new JTextField(35);
		JLabel labelModel2 = new JLabel("Target metamodel:");
		labelModel2.setLabelFor(fTextModel2);

		// Label and text field for TGG rules
		fTextTgg = new JTextField(35);
		JLabel labelTgg = new JLabel("TGG rules:");
		labelTgg.setLabelFor(fTextTgg);

		JButton btnPath2 = new JButton("Browse...");
		btnPath2.addActionListener(new ActionListener() {
			private JFileChooser fChooser;

			public void actionPerformed(ActionEvent e) {
				String path;
				if (fChooser == null) {
					path = Options.getLastDirectory().toString();
					fChooser = new JFileChooser(path);
					ExtFileFilter filter = new ExtFileFilter("use", "USE specifications");
					fChooser.setFileFilter(filter);
					fChooser.setDialogTitle("Open specification");
				}
				int returnVal = fChooser.showOpenDialog(RTLParserParameter.this);
				if (returnVal != JFileChooser.APPROVE_OPTION)
					return;

				path = fChooser.getCurrentDirectory().toString();
				Options.setLastDirectory(new File(path).toPath());

				fTextModel2.setText(Paths.get(path, fChooser.getSelectedFile().getName()).toString());

			}
		});
		JButton btnPath3 = new JButton("Browse...");
		btnPath3.addActionListener(new ActionListener() {
			private JFileChooser fChooser;

			public void actionPerformed(ActionEvent e) {
				String path;
				if (fChooser == null) {
					path = Options.getLastDirectory().toString();
					fChooser = new JFileChooser(path);
					ExtFileFilter filter = new ExtFileFilter("tgg", "TGG rules");
					fChooser.setFileFilter(filter);
					fChooser.setDialogTitle("Open TGG file");
				}
				int returnVal = fChooser.showOpenDialog(RTLParserParameter.this);
				if (returnVal != JFileChooser.APPROVE_OPTION)
					return;

				path = fChooser.getCurrentDirectory().toString();
				Options.setLastDirectory(new File(path).toPath());

				fTextTgg.setText(Paths.get(path, fChooser.getSelectedFile().getName()).toString());

			}
		});

		JButton btnParse = new JButton("Parse");
		btnParse.setMnemonic('P');
		btnParse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parseRTL();
			}
		});
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeDialog();
			}
		});

		// layout content pane
		JComponent contentPane = (JComponent) getContentPane();
		contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		GridBagHelper gh = new GridBagHelper(contentPane);
		gh.add(labelModel1, 0, 0, 6, 1, 0.0, 0.0, GridBagConstraints.HORIZONTAL);
		gh.add(labelModel2, 0, 1, 6, 1, 0.0, 0.0, GridBagConstraints.HORIZONTAL);
		gh.add(fTextModel2, 0, 2, 6, 1, 0.0, 0.0, GridBagConstraints.HORIZONTAL);
		gh.add(btnPath2, 7, 2, 1, 1, 0.0, 0.0, GridBagConstraints.HORIZONTAL);
		gh.add(labelTgg, 0, 3, 6, 1, 0.0, 0.0, GridBagConstraints.HORIZONTAL);
		gh.add(fTextTgg, 0, 4, 6, 1, 0.0, 0.0, GridBagConstraints.HORIZONTAL);
		gh.add(btnPath3, 7, 4, 1, 1, 0.0, 0.0, GridBagConstraints.HORIZONTAL);
		gh.add(btnParse, 0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.HORIZONTAL);
		gh.add(btnClose, 1, 6, 1, 1, 0.0, 0.0, GridBagConstraints.HORIZONTAL);

		getRootPane().setDefaultButton(btnParse);
		pack();
		setLocationRelativeTo(parent);

		// Close dialog on escape key
		CloseOnEscapeKeyListener ekl = new CloseOnEscapeKeyListener(this);
		addKeyListener(ekl);
	}

	private void closeDialog() {
		setVisible(false);
		dispose();
	}

	private void parseRTL() {
		if (checkPath()) {
			RTLLoader loader = new RTLLoader(fSession, Options.getRecentFile("use").toFile(), new File(fTextModel2.getText()), fTextTgg.getText(), logWriter);
			loader.run();
		} else {
			JOptionPane.showMessageDialog(mainWindow, "The target model or transformation rules file does not exist. Please try again.", "File error", JOptionPane.ERROR_MESSAGE);;
		}
	}

	
	/**
	 * Check if paths are valid
	 */
	private boolean checkPath() {
		File f = new File(fTextModel2.getText());
		if (f.exists()) {
			f = new File(fTextTgg.getText());
			if (f.exists())
				return true;
		}
		return false;
	}
}
