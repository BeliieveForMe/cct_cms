package com.guodf.owner.util.ThreadPool;

import lombok.extern.slf4j.Slf4j;
import java.util.Collection;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 如果新的日志框架
 * 
 * @author wangtl
 * 
 */
@Slf4j
public class ThreadExecutor4JDK implements Executor {
	// ---------------------------------------------- Properties
	protected int threadPriority = Thread.NORM_PRIORITY;//线程级别5 

	protected boolean daemon = true;

	protected String namePrefix = "threadExec-sitech-";

	// 1.当poolsize(运行的线程)小于该值，那么只要来了一个request，就新创建一个thread来执行
	// 2.当poolsize已经大于或等于该值，那么来了一个request后，就放进queue中，等来线程执行；
	protected int corePoolSize = 100; //线程池维护线程的最少数量
	// 3.如果queue放不进去了，就新创建一个thread来执行（除非poolsize超过了该值，如果超过了则拒绝执行）
	protected int maximumPoolSize = 150;//线程池维护线程的最大数量

	// 1.如果实际的线程数大于coresize，那么池子中的所有thread过了keep-alive的时间之后，就会被kill掉。这个时间是可以动态设定的；
	// 2.如果小于coresize则不管了
	//keepAliveTime： 线程池维护线程所允许的空闲时间
	
	protected int maxIdleTime = 60000;

	protected ThreadPoolExecutor executor = null;//线程池类

	protected String name;

	// Number of tasks submitted and not yet completed.
	public AtomicInteger submittedTasksCount;
	
	
	// ---------------------------------------------- Constructors
	public ThreadExecutor4JDK() {
		// empty constructor for the digester
		
	}

	// ---------------------------------------------- Public Methods
	public TaskQueue taskqueue = null;
	public int maxSize4Queue=1000;

	public void start() {
		log.info("线程池初始化开始.....");
		log.info("corePoolSize=" + corePoolSize);
		log.info("maximumPoolSize=" + maximumPoolSize);
		log.info("maxIdleTime=" + maxIdleTime);
		log.info("maxSize4Queue=" + maxSize4Queue);
		taskqueue = new TaskQueue();

		// 可以通过设置默认的ThreadFactory来改变threadpool如何创建thread
		TaskThreadFactory tf = new TaskThreadFactory(namePrefix);
		//以下是线程池常用构造方法为：
		executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, maxIdleTime, TimeUnit.MILLISECONDS, taskqueue, tf) {
			@Override
			protected void afterExecute(Runnable r, Throwable t) {
				AtomicInteger atomic = submittedTasksCount;
				if (atomic != null) {
					atomic.decrementAndGet();
				}
			}
		};
		
