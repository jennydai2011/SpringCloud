package com.jdai.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
class ClientApplicationTests {

    RestTemplate restTemplate = new RestTemplate();
    private static CountDownLatch cd1 = new CountDownLatch(200);
    @Test
    void testInvoke() throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(200);
        List<Callable<String>> tasks = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            tasks.add( new Callable() {
                @Override
                public String call() throws Exception {
                    return task();
                }
            });
        }
        executorService.invokeAll(tasks);
        executorService.shutdown();

    }

    private String task() {
       return restTemplate.getForObject("http://127.0.0.1:8080/ticket", String.class);
    }

}
