package com.garden.demo.hello;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.ServerResponse;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/")
public class HelloController {
    @GetMapping(value = "hello" )
    public Mono<String> helloCheck() {
        return Mono.just("Hello World!!");
    }
}