		taskqueue.setParent((ThreadPoolExecutor) executor);
		submittedTasksCount = new AtomicInteger();
		log.info("线程框架完成启动...");
	}

	public void stop() {
		if (executor != null)
			executor.shutdown();
		executor = null;
		submittedTasksCount = null;
	}

	public void execute(Runnable command) {
		try {
			if (executor != null) {
				submittedTasksCount.incrementAndGet();
				try {
//					if (command instanceof InfoWriter) {
//						log.debug("正在处理："+((InfoWriter)command).getVo().getClass().getName());
//					}else {
//						log.warn("正在处理:未知类型,请核查:"+command);
//					}
					executor.execute(command);
				} catch (RejectedExecutionException rx) {
					// there could have been contention around the queue
					if (((TaskQueue) executor.getQueue()).force(command)) {
						submittedTasksCount.decrementAndGet();// 已经强制处理完成
					} else {
						log.error("没有force成功,请注意可能会数据丢失!");
					}
				}
			} else
				throw new IllegalStateException("StandardThreadPool not started.");
		} catch (Exception e) {// 安静模式，此处只记录不往外抛给应用
			log.error("任务执行失败！", e);
		}
	}

	public int getThreadPriority() {
		return threadPriority;
	}

	public boolean isDaemon() {

		return daemon;
	}

	public String getNamePrefix() {
		return namePrefix;
	}

	public int getMaxIdleTime() {
		return maxIdleTime;
	}

	public String getName() {
		return name;
	}

	public void setThreadPriority(int threadPriority) {
		this.threadPriority = threadPriority;
	}

	public void setDaemon(boolean daemon) {
		this.daemon = daemon;
	}

	public void setNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
	}

	public void setMaxIdleTime(int maxIdleTime) {
		this.maxIdleTime = maxIdleTime;
		if (executor != null) {
			executor.setKeepAliveTime(maxIdleTime, TimeUnit.MILLISECONDS);
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	// Statistics from the thread pool
	public int getActiveCount() {
		return (executor != null) ? executor.getActiveCount() : 0;
	}

	public long getCompletedTaskCount() {
		return (executor != null) ? executor.getCompletedTaskCount() : 0;
	}

	public int getCorePoolSize() {
		return (executor != null) ? executor.getCorePoolSize() : 0;
	}

	public int getLargestPoolSize() {
		return (executor != null) ? executor.getLargestPoolSize() : 0;
	}

	public int getPoolSize() {
		return (executor != null) ? executor.getPoolSize() : 0;
	}

	public int getQueueSize() {
		return (executor != null) ? executor.getQueue().size() : -1;
	}

	public TaskQueue getTaskqueue() {
		return taskqueue;
	}

	public int getMaximumPoolSize() {
		return maximumPoolSize;
	}

	
	// ---------------------------------------------- TaskQueue Inner Class
	@SuppressWarnings("serial")
	public class TaskQueue extends LinkedBlockingQueue<Runnable> {
		ThreadPoolExecutor parent = null;


		public TaskQueue() {
			super();
		}

		public TaskQueue(int initialCapacity) {
			super(initialCapacity);
		}

		public TaskQueue(Collection<? extends Runnable> c) {
			super(c);
		}

		public void setParent(ThreadPoolExecutor tp) {
			parent = tp;
		}

		/**
		 * 强制实时调用：一般用作正常逻辑是入库，调度为强制日志记录
		 * 
		 * @param taskInfo
		 * @return
		 */
		public boolean force(Runnable taskInfo) {
			if (parent.isShutdown())
				throw new RejectedExecutionException("Executor not running, can't force a command into the queue");
			// modify wangtl,当队列满了就记日志,后续处理
			log.debug("线程池和缓存队列均饱满,进行拒绝记录处理。==开始");
//			if (taskInfo instanceof InfoWriter) {
//				InfoWriter infoWriter = (InfoWriter) taskInfo;
//				log.info("拒绝执行："+infoWriter.getVo().getClass().getName());
//				WriterTool writerTool = LogstatFactory.getLogWriter(infoWriter.getVo().getClass().getName());
//				if (writerTool instanceof WriterTool4Direct) {
//					((WriterTool4Direct) writerTool).directWriteOnFull(infoWriter.getVo());
//				}
//			}else {
//				log.warn("未知类型的任务....,请检查:"+taskInfo);
//			}
			log.debug("线程池和缓存队列均饱满,进行拒绝记录处理。==结束");
			return true;
		}

		public boolean offer(Runnable o) {
			// we can't do any checks
			if (parent == null)
				return super.offer(o);
			int poolSize = parent.getPoolSize();
			// add by wangtlc 如果队列超过最大值就不再进队列了
			if (this.size() >= maxSize4Queue) {
				return false;
			}
			// we are maxed out on threads, simply queue the object
			if (parent.getPoolSize() == parent.getMaximumPoolSize())
				return super.offer(o);
			// we have idle threads, just add it to the queue  note that we don't use getActiveCount(), see BZ 49730
			AtomicInteger submittedTasksCount = ThreadExecutor4JDK.this.submittedTasksCount;
			if (submittedTasksCount != null) {
				if (submittedTasksCount.get() <= poolSize)
					return super.offer(o);
			}
			// if we have less threads than maximum force creation of a new  thread
			if (poolSize < parent.getMaximumPoolSize())
				return false;
			// if we reached here, we need to add it to the queue
			return super.offer(o);
		}
	}

	// ---------------------------------------------- ThreadFactory Inner Class
	class TaskThreadFactory implements ThreadFactory {
		final ThreadGroup group;
		final AtomicInteger threadNumber = new AtomicInteger(1);
		final String namePrefix;

		TaskThreadFactory(String namePrefix) {
			SecurityManager s = System.getSecurityManager();
			group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
			this.namePrefix = namePrefix;
		}

		public Thread newThread(Runnable r) {
			Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement());
			t.setDaemon(daemon);
			t.setPriority(getThreadPriority());
			return t;
		}
	}

}
