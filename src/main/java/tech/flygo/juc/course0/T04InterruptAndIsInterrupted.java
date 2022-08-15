package tech.flygo.juc.course0;

import java.util.concurrent.TimeUnit;

/**
 * @description: 线程打断 interrupt() and isInterrupted() <br>
 *     设置线程打断标志位 与 查询线程打断标志位
 * @author: flygo
 * @time: 2022/8/14 21:51
 */
public class T04InterruptAndIsInterrupted {

  public static void main(String[] args) throws InterruptedException {
    Thread t =
        new Thread(
            () -> {
              for (; ; ) {
                if (Thread.currentThread().isInterrupted()) {
                  System.out.println("-------Thread is interrupted-------");
                  // 查询线程标志位
                  System.out.println("查询线程打断标志位:" + Thread.currentThread().isInterrupted());
                }
              }
            });

    t.start();

    TimeUnit.SECONDS.sleep(2);

    // 设置线程打断标志位
    t.interrupt();
  }
}
