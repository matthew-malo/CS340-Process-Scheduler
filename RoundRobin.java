/***
 * A class designed to serve as the round robin queue that will be implemented by the 
 * Multilevel Feedback Queue Scheduler, as well as the Process Scheduler class
 * @author M.Malo
 *
 */
public class RoundRobin extends Scheduler{
	
	//global variables to assist in process management
	private static int quantumTime = 4;
	private static int numProcesses;
	private static int arrivalTime;
	private static int burstTime;
	
	public RoundRobin() {
		super();
	}//constructor

	//method to add process to ready queue
	public void addProcessToReadyQueue(Process p) {
		readyQueue.enqueue(p);		
	}

	//method to remove process from ready queue
	public Process removeProcessFromReadyQueue() {
		if(readyQueue.isEmpty() == false)
			readyQueue.dequeue();
		return null;
	}

	//getter method for arrival time
	public static int getArrivalTime() {
		return RoundRobin.arrivalTime;
	}

	//setter method for arrival time
	public static void setArrivalTime(int i) {
		RoundRobin.arrivalTime = i;
	}

	//getter method for number of processes
	public static int getNumProcesses() {
		return RoundRobin.numProcesses;
	}

	//setter method for number of processes
	public static void setNumProcesses(int i) {
		RoundRobin.numProcesses = i;
	}

	//getter method for quantum time of round robin queue
	public static int getQuantumTime() {
		return quantumTime;
	}

	//setter method for quantum time of round robin queue
	public static void setQuantumTime(int i) {
		RoundRobin.quantumTime = i;
	}

	//setter method for burst time
	public static void setBurstTime(int i) {
		RoundRobin.burstTime = i;
	}
	
	//getter method for burst time
	public static int getBurstTime() {
		return RoundRobin.burstTime;
	}

}//class RoundRobin
