package org.uet.dse.rtlplus.sync;

public class OperationExitEvent {
	private String opName;
	private boolean success;
	
	public OperationExitEvent(String opName, boolean success) {
		super();
		this.opName = opName;
		this.success = success;
	}
	
	public String getOpName() {
		return opName;
	}
	public boolean isSuccess() {
		return success;
	}
}
