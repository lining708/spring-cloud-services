package com.anibaba.core.common.thread;

import java.util.Date;
import java.util.concurrent.*;

public class ThreadTests {

    public static void main(String[] args) throws CloneNotSupportedException {
        System.out.println("执行开始");
        for (int i = 0; i < 100; i++) {
            new ThreadTests().orderRun();
        }
        Runtime.getRuntime().halt(1);
        System.out.println("执行结束");

    }

    public void orderRun() {
        final Thread firstThread;
        final Thread secondThread;
        final Thread thirdThread;
        firstThread = new Thread(() -> {
            System.out.println("thirdThread执行");
        });
        secondThread = new Thread(() -> {
            try {
                firstThread.join();
                System.out.println("secondThread执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thirdThread = new Thread(() -> {
            try {
                firstThread.join();
                System.out.println("thirdThread执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        firstThread.start();
        secondThread.start();
        thirdThread.start();
    }

    public void demo1() {
        //创建线程池
        MyThreadPool threadPool = new MyThreadPool(2, 4, 20);
        //提交任务
        for (int i = 0; i < 21; i++) {
            MyTask myTask = new MyTask(i);
            threadPool.submit(myTask);
        }
    }

    public void demo2() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3, new ThreadFactory() {
            int n = 1;

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "自定义线程名--- " + n++);
            }
        });

        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            System.out.println(Thread.currentThread() + "执行任务: " + new Date());
        }, 1, 5, TimeUnit.SECONDS);
    }

    public void demo3() {
        ExecutorService es = Executors.newCachedThreadPool();
        Future<Integer> future = es.submit(new MyCall(5, 25));

        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    class MyCall implements Callable<Integer> {
        private int a;
        private int b;

        public MyCall(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public Integer call() throws Exception {
            Thread.sleep(5000);
            return a + b;
        }
    }
}

abstract class Test {
}