package kg.founders.simplelogisticscrmbff.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GreetingController {

    @GetMapping("/greeting")
    public String getGreeting() {
        return "Привет, всем!";
    }
}