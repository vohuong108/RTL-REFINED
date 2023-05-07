package org.uet.dse.rtlplus.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.StringWriter;
import java.net.URL;
import java.util.Collection;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import org.tzi.use.gui.main.MainWindow;
import org.tzi.use.gui.main.ViewFrame;
import org.uet.dse.rtlplus.diagram.tggdiagram.TggDiagramView;
import org.uet.dse.rtlplus.mm.MRuleCollection;
import org.uet.dse.rtlplus.mm.MTggRule;

public class RTLRuleTree extends JPanel implements TreeSelectionListener {
	private static final long serialVersionUID = -6594017690147608266L;
	private JEditorPane htmlPane;
	private JTree tree;
	private Collection<MTggRule> fTggRules;
	private MainWindow mainWindow;

	// Optionally play with line styles. Possible values are
	// "Angled" (the default), "Horizontal", and "None".
	private static boolean playWithLineStyle = false;
	private static String lineStyle = "Horizontal";

	public RTLRuleTree(MRuleCollection tggRuleCollection, MainWindow mainWindow) {
		super(new GridLayout(1, 0));
		this.fTggRules = tggRuleCollection.getRuleList();
		this.mainWindow = mainWindow;

		// Create the nodes.
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Triple Rules");
		createNodes(top);

		// Create a tree that allows one selection at a time.
		tree = new JTree(top);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

		// Listen for when the selection changes.
		tree.addTreeSelectionListener(this);

		if (playWithLineStyle) {
			System.out.println("line style = " + lineStyle);
			tree.putClientProperty("JTree.lineStyle", lineStyle);
		}

		// Create the scroll pane and add the tree to it.
		JScrollPane treeView = new JScrollPane(tree);

		// Create the HTML viewing pane.
		htmlPane = new JEditorPane();
		htmlPane.setEditable(false);
		htmlPane.setContentType("text/html");
		JScrollPane htmlView = new JScrollPane(htmlPane);

		// Add the scroll panes to a split pane.
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane.setTopComponent(treeView);
		splitPane.setBottomComponent(htmlView);

		Dimension minimumSize = new Dimension(100, 100);
		htmlView.setMinimumSize(minimumSize);
		treeView.setMinimumSize(minimumSize);
		splitPane.setDividerLocation(200);
		splitPane.setPreferredSize(new Dimension(300, 500));

		// Add the split pane to this panel.
		add(splitPane);
	}

	/** Required by TreeSelectionListener interface. */
	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

		if (node == null)
			return;

		Object nodeInfo = node.getUserObject();
		if (node.isLeaf()) {
			MTggRule tggRule = (MTggRule) nodeInfo;
			String specification = readHTMLFile(tggRule);
			htmlPane.setText(specification);
			
			TggDiagramView tggView = new TggDiagramView(mainWindow, tggRule);
			tggView.setVisible(true);
			ViewFrame f = new ViewFrame("Triple rule [" + tggRule.getName() + "]", tggView, "rtl.png");
			URL url = getClass().getResource("/resources/rtl.png");
			ImageIcon icon = new ImageIcon(url);
			f.setFrameIcon(icon);
			

			JComponent c = (JComponent) f.getContentPane();
			c.setLayout(new BorderLayout());
			c.add(new JScrollPane(tggView, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
			mainWindow.addNewViewFrame(f);
			
		}
	}

	private void createNodes(DefaultMutableTreeNode top) {
		DefaultMutableTreeNode rule = null;
		for (MTggRule tggRule : fTggRules) {
			rule = new DefaultMutableTreeNode(tggRule);
			top.add(rule);
		}
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be invoked
	 * from the event dispatch thread.
	 */
	public static JFrame createAndShowGUI(MRuleCollection tggRules, MainWindow mainWindow) {
		// Create and set up the window.
		JFrame frame = new JFrame("RTL rules");
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		// Add content to the window.
		frame.add(new RTLRuleTree(tggRules, mainWindow));

		// Display the window.
		frame.pack();
		frame.setVisible(true);

		return frame;
	}

	private String readHTMLFile(MTggRule tggRule) {
		StringWriter sw = new StringWriter();
		sw.write("<html><head>");
		sw.write("<style> <!-- body { font-family: monospace; } --> </style>");
		sw.write("</head><body><font size=\"-1\">");
		String html = tggRule.getHtml().replace(">", "&gt;").replace("<", "&lt;")
				.replace("rule", "<strong>rule</strong>")
				.replace("checkSource", "<strong>checkSource</strong>")
				.replace("checkTarget", "<strong>checkTarget</strong>")
				.replace("checkCorr", "<strong>checkCorr</strong>")
				.replace(" ", "&nbsp;").replace("\n", "<br>")
				.replace(" ", "&nbsp;");
		sw.write(html);
		sw.write("</body></html>");
		String spec = sw.toString();
		return spec;
	}
}