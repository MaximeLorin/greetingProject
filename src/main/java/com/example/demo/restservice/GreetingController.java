package com.example.demo.restservice;

import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private final ArrayList<Greeting> list=new ArrayList<Greeting>();

    private final ArrayList<String> lumList=new ArrayList<>();
    private static final String lum="Envoyé: ";

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/greetinglist")
    public ArrayList<Greeting> greetinglist(@RequestParam(value = "name", defaultValue = "World") String name) {
        return list;
    }
    @PostMapping("/greeting")
    public ArrayList<Greeting> greetingPost(@RequestBody Greeting newGreeting) {
        list.add(newGreeting);
        return list;
    }

    @PostMapping("/greetinglum")
    public String greetingLum(@RequestBody String newGreeting) {
        lumList.add(newGreeting+" Créée le: "+ Instant.now());
        return lum+newGreeting;
    }
    @GetMapping("/greetinglumlist")
    public String greetinglist() {
        String returnList="";
        for (String s: lumList) returnList += s + "\n";
        if(returnList.equals("")){
            return "Liste vide";
        }
        return returnList;
    }

}
