package com.anibaba.dict;

import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ThreadTests {

    public static int totalTicket = 100;

    @Test
    public void threadTest (){
        Thread thread1 = new Thread(new SaleTicket(), "线程一");
        Thread thread2 = new Thread(new SaleTicket(), "线程二");
        thread1.start();
        thread2.start();
        System.out.println(Thread.currentThread()+": 主方法运行结束");
    }


    @Data
    class SaleTicket implements Runnable {
        @Override
        public void run() {
            while (ThreadTests.totalTicket > 0) {
                try {
                    sale();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public synchronized void sale() throws InterruptedException {
            if (ThreadTests.totalTicket > 0) {
                System.out.print(Thread.currentThread().getName()+": 正在售票中");
                Thread.sleep(1L);
                System.out.print(".");
                System.out.print(".");
                System.out.println(".");
                System.out.println(Thread.currentThread().getName()+": 卖出一张票, 剩余"+ --ThreadTests.totalTicket);
            }
        }
    }
}
