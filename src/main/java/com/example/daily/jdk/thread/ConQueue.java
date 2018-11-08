package com.example.daily.jdk.thread;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @Project: daily
 * @Auther: zhiyuan
 * @Date: 2018/11/2 16:19
 * @Description:
 */
public class ConQueue {
    @Test
    public void unboundedQueue() {
        SynchronousQueue synchronousQueue = new SynchronousQueue(true);
        ConcurrentLinkedQueue<String> clq = new ConcurrentLinkedQueue() {{
            add("aaaa");
            add("bbbb");
            add("cccc");
            add("dddd");
        }};
        String peek = clq.peek();
        System.out.printf("%s\r\n", peek);
    }

    @Test
    public void blockingQueue() throws InterruptedException{
        BlockingQueue<String> clq = new LinkedBlockingDeque(3) {{
            add("aaaa");
            add("bbbb");
        }};
        String peek = clq.peek();
        clq.offer("erf",3000, TimeUnit.MILLISECONDS);
        System.out.printf("%s\r\n", peek);
    }
}
