/***
 * Class to define a 'process', which will be the data type passed through the 
 * queues and executed to completion.
 * @author M.Malo
 *
 */
public class Process {

	//Global variables for the process data types
	int processID;
	int processAT;
	int processBL;
	
	public Process() {
		processID = 0;
		processAT = 0;
		processBL = 0;
	}//constructor
	
	//getter method for process ID
	protected int getProcessID() {
		return processID;
	}
	
	//getter method for process arrival time
	protected int getProcessAT() {
		return processAT;
	}
	
	//getter method for process burst length
	protected int getProcessBL() {
		return processBL;
	}
	
	//setter method for process id
	protected void setProcessID(int p) {
		processID = p;
	}
	
	//setter method for process arrival time
	protected void setProcessAT(int p) {
		processAT = p;
	}
	
	//setter method for process burst length
	protected void setProcessBL(int p) {
		processBL = p;
	}
	
}//class Process
