package tech.flygo.juc.course0;

/**
 * @description: 线程打断 interrupt() and synchronized <br>
 *     设置线程打断标志位interrupt()和synchronized锁，线程是否被打断？
 * @author: flygo
 * @time: 2022/8/14 21:51
 */
public class T08InterruptAndSynchronized {

  private static Object o = new Object();

  public static void main(String[] args) {
    Thread t1 =
        new Thread(
            () -> {
              synchronized (o) {
                SleepHelper.sleepSeconds(10);
              }
            });

    t1.start();

    SleepHelper.sleepSeconds(1);

    // 线程t2只是去争抢线程t1线程对象o的锁
    Thread t2 =
        new Thread(
            () -> {
              synchronized (o) {
              }
              System.out.println("t2 end");
            });

    t2.start();

    // 设置线程打断标志位
    t2.interrupt();
  }
}
