package com.anibaba.core.common.thread;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

public class GoosSale {
    public static List<String> goosList;

    static {
        System.out.println("==================执行静态代码块内容===================");
        goosList = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            goosList.add(Character.toString((char)('一' + i)));
        }
        goosList.forEach(System.out::println);
        System.out.println("==================执行静态代码块内容===================");
    }

    public static void main(String[] args) {
        GoosSale goosSale = new GoosSale();
        goosSale.demo1();
    }

    public void demo1() {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 5, 1, TimeUnit.MINUTES, new LinkedBlockingDeque<>(10));
        for (int i = 1; i <= 20; i++) {
            Future<String> result = threadPool.submit(new GoodsSaleTask(i + ""));
            try {
                System.out.println(result.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

        }
    }

    class GoodsSaleTask implements Callable<String> {


        private String username;

        public GoodsSaleTask(String username) {
            this.username = username;
        }

        @Override
        public String call() throws Exception {
            String resultMessage;
            synchronized (GoodsSaleTask.class) {
                if (goosList.size() > 0) {
                    resultMessage = "用户"+username+"---执行线程"+Thread.currentThread().getName()+"抢到了商品: " + GoosSale.goosList.remove(0);
                } else {
                    resultMessage = "用户"+username+"---执行线程"+Thread.currentThread().getName()+"秒杀商品失败";
                }
            }
            return resultMessage;
        }
    }
}
