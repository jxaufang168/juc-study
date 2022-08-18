package tech.flygo.juc.course2;

import sun.misc.Contended;

import java.util.concurrent.CountDownLatch;

/**
 * @description: 注意：运行这个程序的时候，需要加参数：-XX:-RestrictContended
 * @author: flygo
 * @time: 2022/8/18 14:22
 */
public class T04Contended {

  public static long COUNT = 10_0000_0000L;

  private static class T {
    @Contended // 只有1.8起作用
    private long x = 0L;
  }

  public static T[] arr = new T[2];

  static {
    arr[0] = new T();
    arr[1] = new T();
  }

  public static void main(String[] args) throws Exception {
    CountDownLatch latch = new CountDownLatch(2);

    Thread t1 =
        new Thread(
            () -> {
              for (int i = 0; i < COUNT; i++) {
                arr[0].x = i;
              }
              latch.countDown();
            });

    Thread t2 =
        new Thread(
            () -> {
              for (int i = 0; i < COUNT; i++) {
                arr[1].x = i;
              }
              latch.countDown();
            });

    final long start = System.nanoTime();
    t1.start();
    t2.start();

    latch.await();
    final long end = System.nanoTime();

    System.out.println("t1和t2线程总共执行的时间: " + (end - start) / 100_0000);
  }
}
