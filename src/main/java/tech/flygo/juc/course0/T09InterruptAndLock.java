package tech.flygo.juc.course0;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 线程打断 interrupt() and lock <br>
 *     设置线程打断标志位interrupt()和lock锁，线程是否被打断？
 * @author: flygo
 * @time: 2022/8/14 21:51
 */
public class T09InterruptAndLock {

  private static ReentrantLock lock = new ReentrantLock();

  public static void main(String[] args) {
    Thread t1 =
        new Thread(
            () -> {
              lock.lock();
              try {
                Thread.sleep(10000);
              } catch (InterruptedException e) {
                e.printStackTrace();
              } finally {
                lock.unlock();
              }
              System.out.println("t1 end");
            });

    t1.start();

    SleepHelper.sleepSeconds(1);

    // 线程t2只是去争抢线程t1线程lock锁
    Thread t2 =
        new Thread(
            () -> {
              lock.lock();

              lock.unlock();

              System.out.println("t2 end");
            });

    t2.start();

    SleepHelper.sleepSeconds(1);

    // 设置线程打断标志位
    t2.interrupt();
  }
}
