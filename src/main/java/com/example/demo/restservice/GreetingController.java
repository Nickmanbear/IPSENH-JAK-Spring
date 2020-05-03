package com.example.demo.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author NAMEHERE
 */
@RestController
public class GreetingController {
    @Autowired
    private GreetingRepository greetingRepository;

    private static final String template = "Hello, %s!";

    @PostMapping(path="/add")
    public @ResponseBody String addNewGreeting (@RequestParam String content) {
        Greeting greeting = new Greeting();
        greeting.setContent(content);
        greetingRepository.save(greeting);
        return "Saved";
    }

    @GetMapping("/greeting")
    public @ResponseBody String getGreetings() {
        // This returns a JSON or XML with the users
        Optional<Greeting> optionalGreeting = greetingRepository.findById(3);
        if (optionalGreeting.isPresent()) {
            Greeting greeting = optionalGreeting.get();
            return String.format(template, greeting.getContent());
        } else {
            return String.format(template, "world");
        }
    }
}
