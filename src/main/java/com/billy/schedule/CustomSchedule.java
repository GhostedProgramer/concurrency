package com.billy.schedule;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

@Component
public class CustomSchedule extends Schedule {
    private final String ELIF = "elif";
    private final String CAML = "caml";
    private final String IASS = "iass";
    private final String JEEN = "jeen";
    private final String SAAS = "saas";

    Logger logger = LoggerFactory.getLogger(CustomSchedule.class);

    @Override
    Map<String, String> forOuterService(Map<String, String> paramMap) {
        Map<String, String> urlMaps = config.getUrlMaps();
        CountDownLatch latch = new CountDownLatch(urlMaps.size());
        String elifUrl = urlMaps.get(ELIF);
        Callable<Map<String, String>> elif = new ScheduleElement(elifUrl, paramMap, latch);
        String camlUrl = urlMaps.get(CAML);
        Callable<Map<String, String>> caml = new ScheduleElement(camlUrl, paramMap, latch);
        String iassUrl = urlMaps.get(IASS);
        Callable<Map<String, String>> iass = new ScheduleElement(iassUrl, paramMap, latch);
        String jeenUrl = urlMaps.get(JEEN);
        Callable<Map<String, String>> jeen = new ScheduleElement(jeenUrl, paramMap, latch);
        String saasUrl = urlMaps.get(SAAS);
        Callable<Map<String, String>> saas = new ScheduleElement(saasUrl, paramMap, latch);
        Future<Map<String, String>> elifFuture = threadPool.submit(elif);
        Future<Map<String, String>> camlFuture = threadPool.submit(caml);
        Future<Map<String, String>> iassFuture = threadPool.submit(iass);
        Future<Map<String, String>> jeenFuture = threadPool.submit(jeen);
        Future<Map<String, String>> saasFuture = threadPool.submit(saas);
        Map<String, String> resultMap = new HashMap<>();
        try {
            latch.await();
            Map<String, String> elifResult = elifFuture.get();
            Map<String, String> camlResult = camlFuture.get();
            Map<String, String> iassResult = iassFuture.get();
            Map<String, String> jeenResult = jeenFuture.get();
            Map<String, String> saasResult = saasFuture.get();
            resultMap.putAll(elifResult);
            resultMap.putAll(camlResult);
            resultMap.putAll(iassResult);
            resultMap.putAll(jeenResult);
            resultMap.putAll(saasResult);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return resultMap;
    }
}
