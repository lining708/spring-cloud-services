package com.anibaba.core.common.thread;

import lombok.Data;

/**
 * @author lining
 */
@Data
public class MyTask implements Runnable{
    private int id;

    public MyTask(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println("线程:" + threadName + ", 即将执行任务: " + id);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程:" + threadName + ", 完成任务: " + id);
    }
}
