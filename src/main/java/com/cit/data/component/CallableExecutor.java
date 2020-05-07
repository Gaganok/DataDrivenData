package com.cit.data.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class CallableExecutor {

    @Value("${callable.executor}")
    private int threadPool;
    private ExecutorService executorService;

    private CallableExecutor(){}

    @PostConstruct
    private void postContsturct(){
        executorService = Executors.newFixedThreadPool(threadPool);
    }

    public void call(Callable callable){
        executorService.submit(callable);
    }

    public void call(Runnable runnable){
        executorService.submit(runnable);
    }

}
