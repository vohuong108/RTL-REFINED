/*
 * USE - UML based specification environment
 * Copyright (C) 1999-2010 Mark Richters, University of Bremen
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

// $Id$

package org.tzi.use.gui.views.diagrams.behavior.sequencediagram;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import org.tzi.use.gui.views.diagrams.statemachine.StateNode;
import org.tzi.use.uml.mm.statemachines.MProtocolStateMachine;
import org.tzi.use.uml.mm.statemachines.MRegion;
import org.tzi.use.uml.mm.statemachines.MState;
import org.tzi.use.uml.mm.statemachines.MTransition;
import org.tzi.use.uml.mm.statemachines.MVertex;
import org.tzi.use.uml.sys.MObject;
import org.tzi.use.uml.sys.events.ObjectCreatedEvent;
import org.tzi.use.uml.sys.events.ObjectDestroyedEvent;
import org.tzi.use.uml.sys.events.OperationEnteredEvent;

/**
 * Represents a lifeline for Objects.
 * 
 * @author Antje Werner
 * @author Carsten Schlobohm
 */
public class ObjLifeline extends Lifeline {
	/**
	 * The object of this lifeline.
	 */
	private MObject fObj;

	/**
	 * Costructs a new ObjLifeline-Object.
	 * 
	 * @param col the column of the lifeline
	 * @param obj the object of the lifeline
	 * @param antecessor the antecessor lifeline
	 */
	public ObjLifeline(int col, MObject obj, Lifeline antecessor, SequenceDiagram sequenceDiagram) {
		super(sequenceDiagram);
		columnNumber = col;
		fObj = obj;

		// State Machine
		sortedPSMs = new LinkedList<MProtocolStateMachine>(fObj.cls().getOwnedProtocolStateMachines());

		if (sortedPSMs.size() > 0) {
			hasStatesMachine = true;
			// Support one state machine per class
			MRegion reg = sortedPSMs.get(0).getRegions().get(0);
			initState = reg.getInitialState();
		}

		activationsList = new ArrayList<Activation>();
		objectBox = new ObjectBox(-1, -1, fObj.name() + ":" + fObj.cls().name());

		// calculate x-value subject to the column of the lifeline
		xPos = getSequenceDiagramProperties().getLeftMargin() + columnNumber * getSequenceDiagramProperties().llStep();
		this.antecessor = antecessor;
		// normally a lifeline should not be drawn
		fDraw = false;
		// normally a lifeline should not be hidden
		isHidden = false;
	}

	/**
	 * Restores some values to be sure, that all values will be calculated
	 * correctly, when update is called.
	 */
	public synchronized void restoreValues() {
		objectBox = new ObjectBox(-1, -1, fObj.name() + ":" + fObj.cls().name());
		// deletes all activations for this lifeline
		activationsList = new ArrayList<Activation>();
		activationNesting = 0;
		// the lifeline should not be drawn
		fDraw = false;
		maxOfMessLength = 0;
	}

	public MObject getObject() {
		return fObj;
	}

