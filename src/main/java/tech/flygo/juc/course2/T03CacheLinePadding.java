package tech.flygo.juc.course2;

import java.util.concurrent.CountDownLatch;

/**
 * @description: 缓存行对齐 缓存行64个字节是CPU同步的基本单位，缓存行隔离会比伪共享效率高
 * @author: flygo
 * @time: 2022/8/18 11:33
 */
public class T03CacheLinePadding {

  public static long COUNT = 10_0000_0000L;

  private static class T {
    private long p1, p2, p3, p4, p5, p6, p7;
    private long x = 0L;
    private long p9, p10, p11, p12, p13, p14, p15;
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
