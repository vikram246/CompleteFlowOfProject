package demo;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ScheduledTask extends TimerTask {
	Date now; // to display current time
	AtributeMapper MQuery; // Object created for class Multiple Query

	// Add your task here
	public void run() {
		MQuery = new AtributeMapper(); // Created a object of Multiple Query class so
		// that it can call method present inside Multiple Query
		AtributeMapper.run(); // Method Query has been called using MQuery object
		now = new Date(); // initialize date
		System.out.println("Time is :" + now);// Display current time

	}
}
