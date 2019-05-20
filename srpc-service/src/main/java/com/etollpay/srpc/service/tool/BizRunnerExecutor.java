package com.etollpay.srpc.service.tool;

import com.etollpay.srpc.service.util.SysConfig;
import com.etollpay.srpc.standard.basic.Metadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class BizRunnerExecutor {
    private static Logger log = LoggerFactory.getLogger(BizRunnerExecutor.class);

    private static ExecutorService executorService = null;

    private static byte[] lock = new byte[0];

    private static ExecutorService getExecutorService(Metadata metadata) {
        log.debug("execute thread {}...", metadata != null ? metadata.getApiName() : "");
        synchronized (lock) {
            if (executorService == null) {
                int processors = Runtime.getRuntime().availableProcessors();
                int corePoolSize = SysConfig.getInt("corePoolSize", processors + 1);
                int maxPoolSize = SysConfig.getInt("maxPoolSize",  processors * 2);
                executorService = new ThreadPoolExecutor(corePoolSize, maxPoolSize, 5, TimeUnit.SECONDS,
                        new LinkedBlockingDeque<Runnable>(5));
            }
        }
        return executorService;
    }

    public static void execute(Runnable command, Metadata metadata) {
        getExecutorService(metadata).execute(command);
    }

    public static void execute(Runnable command) {
        execute(command, null);
    }

    public static void shutdown() {
        if (executorService != null) {
            executorService.shutdownNow();
            executorService = null;
        }
    }


    @Override
    protected void finalize() throws Throwable {
        shutdown();
        super.finalize();
    }
}
