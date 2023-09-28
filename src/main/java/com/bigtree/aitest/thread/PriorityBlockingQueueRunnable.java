package com.bigtree.aitest.thread;

/**
 * @author 实现 MyThreadFactory接口中的方法 获取线程 ,这里可能会有线程创建数据导致创建出错
 * @ClassName PriorityBlockingQueueRunnable
 * @description: 实现优先级比较器 先按优先级；若是相等 则按LocalTime
 * @date 2023年09月28日
 * @version: 1.0
 */
public class PriorityBlockingQueueRunnable implements Runnable, Comparable<PriorityBlockingQueueRunnable> {

    private int priority;

    public PriorityBlockingQueueRunnable(int priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(PriorityBlockingQueueRunnable o) {

        if (this.priority < o.priority) {
            return 1;
        }

        if (this.priority > o.priority) {
            return -1;
        }

        return 0;
    }

    @Override
    public void run() {
        System.out.printf("PriorityBlockingQueueRunnable run : %s Priorty : %d\n", Thread.currentThread().getName(), priority);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}