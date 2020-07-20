/***
 * Main class of a program that creates a multilevel feedback queue, consisting
 * of two round robin queues of different quantum times, as well as a first come
 * first serve queue to handle the remaining processes. Calls the method execute
 * from the ProcessScheduler class.
 * @author M.Malo
 */
import java.io.IOException;

public class Assignment1 {
	

	public static void main(String[] args) throws IOException{
		ProcessScheduler.execute();
	}
}
