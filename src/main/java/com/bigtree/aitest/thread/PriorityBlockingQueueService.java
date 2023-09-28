package com.bigtree.aitest.thread;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author admin
 * @ClassName PriorityBlockingQueueService
 * @description: 拥有优先级的阻塞队列
 * @date 2023年09月28日
 * @version: 1.0
 */
public class PriorityBlockingQueueService {

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
    private static int count;

    public static void main(String[] args) {


        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(2, 2, 1,
                        TimeUnit.MILLISECONDS, new PriorityBlockingQueue<Runnable>());

        for (int i = 0; i < 5; i++) {
            System.out.println(PriorityBlockingQueueService.format.format(System.currentTimeMillis()) + "加入消息==> 加入队列第" + (++count) + "条消息！");
            executor.execute(new PriorityBlockingQueueRunnable(1));
        }

     try {
      Thread.sleep(100);
     } catch (InterruptedException e) {
      throw new RuntimeException(e);
     }

     for (int i = 0; i < 5; i++) {
      System.out.println(PriorityBlockingQueueService.format.format(System.currentTimeMillis()) + "加入消息==> 加入队列第" + (++count) + "条消息！");
      executor.execute(new PriorityBlockingQueueRunnable(5));
     }

     try {
      Thread.sleep(100);
     } catch (InterruptedException e) {
      throw new RuntimeException(e);
     }

     executor.shutdown();

     try {
      executor.awaitTermination(1,TimeUnit.HOURS);
     } catch (InterruptedException e) {
      throw new RuntimeException(e);
     }
     System.out.println("over");

    }


}