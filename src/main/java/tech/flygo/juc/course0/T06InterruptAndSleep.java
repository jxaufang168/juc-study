package tech.flygo.juc.course0;

import java.util.concurrent.TimeUnit;

/**
 * @description: 线程打断 interrupt() and sleep() wait() join() <br>
 * @author: flygo
 * @time: 2022/8/14 21:51
 */
public class T06InterruptAndSleep {

  public static void main(String[] args) throws Exception {
    Thread t =
        new Thread(
            () -> {
              try {
                // 线程 sleep() 、wait()、join()，线程被interrupt()之后，主动抛出InterruptedException异常
                Thread.sleep(10000);
              } catch (InterruptedException e) { // 抛出异常之后，java会默认把线程标志位复位
                System.out.println("-------Thread is interrupted-------");
                // 查询线程标志位
                System.out.println("查询线程打断标志位:" + Thread.currentThread().isInterrupted());
              }
            });

    t.start();

    TimeUnit.SECONDS.sleep(5);

    // 设置线程打断标志位
    t.interrupt();
  }
}
