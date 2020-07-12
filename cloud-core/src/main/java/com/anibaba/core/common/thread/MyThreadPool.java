package com.anibaba.core.common.thread;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author lining
 *  自定义线程池
 *  成员变量
 *  1. 任务队列          线程安全问题
 *  2. 当前线程数量
 *  3. 核心线程数量
 *  4. 最大线程数量
 *  5. 任务队列的长度
 *
 *  成员方法
 *  1. 提交任务
 *  2. 执行任务
 */
public class MyThreadPool {

    //任务队列
    private List<Runnable> tasks = Collections.synchronizedList(new LinkedList<>());

    //当前线程数量
    private int num;

    //核心线程数量
    private int corePoolSize;

    //最大线程数量
    private int maxSize;

    //任务队列的长度
    private int workSize;

    public MyThreadPool(int corePoolSize, int maxSize, int workSize) {
        this.corePoolSize = corePoolSize;
        this.maxSize = maxSize;
        this.workSize = workSize;
    }

    //提交任务
    public void submit(Runnable r) {
        if (tasks.size() >= workSize){
            System.out.println("任务:"+r+"被丢弃了");
        }else {
            tasks.add(r);
            execTask(r);
        }
    }

    public void execTask(Runnable r) {
        if (num < corePoolSize){
            new MyWorker("核心线程: "+ num,tasks).start();
            num++;
        } else if (num < maxSize) {
            new MyWorker("非核心线程: "+ num,tasks).start();
            num++;
        } else {
            System.out.println("任务"+r+"被缓存了...");
        }
    }
}
