package tech.flygo.juc.course2;

import tech.flygo.juc.course0.SleepHelper;

/**
 * @description: volatile 引用类型(包括数组) 只能保证引用本身的可见性，不能保证内部字段的可见性
 * @author: flygo
 * @time: 2022/8/18 10:57
 */
public class T02ThreadVolatileReference {

  public static class A {
    boolean running = true;

    void m() {
      System.out.println("------m method start------");
      while (running) {}
      System.out.println("------m method end------");
    }
  }

  private static volatile A a = new A();

  public static void main(String[] args) {
    new Thread(a::m, "t1").start();
    SleepHelper.sleepSeconds(1);
    a.running = false;
  }
}
