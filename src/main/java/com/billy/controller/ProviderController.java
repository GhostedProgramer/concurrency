package com.billy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/provider")
public class ProviderController {

    Logger logger = LoggerFactory.getLogger(ProviderController.class);

    @PostMapping("/ecif")
    public Map<String, String> ecif(@RequestBody Map<String, String> body) {
//        logger.info("ecif provider");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new HashMap<String, String>() {
            {
                put("ecif1", "value1");
                put("ecif2", "value2");
            }
        };
    }

    @PostMapping("/elif")
    public Map<String, String> elif(@RequestBody Map<String, String> body) {
//        logger.info("elif provider");
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new HashMap<String, String>() {
            {
                put("elif1", "value1");
                put("elif2", "value2");
            }
        };
    }

    @PostMapping("/caml")
    public Map<String, String> caml(@RequestBody Map<String, String> body) {
//        logger.info("caml provider");
        try {
            Thread.sleep(120);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new HashMap<String, String>() {
            {
                put("caml1", "value1");
                put("caml2", "value2");
            }
        };
    }

    @PostMapping("/iass")
    public Map<String, String> iass(@RequestBody Map<String, String> body) {
//        logger.info("iass provider");
        try {
            Thread.sleep(111);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new HashMap<String, String>() {
            {
                put("iass1", "value1");
                put("iass2", "value2");
            }
        };
    }

    @PostMapping("/jeen")
    public Map<String, String> jeen(@RequestBody Map<String, String> body) {
//        logger.info("jeen provider");
        try {
            Thread.sleep(137);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new HashMap<String, String>() {
            {
                put("jeen1", "value1");
                put("jeen2", "value2");
            }
        };
    }

    @PostMapping("/saas")
    public Map<String, String> saas(@RequestBody Map<String, String> body) {
//        logger.info("saas provider");
        try {
            Thread.sleep(122);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new HashMap<String, String>() {
            {
                put("saas1", "value1");
                put("saas2", "value2");
            }
        };
    }

    @PostMapping("/engine")
    public Map<String, String> engine(@RequestBody Map<String, String> body) {
//        logger.info("engine provider");
//        logger.info("body is " + body.toString());
        try {
            Thread.sleep(130);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new HashMap<String, String>() {
            {
                put("engine1", "value1");
                put("engine2", "value2");
            }
        };
    }

}