	@Override
	protected void drawLifeline(Graphics2D g, FontMetrics fm) {
		// the values for the start- and end-point and the height of the
		// area on which the diagram should be drawn -> depends on the value
		// of fOnlyView
		int SB_yValue;
		if (sequenceDiagram.isOnlyView()) {
			// set values of the visible view
			SB_yValue = (int) sequenceDiagram.getView().getY();
		} else {
			// diagram starts and ends at 0
			SB_yValue = 0;
		}

		// y-position where lifeline starts
		int y = fProperties.yScroll() - 20;
		// positions where objectbox starts and lifeline ends
		int y_start = 0, y_end = -1;
		
		boolean isInitStateShown = false;

		// List of activation frames for this lifeline
		fFrames = new ArrayList<Frame>();
		stateNodesList = new ArrayList<StateNode>();

		// regard each actiovation of the lifeline
		Iterator<Activation> activationIter = activationsList.iterator();

		synchronized (this) {
			while (activationIter.hasNext()) {
				y_start = objectBox.getYPosOfBoxStart();
				Activation a = activationIter.next();
				// y-value of the activation message
				y = a.getYOfActivationMessArrow();

				// if insert- or create-command-> calculate correct position
				// for the object box
				if (a.getEvent() instanceof ObjectCreatedEvent) {
					// if object box was not scrolled to the top so far
					if (y - objectBox.getHeight() / 2 > y_start)
						y_start = y + objectBox.getHeight() / 2;

					if (hasStatesMachine && isShowStates()) {
						StateNode sn = new StateNode(initState);
						int yPosStateNode;
						if (fProperties.getActManDist() == -1) {
							yPosStateNode = y + (fProperties.actStep() + fProperties.getStateNodeExtension() - objectBox.getHeight() / 2) / 2;
						} else {
							yPosStateNode = y + (fProperties.getActManDist() + fProperties.getStateNodeExtension() - objectBox.getHeight() / 2) / 2;
						}
						setupState(sn, yPosStateNode, g.getFont());
						isInitStateShown = true;
						stateNodesList.add(sn);
					}
				}

				// if position of object box is on top of the front of the
				// diagram
				if (y_start + objectBox.getHeight() < fProperties.yScroll() + SB_yValue) {
					// set y-Start at the front of the diagram
					y_start = SB_yValue + fProperties.yScroll() - objectBox.getHeight();
				}
				// is delete- or destroy-command
				if (a.getEvent() instanceof ObjectDestroyedEvent) {
					// lifeline ends at the position of the corresponding
					// message
					y_end = y;
					// otherwise if not create-command
				} else if (!(a.getEvent() instanceof ObjectCreatedEvent)) {
					// position of return arrow of the current activation
					int fEnd = a.getEnd();
					// if frame still active
					if (fEnd == 0)
						fEnd = sequenceDiagram.getNumSteps();

					// calculate position subject to the user settings for
					// the message distances
					if (fProperties.getActManDist() == -1) {
						fEnd = (fEnd - a.getStart()) * fProperties.actStep();
					} else {
						fEnd = (fEnd - a.getStart()) * fProperties.getActManDist();
					}

					// in case of openter-command -> draw only frame if
					// operation call was successful
					if (!(a.getEvent() instanceof OperationEnteredEvent && !((OperationEnteredEvent) a.getEvent()).getOperationCall().enteredSuccessfully())) {
						fFrames.add(new Frame(xPos, y, fEnd, a.getNesting()));

						if (hasStatesMachine && isShowStates()) {
							
							if (!isInitStateShown) {
								StateNode sn = new StateNode(initState);
								int yPosStateNode = y - fProperties.getStateNodeExtension();
								setupState(sn, yPosStateNode, g.getFont());
								isInitStateShown = true;
								stateNodesList.add(sn);
							}
							
							
							if (a.getEvent() instanceof OperationEnteredEvent
									&& ((OperationEnteredEvent) a.getEvent()).getOperationCall().hasExecutedTransitions()
									&& ((OperationEnteredEvent) a.getEvent()).getOperationCall().enteredSuccessfully()) {

								Collection<Set<MTransition>> transitions = ((OperationEnteredEvent) a.getEvent()).getOperationCall().getExecutedTransitions()
										.values();

								MVertex newState = null;

								for (Set<MTransition> transitionSet : transitions) {
									for (MTransition trans : transitionSet) {
										if (trans.getSource().name().equals(currentStateName)) {
											newState = trans.getTarget();
											currentStateName = newState.name();
											break;
										}
									}
								}

								if (newState != null) {
									StateNode sn = new StateNode(new MState(newState.toString()));
									int yPosStateNode;
									if (fProperties.getActManDist() == -1) {
										yPosStateNode = y + fEnd + fProperties.actStep();
									} else {
										yPosStateNode = y + fEnd + fProperties.getActManDist();
									}
									setupState(sn, yPosStateNode, g.getFont());
									stateNodesList.add(sn);
								}
							}
						}
					}
				}
			}
		}// end of while

		// if only inserted as-Message is send to this Lifeline -> position
		// the object box on the front of the diagram
		if (y_start == 0)
			y_start = SB_yValue + fProperties.yScroll() - objectBox.getHeight();
		// if only visible view should be drawn and the user has scrolled in
		// vertical direction
		if (sequenceDiagram.isOnlyView() && sequenceDiagram.getView().getY() > 0) {
			drawZigZag(g);
		}

		// if there was no destroy- or delete- command for this lifeline
		if (y_end == -1) {
			y_end = sequenceDiagram.getLastYPos() + fProperties.yStart();
		}
		
		yEndPos = y_end;

		if (sequenceDiagram.getChoosedLifelines().isSelected(this)) {
			drawSelectedBound(g, y_start, y_end);
		}

		// draw objectBox in the right position (->y_start)
		objectBox.drawBox(g, fm, y_start, sequenceDiagram.getChoosedLifelines().isSelected(this));

		// if only the visible view should be drawn
		if (sequenceDiagram.isOnlyView()) {
			// set drawing area below the zigzag pattern
			g.clipRect((int) sequenceDiagram.getView().getX(),
					(int) sequenceDiagram.getView().getY() + fProperties.yScroll() + sequenceDiagram.getScrollCounter() * 4, (int) sequenceDiagram.getView()
							.getWidth(), (int) sequenceDiagram.getView().getHeight() - fProperties.yScroll() - sequenceDiagram.getScrollCounter() * 4);
		}

		// draw dashedLine in the right position and with the right height
		drawDashedLine(xPos, y_start + objectBox.getHeight(), y_end, g);

		// draw frames for this lifeline
		drawFrames(g);

		// regard each activation of this lifeline
		activationIter = activationsList.iterator();
		synchronized (this) {
			while (activationIter.hasNext()) {
				Activation a = activationIter.next();
				y = a.getYOfActivationMessArrow();
				// if Object destroyed or Link deleted -> draw cross
				if (a.getEvent() instanceof ObjectDestroyedEvent) {
					y_end = y;
					drawDestroySaltire(g, y);
				}
			}
		}

		if (fProperties.isStatesShown() && hasStatesMachine && isShowStates()) {
			// draw states for this lifeline
			drawStateNodes(g);
		}
	}

	@Override
	boolean isHidden() {
		if (getSequenceDiagram().getVisibleDataManager().getData().isObjectKnown(fObj)) {
			return !getSequenceDiagram().getVisibleDataManager().getData().isObjectVisible(fObj);
		}
		return super.isHidden();
	}

	@Override
	void setHidden(boolean hidden) {
		if (getSequenceDiagram().getVisibleDataManager().getData().isObjectKnown(fObj)) {
			getSequenceDiagram().getVisibleDataManager().getData().changeObjectVisibility(fObj, !hidden);
		} else {
			super.setHidden(hidden);
		}
	}
}
