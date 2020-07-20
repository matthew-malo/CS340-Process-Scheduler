/***
 * A class designed to open a text file, read the file's inputs and convert it to a string,
 * then convert the strings into 'processes' that the system can execute via a multilevel 
 * feedback queue while showing system time
 * @author M.Malo
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcessScheduler extends MultilevelFeedbackQueueScheduler{

	private static int systemTime = 0;
	//Global variable to keep track of system time as processes execute
	
	static MultilevelFeedbackQueueScheduler MFQS;
	
	public ProcessScheduler() {
		super();
	}//constructor

	/***
	 * Method to open the text file and convert the file into readable lines for the
	 * rest of the program to manipulate. Calls methods add and run to execute the 
	 * queueing algorithms
	 * @throws IOException if input of text file fails
	 */
	public static void execute() throws IOException {
	FileInputStream fstream = new FileInputStream("assignment1.txt");
	BufferedReader br = new BufferedReader(new InputStreamReader(fstream, "UTF-8"));
	String line = br.readLine();
	MFQS = new MultilevelFeedbackQueueScheduler();
	while(line!=null) {
		add(line);
		RoundRobin.setNumProcesses(RoundRobin.getNumProcesses() + 1);
		line = br.readLine();
	}
	run();
	}
	
	/***
	 * Method to convert each line read from buffered reader into a 'process' for the
	 * system to execute. Process attributes are assigned from the values parsed from
	 * the input string
	 * @param line is the string input read from the buffered reader
	 * Process is then added to the first level of the queue
	 */
	private static void add(String line) {
		String[] temp = line.split("\\s+");
		Process p = new Process();
		p.setProcessID(Integer.parseInt(temp[0]));
		p.setProcessAT(Integer.parseInt(temp[1]));
		p.setProcessBL(Integer.parseInt(temp[2]));
		MultilevelFeedbackQueueScheduler.RR2.addProcessToReadyQueue(p);
	}
	
	/***
	 * Method to initialize and execute the queueing process, beginning with the first round
	 * robin queue with burst time of 4, then passing the processes into the next round robin
	 * queue with burst time 8. The remaining processes are passed to a first come first
	 * serve queue, which run until completion.
	 */
	private static void run() {
		Process current = MultilevelFeedbackQueueScheduler.RR2.readyQueue.first();
		//sets the current process to the first process contained in the first level of queues
		System.out.println("Multilevel Feedback Queue Scheduling algorithm");
		System.out.println("============================================================");	
		//if statement to check that at least one of the queues contains processes to execute
		if(!MultilevelFeedbackQueueScheduler.RR2.isReadyQueueEmpty() || !MultilevelFeedbackQueueScheduler.RR1.isReadyQueueEmpty() || !MultilevelFeedbackQueueScheduler.FCFS.isReadyQueueEmpty()) {
			//while statement to ensure the round robin queue executes until it is empty
			while(!MultilevelFeedbackQueueScheduler.RR2.isReadyQueueEmpty()) {
				//for loop to run each process for the designated quantum time of the queue
				for(int i = 0; i < RoundRobin.getQuantumTime(); i++) {
					System.out.println("System time: " + systemTime + " process " + current.processID + " is running ");
					current.processBL -= 1;
					systemTime++;
				}
				//if statement to check if the process burst length is 0, print finished and remove if this is true
				//sets next process as the current to be operated on
				if(current.getProcessBL() <= 0) {
					System.out.println("System time: " + systemTime + " process " + current.processID + " is finished ");
					MultilevelFeedbackQueueScheduler.RR2.removeProcessFromReadyQueue();
					current = MultilevelFeedbackQueueScheduler.RR2.readyQueue.first();
				}
				//else statement removes the process from the queue and adds it to the next level of queueing
				//ensures the next process is loaded in the queue to be executed
				else {
					MultilevelFeedbackQueueScheduler.RR1.addProcessToReadyQueue(current);
					MultilevelFeedbackQueueScheduler.RR2.removeProcessFromReadyQueue();
					if(!MultilevelFeedbackQueueScheduler.RR2.isReadyQueueEmpty())
							current = MultilevelFeedbackQueueScheduler.RR2.readyQueue.first();
				}
			}
			//sets current process to the start of the next queue
			current = MultilevelFeedbackQueueScheduler.RR1.readyQueue.first();
			//while statement to ensure the round robin queue executes until it is empty
			while(!MultilevelFeedbackQueueScheduler.RR1.isReadyQueueEmpty()) {
				//for loop to run each process for the designated quantum time of the queue
				for(int i = 0; i < RoundRobin.getQuantumTime() * 2; i++) {
					System.out.println("System time: " + systemTime + " process " + current.processID + " is running ");
					current.processBL -= 1;
					systemTime++;
				}
				//if statement to check if the process burst length is 0, print finished and remove if this is true
				//sets next process as the current to be operated on
				if(current.getProcessBL() <= 0) {
					System.out.println("System time: " + systemTime + " process " + current.processID + " is finished ");
					MultilevelFeedbackQueueScheduler.RR1.removeProcessFromReadyQueue();
					current = MultilevelFeedbackQueueScheduler.RR1.readyQueue.first();
				}
				//else statement removes the process from the queue and adds it to the next level of queueing
				//ensures the next process is loaded in the queue to be executed
				else {
					MultilevelFeedbackQueueScheduler.FCFS.addProcessToReadyQueue(current);
					MultilevelFeedbackQueueScheduler.RR1.removeProcessFromReadyQueue();
					if(!MultilevelFeedbackQueueScheduler.RR1.isReadyQueueEmpty())
							current = MultilevelFeedbackQueueScheduler.RR1.readyQueue.first();
				}
			}
			//sets current process to the start of the next queue
			current = MultilevelFeedbackQueueScheduler.FCFS.readyQueue.first();
			//while statement to ensure the first come first serve queue executes until it is empty
			while(!MultilevelFeedbackQueueScheduler.FCFS.isReadyQueueEmpty()) {
				//unlike the round robin queues, the first come first serve algorithm runs the current process to completion
				if(current.processBL >= 0) {
					System.out.println("System time: " + systemTime + " process " + current.processID + " is running ");
					current.processBL -= 1;
					systemTime++;
				}
				//else statement to print that that process is finished, remove the process from the queue, and move to the next
				//process in the queue
				else{
					System.out.println("System time: " + systemTime + " process " + current.processID + " is finished ");
					MultilevelFeedbackQueueScheduler.FCFS.removeProcessFromReadyQueue();
					current = MultilevelFeedbackQueueScheduler.FCFS.readyQueue.first();
				}
			}//while
		}//if block
	}//method run
	
}//class ProcessScheduler
