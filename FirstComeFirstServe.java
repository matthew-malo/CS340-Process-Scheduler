/***
 * A class designed to serve as the first come first serve queue that will be 
 * implemented by the Multilevel Feedback Queue Scheduler, as well as the 
 * Process Scheduler class
 * @author M.Malo
 *
 */
public class FirstComeFirstServe extends Scheduler{


	public FirstComeFirstServe() {
		super();
	}//constructor
	
	//Method to add a process to the ready queue
	public void addProcessToReadyQueue(Process p) {
			readyQueue.enqueue(p);
	}

	//Method to remove a process from the ready queue
	public Process removeProcessFromReadyQueue() {
		if (readyQueue.isEmpty() == false)
			readyQueue.dequeue();
		return null;
	}

}
