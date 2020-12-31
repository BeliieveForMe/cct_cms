package com.guodf.owner.util.ThreadPool;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import lombok.Data;

/**
 * 优先级线程池
 * @author hanxb
 *
 */

public class PriorityThreadPoolExecutor extends ThreadPoolExecutor{

	
	private ThreadLocal<Integer> local = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };
    public PriorityThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, getWorkQueue());
    }
 
    public PriorityThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, getWorkQueue(), threadFactory);
    }
 
    public PriorityThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, getWorkQueue(), handler);
    }
 
    public PriorityThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, getWorkQueue(), threadFactory, handler);
        };

    protected static PriorityBlockingQueue getWorkQueue() {
        return new PriorityBlockingQueue();
    }
    
    @Override
    public void execute(Runnable command) {
        int priority = local.get();
        try {
            this.execute(command, priority);
        } finally {
            local.set(0);
        }
    }
 
    public void execute(Runnable command, int priority) {
        super.execute(new PriorityRunnable(command, priority));
    }
 
    public <T> Future<T> submit(Callable<T> task, int priority) {
        local.set(priority);
        return super.submit(task);
    }
 
    public <T> Future<T> submit(Runnable task, T result, int priority) {
        local.set(priority);
        return super.submit(task, result);
    }
 
    public Future<?> submit(Runnable task, int priority) {
        local.set(priority);
        return super.submit(task);
    }
 
    protected static class PriorityRunnable<E extends Comparable<? super E>> implements Runnable, Comparable<PriorityRunnable<E>> {
        private final static AtomicLong seq = new AtomicLong();
        private final long seqNum;
        Runnable run;
        private int priority;
 
        public PriorityRunnable(Runnable run, int priority) {
            seqNum = seq.getAndIncrement();
            this.run = run;
            this.priority = priority;
        }
 
        public int getPriority() {
            return priority;
        }
 
        public void setPriority(int priority) {
            this.priority = priority;
        }
 
        public Runnable getRun() {
            return run;
        }
 
        @Override
        public void run() {
            this.run.run();
        }
 
        @Override
        public int compareTo(PriorityRunnable<E> other) {
            int res = 0;
            if (this.priority == other.priority) {
                if (other.run != this.run) {// ASC
                    res = (seqNum < other.seqNum ? -1 : 1);
                }
            } else {// DESC
                res = this.priority > other.priority ? -1 : 1;
            }
            return res;
        }
    }
}
