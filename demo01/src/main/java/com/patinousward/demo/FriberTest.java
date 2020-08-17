package com.patinousward.demo;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.Strand;
import com.google.common.base.Stopwatch;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class FriberTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch count  = new CountDownLatch(10000);
        Stopwatch stopWatch = Stopwatch.createStarted();
        IntStream.range(0,10000).forEach(i-> new Fiber() {
            @Override
            protected String run() throws SuspendExecution, InterruptedException {
                Strand.sleep(1000 );
                count.countDown();
                return  "aa";
            }
        }.start());
        count.await();stopWatch.stop();
        System.out.println("结束了: " + stopWatch.toString());
    }
}
