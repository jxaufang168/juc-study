package tech.flygo.juc.course3;

/**
 * @description: 线程的可见性和有序性
 * @author: flygo
 * @time: 2022/8/19 10:40
 */
public class T02NoVisbility {

  private static /*volatile*/ boolean ready = false;
  private static int number;

  private static class ReaderThread extends Thread {
    @Override
    public void run() {
      while (!ready) {
        Thread.yield();
      }
      System.out.println(number);
    }
  }

  public static void main(String[] args) throws InterruptedException {
    Thread t = new ReaderThread();
    t.start();
    number = 42;
    ready = true;
    t.join();
  }
}
