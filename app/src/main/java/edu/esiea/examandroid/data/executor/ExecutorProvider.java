package edu.esiea.examandroid.data.executor;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorProvider {
    private static volatile ExecutorProvider INSTANCE;
    private final ExecutorService executorService;

    private ExecutorProvider() {
        executorService = Executors.newSingleThreadExecutor();
    }

    public static ExecutorProvider getInstance() {
        if (INSTANCE == null) {
            synchronized (ExecutorProvider.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ExecutorProvider();
                }
            }
        }
        return INSTANCE;
    }

    public ExecutorService getExecutor() {
        return executorService;
    }

    public void shutdown() {
        executorService.shutdown();
    }
}