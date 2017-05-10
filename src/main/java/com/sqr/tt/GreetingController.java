package com.sqr.tt;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(path = "/greeting", method = RequestMethod.GET)
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        System.out.println("name = [" + name + "]");
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    public static void main(String[] args) {
        for (int i = 1; i < 44; i++) {
            System.out.println(i);
        }
    }
}










































