package application.controller;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 * This class is a background thread for displaying warning message for a period of time and then disable the message
 */
public class TimedMessage extends Service<Void>{

	private static final int MESSAGE_DISPLAY_TIME = 1000;

	@Override
	protected Task<Void> createTask() {
		return new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				//delay the disable warning message from a period of time 
				Thread.sleep(MESSAGE_DISPLAY_TIME);
				return null;
			}
		};
	}
}

