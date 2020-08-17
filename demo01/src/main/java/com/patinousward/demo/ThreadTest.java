package com.patinousward.demo;

import com.google.common.base.Stopwatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch count  = new CountDownLatch(10000);
        Stopwatch stopWatch = Stopwatch.createStarted();
        ExecutorService executorService = Executors.newCachedThreadPool();
        IntStream.range(0,10000).forEach(i-> executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) { }
            count.countDown();
        }));
        count.await();stopWatch.stop();
        System.out.println("结束了: " + stopWatch.toString());
    }
}
