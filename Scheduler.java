public abstract class Scheduler
{
  protected LinkedQueue<Process> readyQueue;

  public Scheduler()
  {
    readyQueue = new LinkedQueue<>();
  }

  public boolean isReadyQueueEmpty()
  {
    return readyQueue.isEmpty();
  }

  public abstract void addProcessToReadyQueue(Process p);

  public abstract Process removeProcessFromReadyQueue();
}