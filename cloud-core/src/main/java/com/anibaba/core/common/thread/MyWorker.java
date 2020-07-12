package com.anibaba.core.common.thread;

import lombok.Data;

import java.util.List;

/**
 * @author lining
 */
public class MyWorker extends Thread {
    private String name;

    private List<Runnable> tasks;

    public MyWorker(String name, List<Runnable> tasks) {
        this.name = name;
        this.tasks = tasks;
    }

    @Override
    public void run() {
        while (tasks.size() > 0) {
            Runnable r = tasks.remove(0);
            r.run();
        }
    }

}
