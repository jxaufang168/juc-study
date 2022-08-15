package tech.flygo.juc.course0;

import java.util.concurrent.TimeUnit;

/**
 * @description: 线程打断 interrupt() and interrupted() <br>
 *     设置线程打断标志位 与 查询当前线程标志位，并且重置线程标志位
 * @author: flygo
 * @time: 2022/8/14 21:51
 */
public class T05InterruptAndInterrupted {

  public static void main(String[] args) throws InterruptedException {
    Thread t =
        new Thread(
            () -> {
              for (; ; ) {
                if (Thread.interrupted()) { // 查询当前线程标志位，并且重置线程标志位
                  System.out.println("-------Thread is interrupted-------");
                  // 查询当前线程标志位，并且重置线程标志位
                  System.out.println("查询线程打断标志位:" + Thread.interrupted());
                }
              }
            });

    t.start();

    TimeUnit.SECONDS.sleep(2);

    // 设置线程打断标志位
    t.interrupt();

    System.out.println("main主线程的线程打断标志位: " + t.interrupted());
  }
}
