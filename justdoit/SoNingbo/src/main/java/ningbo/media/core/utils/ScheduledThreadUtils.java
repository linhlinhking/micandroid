package ningbo.media.core.utils;

import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description:异步多线程执行任务工具
 * @author Devon.Ning
 * @2012-4-18下午04:00:57
 * @version 1.0
 * <p>Copyright (c) 2012 宁波商外文化传媒有限公司,Inc. All Rights Reserved.</p>
 */
public class ScheduledThreadUtils {

protected static final Logger logger = LoggerFactory.getLogger(ScheduledThreadUtils.class); 
	
	public static void start(Collection<Runnable> runnables){
		final ScheduledExecutorService scheduler= Executors.newScheduledThreadPool(runnables.size());
		logger.info("初始化线程池数量为:"+runnables.size());
		for(Runnable runnable:runnables){
			try{
				scheduler.schedule(runnable, 1, TimeUnit.SECONDS);
				logger.info("正在执行中hashCode:"+runnable.hashCode());
			}catch (Exception e) {
				logger.error("线程执行失败hashCode:"+runnable.hashCode());
			}
		}
		scheduler.shutdown();
		logger.info("全部线程任务结束");
	}
	
	public static void start(Runnable runnable){
		Collection<Runnable> runnables= new LinkedList<Runnable>();
		runnables.add(runnable);
		start(runnables);
	}
}
