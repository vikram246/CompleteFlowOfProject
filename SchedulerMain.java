package simplescheduler;

import java.util.Timer;

public class SchedulerMain 
{
	public static void main(String args[]) throws InterruptedException 
	{

		Timer time = new Timer(); // Instantiate Timer Object
		ScheduledTask st = new ScheduledTask(); // Instantiate ScheduledTask class
		time.schedule(st, 0, 10000); // Create Repetitively task for every 2 min's

		//for demo only.
		for (int i = 0; i <= 5; i++) 
		{
			System.out.println("Execution in Main Thread...." + i);
			Thread.sleep(10000);
			if (i == 5) 
			{
				System.out.println("Application Terminates");
				System.exit(0);
			}
		}
	}
}
