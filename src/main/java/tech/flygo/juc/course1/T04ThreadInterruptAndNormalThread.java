package tech.flygo.juc.course1;

import tech.flygo.juc.course0.SleepHelper;

/**
 * @description: 使用线程interrupt方法设置标志位，判断标志位停止线程
 * @author: flygo
 * @time: 2022/8/16 10:10
 */
public class T04ThreadInterruptAndNormalThread {

  public static void main(String[] args) {
    Thread t =
        new Thread(
            () -> {
              long i = 0L;
              // 判断线程标志位
              while (!Thread.interrupted()) {
                i++;
              }
              // 比如第1次停止线程i的值为: 2468460208, 第2次停止线程i的值为: 2466114518
              // 没有办法固定一个统一的停止时间，每次停止时间都不一样
              System.out.println("使用线程interrupt方法设置的标志位判断,优雅的停止线程-i的值: " + i);
              System.out.println("t thread end");
            });

    t.start();

    SleepHelper.sleepSeconds(1);

    // 设置线程标志位
    t.interrupt();
  }
}
