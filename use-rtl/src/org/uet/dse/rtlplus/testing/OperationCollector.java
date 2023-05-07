package org.uet.dse.rtlplus.testing;

import java.util.ArrayList;
import java.util.List;

import org.uet.dse.rtlplus.sync.OperationEnterEvent;
import org.uet.dse.rtlplus.sync.OperationExitEvent;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class OperationCollector {
	private List<OperationEnterEvent> eventList = new ArrayList<>();
	private OperationEnterEvent currentEvent;
	public void subscribe(EventBus eventBus) {
		eventBus.register(this);
	}
	
	@Subscribe
	public void onOperationEnterEvent(OperationEnterEvent e) {
		currentEvent = e;
	}
	
	@Subscribe 
	public void onOperationExitEvent(OperationExitEvent e) {
		if (e.isSuccess())
			eventList.add(currentEvent);
	}
	
	public void unsubscribe(EventBus eventBus) {
		eventBus.unregister(this);
	}

	public List<OperationEnterEvent> getEventList() {
		return eventList;
	}
}
