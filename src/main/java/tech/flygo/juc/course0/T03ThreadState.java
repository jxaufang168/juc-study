package tech.flygo.juc.course0;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 线程的状态切换
 * @author: flygo
 * @time: 2022/8/13 22:01
 */
public class T03ThreadState {

  /**
   * description: 线程状态 New -> Runnable -> Terminated <br>
   * date: 2022/8/13 22:02 <br>
   * author: flygo <br>
   *
   * @return void
   */
  public static void stateNewRunnableTerminated() throws Exception {
    System.out.println("-------线程状态 New -> Runnable -> Terminated-------");
    Thread t1 =
        new Thread(
            () -> {
              System.out.println(
                  "stateNewRunnableTerminated()-2: " + Thread.currentThread().getState());
              for (int i = 0; i < 3; i++) {
                SleepHelper.sleepSeconds(1);
                System.out.print(i + " ");
              }
              System.out.println();
            });

    System.out.println("stateNewRunnableTerminated()-1: " + t1.getState());

    t1.start();
    // 等待线程t1执行结束
    t1.join();

    System.out.println("stateNewRunnableTerminated()-3: " + t1.getState());
    System.out.println();
  }

  /**
   * description: 线程状态 Waiting -> TimedWaiting <br>
   * date: 2022/8/13 22:17 <br>
   * author: flygo <br>
   *
   * @return void
   */
  public static void stateWaitingTimedWaiting() {
    System.out.println("-------线程状态 Waiting -> TimedWaiting-------");
    Thread t2 =
        new Thread(
            () -> {
              LockSupport.park();
              System.out.println("stateWaitingTimedWaiting()-: " + "t2 go on");
              SleepHelper.sleepSeconds(5);
            });

    t2.start();
    SleepHelper.sleepSeconds(1);
    System.out.println("stateWaitingTimedWaiting()-4: " + t2.getState());

    LockSupport.unpark(t2);
    SleepHelper.sleepSeconds(1);
    System.out.println("stateWaitingTimedWaiting()-5: " + t2.getState());
    System.out.println();
  }

  /**
   * description: 线程状态 Blocked <br>
   * date: 2022/8/13 22:30 <br>
   * author: flygo <br>
   *
   * @return void
   */
  public static void stateBlocked() {
    System.out.println("-------线程状态 Blocked-------");

    final Object o = new Object();
    Thread t3 =
        new Thread(
            () -> {
              synchronized (o) {
                System.out.println("stateBlocked()-: t3得到了锁 o");
              }
            });

    new Thread(
            () -> {
              synchronized (o) {
                SleepHelper.sleepSeconds(5);
              }
            })
        .start();

    SleepHelper.sleepSeconds(1);

    t3.start();
    SleepHelper.sleepSeconds(1);

    System.out.println("stateBlocked()-6: " + t3.getState());
    System.out.println();
  }

  /**
   * description: 线程状态在lock和synchronized的区别 <br>
   * date: 2022/8/13 22:44 <br>
   * author: flygo <br>
   *
   * @return void
   */
  public static void threadOnLock() {
    System.out.println("-------线程状态在lock和synchronized的区别-------");

    final Lock lock = new ReentrantLock();
    Thread t4 =
        new Thread(
            () -> {
              lock.lock();
              System.out.println("threadOnLock()-: t4得到了锁");
              lock.unlock();
            });

    new Thread(
            () -> {
              lock.lock();
              SleepHelper.sleepSeconds(5);
              lock.unlock();
            })
        .start();

    t4.start();

    SleepHelper.sleepSeconds(1);
    System.out.println("threadOnLock()-7: " + t4.getState());
  }

  /**
   * description: 线程park之后的状态 <br>
   * date: 2022/8/13 22:55 <br>
   * author: flygo <br>
   *
   * @return void
   */
  public static void threadPark() {
    System.out.println("-------线程park之后的状态-------");

    Thread t5 =
        new Thread(
            () -> {
              LockSupport.park();
            });

    t5.start();

    SleepHelper.sleepSeconds(1);

    System.out.println("threadPark()-8: " + t5.getState());
    LockSupport.unpark(t5);
  }

  public static void main(String[] args) throws Exception {
    // 线程状态 New -> Runnable -> Terminated
    stateNewRunnableTerminated();

    // 线程状态 Waiting -> TimedWaiting
    stateWaitingTimedWaiting();

    // 线程状态 Blocked
    stateBlocked();

    // 线程状态在lock和synchronized的区别
    threadOnLock();

    // 线程park之后的状态
    threadPark();
  }
}
