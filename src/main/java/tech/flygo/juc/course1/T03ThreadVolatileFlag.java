package tech.flygo.juc.course1;

import tech.flygo.juc.course0.SleepHelper;

/**
 * @description: 使用volatile修饰的标志位判断，优雅的停止线程
 * @author: flygo
 * @time: 2022/8/15 22:37
 */
public class T03ThreadVolatileFlag {

  private static volatile boolean running = true;

  public static void main(String[] args) {
    Thread t =
        new Thread(
            () -> {
              long i = 0L;
              while (running) {
                i++;
              }
              // 比如第1次停止线程i的值为: 2468460208, 第2次停止线程i的值为: 2466114518
              // 没有办法固定一个统一的停止时间，每次停止时间都不一样
              System.out.println("使用volatile修饰的标志位判断,优雅的停止线程-i的值: " + i);
              System.out.println("t thread end");
            });

    t.start();
    SleepHelper.sleepSeconds(1);
    running = false;
  }
}
