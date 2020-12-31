package com.guodf.owner.util.ThreadPool;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ThreadPool {
    private static final Log logger = LogFactory.getLog(ThreadPool.class);

    public static ThreadExecutor4JDK threadExecutor4JDK = null;

    public static void initTreadExecutor() {
        logger.info("【线程池--初始化--开始！】");
        threadExecutor4JDK = new ThreadExecutor4JDK();
        threadExecutor4JDK.start();
        logger.info("【线程池--初始化--完成！】");
    }

    public static void put(Thread rechargeThread) {
        if (threadExecutor4JDK == null) {
            initTreadExecutor();
        }
        try {
//			logger.debug("线程池正在存放任务………………………………………………………………");
            threadExecutor4JDK.execute(rechargeThread);
        } catch (Exception e) {
            logger.error("线程池接收任务失败！", e);
        }
    }
}
