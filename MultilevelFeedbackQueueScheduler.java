/***
 * Class designed to serve as the the Multilevel Feedback Queue
 *  Scheduler, which consists of three different queues and will be
 *  implemented by the ProcessScheduler class.
 */
import java.io.InputStream;
import java.io.OutputStream;

public class MultilevelFeedbackQueueScheduler extends Process{
	
	//Three queues to be used by the MFQS
	static FirstComeFirstServe FCFS;
	static RoundRobin RR1;
	static RoundRobin RR2;

	public MultilevelFeedbackQueueScheduler(){
		FCFS = new FirstComeFirstServe();
		RR1 = new RoundRobin();
		RR2 = new RoundRobin();
		}//constructor


}//class MultilevelFeedbackQueueScheduler
