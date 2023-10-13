package com.bigtree.aitest.thread;

import org.redisson.client.handler.ConnectionWatchdog;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

/**
 * @author admin
 * @ClassName CompletableFutureService
 * @description: TODO
 * @date 2023年09月25日
 * @version: 1.0
 */
public class CompletableFutureService {

 public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
  ExecutorService executorService = Executors.newFixedThreadPool(10);

//  CountDownLatch latch = new CountDownLatch(2);
//  Future<String> say = executorService.submit(()->{
//   System.out.println("说话 ...");
//   latch.countDown();
//   return "说话";
//  });
//
//  Future<String> drink = executorService.submit(()->{
//   System.out.println("喝水 ...");
//   latch.countDown();
//   return "喝水";
//  });
//  latch.await();
//  Future<String> go = executorService.submit(()->{
//   System.out.println("行动 ...");
//   return "go last";
//  });
//  System.out.printf(go.get());

  //testCompleFutreTask(executorService);

/*
  ---使用SynchronousQueue队列，提交的任务不会被保存，总是会马上提交执行。
   如果用于执行任务的线程数量小于maximumPoolSize，则尝试创建新的进程，
   如果达到maximumPoolSize设置的最大值，则根据你设置的handler执行拒绝策略。
   因此这种方式你提交的任务不会被缓存起来，而是会被马上执行，在这种情况下，你需要对你程序的并发量有个准确的评估，
  才能设置合适的maximumPoolSize数量，否则很容易就会执行拒绝策略；*/

  ThreadPoolExecutor executorSynQ = new ThreadPoolExecutor(
                  1,2,1000, TimeUnit.MILLISECONDS,
                        new SynchronousQueue<Runnable>(),Executors.defaultThreadFactory(),
                             new ThreadPoolExecutor.AbortPolicy());
 /*
 使用ArrayBlockingQueue有界任务队列，若有新的任务需要执行时，线程池会创建新的线程，直到创建的线程数量达到corePoolSize时，
 则会将新的任务加入到等待队列中。若等待队列已满，即超过ArrayBlockingQueue初始化的容量，
 则继续创建线程，直到线程数量达到maximumPoolSize设置的最大线程数量，若大于maximumPoolSize，则执行拒绝策略。
 在这种情况下，线程数量的上限与有界任务队列的状态有直接关系，如果有界队列初始容量较大或者没有达到超负荷的状态，
 线程数将一直维持在corePoolSize以下，反之当任务队列已满时，则会以maximumPoolSize为最大线程数上限。
 */
  ThreadPoolExecutor executorArryQ = new ThreadPoolExecutor(
          1,2,1000, TimeUnit.MILLISECONDS,
          new ArrayBlockingQueue<>(10),Executors.defaultThreadFactory(),
          new ThreadPoolExecutor.AbortPolicy());

  for (int i = 0; i < 29; i++) {
  // executorSynQ.execute(() -> System.out.println(Thread.currentThread().getName()));
   executorArryQ.execute(() -> System.out.println(Thread.currentThread().getName()));
  }


 }

 public static void testCompleFutreTask(ExecutorService executorService) throws ExecutionException, InterruptedException, TimeoutException {
   CompletableFuture<String> say = CompletableFuture.supplyAsync(()->{
    return "say"; 
   },executorService);
//  say.get();
  CompletableFuture<String> drink = CompletableFuture.supplyAsync(()->{
   return "drink";
  },executorService);
//drink.get();
  CompletableFuture<String> stringCompletableFuture = drink.thenCombine(drink, (sayAction, drinkAction) ->{
   try {
    return say.get(1, TimeUnit.MINUTES)+drink.get(1,TimeUnit.MINUTES)+"end";
   } catch (InterruptedException e) {
    throw new RuntimeException(e);
   } catch (ExecutionException e) {
    throw new RuntimeException(e);
   } catch (TimeoutException e) {
    throw new RuntimeException(e);
   }
  });
  stringCompletableFuture.join();
  System.out.println("===>"+stringCompletableFuture.get(1,TimeUnit.MINUTES));
 }


}