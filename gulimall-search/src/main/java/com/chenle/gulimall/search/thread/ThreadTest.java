package com.chenle.gulimall.search.thread;

import java.util.concurrent.*;

/**
      @param corePoolSize the number of threads to keep in the pool, even
        * if they are idle, unless {@code allowCoreThreadTimeOut} is set
        池中一直保持的线程的数量，即使线程空闲。除非设置了 allowCoreThreadTimeOut
        * @param maximumPoolSize the maximum number of threads to allow in the
        * pool
        池中允许的最大的线程数
        * @param keepAliveTime when the number of threads is greater than
        * the core, this is the maximum time that excess idle threads
        * will wait for new tasks before terminating. 当线程数大于核心线程数的时候，线程在最大多长时间没有接到新任务就会终止释放，
        最终线程池维持在 corePoolSize 大小
        * @param unit the time unit for the {@code keepAliveTime} argument
        时间单位
        * @param workQueue the queue to use for holding tasks before they are
        * executed. This queue will hold only the {@code Runnable}
        * tasks submitted by the {@code execute} method. 阻塞队列，用来存储等待执行的任务，如果当前对线程的需求超过了 corePoolSize
        大小，就会放在这里等待空闲线程执行。
        * @param threadFactory the factory to use when the executor
        * creates a new thread
        创建线程的工厂，比如指定线程名等
        * @param handler the handler to use when execution is blocked
        * because the thread bounds and queue capacities are reached
        拒绝策略，如果线程满了，线程池就会使用拒绝策略
**/

public class ThreadTest {

    public static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
         System.out.println("main......start.....");



//         Thread thread = new Thread01();
//         thread.start();
//         Runable01 runable01 = new Runable01();
//         new Thread(runable01).start();


//        FutureTask<Integer> futureTask = new FutureTask<>(new Callable01());
//        new Thread(futureTask).start();


//         executor.execute(new Runable01());

/**
       运行流程：
        1、线程池创建，准备好 core 数量的核心线程，准备接受任务
        2、新的任务进来，用 core 准备好的空闲线程执行。
        (1) 、core 满了，就将再进来的任务放入阻塞队列中。空闲的 core 就会自己去阻塞队
                列获取任务执行
        (2) 、阻塞队列满了，就直接开新线程执行，最大只能开到 max 指定的数量
                (3) 、max 都执行好了。Max-core 数量空闲的线程会在 keepAliveTime 指定的时间后自
        动销毁。最终保持到 core 大小
                (4) 、如果线程数开到了 max 的数量，还有新任务进来，就会使用 reject 指定的拒绝策
                略进行处理
        3、所有的线程创建都是由指定的 factory 创建的。
        面试：
        一个线程池 core 7； max 20 ，queue：50，100 并发进来怎么分配的；
        先有 7 个能直接得到执行，接下来 50 个进入队列排队，在多开 13 个继续执行。现在 70 个
        被安排上了。剩下 30 个默认拒绝策略。
        **/

//        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,
//                200,
//                10,
//                TimeUnit.SECONDS,
//                new LinkedBlockingDeque<>(100000),
//                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy());


/**
 * newCachedThreadPool
 *  创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若
 * 无可回收，则新建线程。
 *  newFixedThreadPool
 *  创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
 *  newScheduledThreadPool
 *  创建一个定长线程池，支持定时及周期性任务执行。
 *  newSingleThreadExecutor
 *  创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务
 * 按照指定顺序(FIFO, LIFO, 优先级)执行。
 */

        System.out.println("main......start.....");
//         CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
//             System.out.println("当前线程：" + Thread.currentThread().getId());
//             int i = 10 / 2;
//             System.out.println("运行结果：" + i);
//         }, executor);

        /**
         * 方法完成后的处理
        */
//         CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
//             System.out.println("当前线程：" + Thread.currentThread().getId());
//             int i = 10 / 0;
//             System.out.println("运行结果：" + i);
//             return i;
//         }, executor).whenComplete((res,exception) -> {
//             //虽然能得到异常信息，但是没法修改返回数据
//             System.out.println("异步任务成功完成了...结果是：" + res + "异常是：" + exception);
//         }).exceptionally(throwable -> {
//             //可以感知异常，同时返回默认值
//             return 10;
//         });

        /**
         * 方法执行完后端处理
         */
//         CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
//             System.out.println("当前线程：" + Thread.currentThread().getId());
//             int i = 10 / 2;
//             System.out.println("运行结果：" + i);
//             return i;
//         }, executor).handle((result,thr) -> {
//             if (result != null) {
//                 return result * 2;
//             }
//             if (thr != null) {
//                 System.out.println("异步任务成功完成了...结果是：" + result + "异常是：" + thr);
//                 return 0;
//             }
//             return 0;
//         });
//        System.out.println(""+future.get());

        /**
         * 线程串行化
         * 1、thenRunL：不能获取上一步的执行结果
         * 2、thenAcceptAsync：能接受上一步结果，但是无返回值
         * 3、thenApplyAsync：能接受上一步结果，有返回值
         */

//         CompletableFuture<Void> future1 = CompletableFuture.supplyAsync(() -> {
//             System.out.println("当前线程：" + Thread.currentThread().getId());
//             int i = 10 / 2;
//             System.out.println("运行结果：" + i);
//             return i;
//         }, executor).thenRunAsync(()->{
//             System.out.println("任务2启动了...");
//         },executor);

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果：" + i);
            return i;
        }, executor).thenApplyAsync(res -> {
            System.out.println("任务2启动了..." + res);
            return "Hello" + res;
        }, executor);
        System.out.println("main......end....." + future.get());


    }



    private static void threadPool() {

        ExecutorService threadPool = new ThreadPoolExecutor(
                200,
                10,
                10L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(10000),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        //定时任务的线程池
        ExecutorService service = Executors.newScheduledThreadPool(2);
    }



    public static class Thread01 extends Thread {
        @Override
        public void run() {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果：" + i);
        }
    }


    public static class Runable01 implements Runnable {
        @Override
        public void run() {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果：" + i);
        }
    }


    public static class Callable01 implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果：" + i);
            return i;
        }
    }
}
