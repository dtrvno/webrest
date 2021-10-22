package com.dima.webrest.helloworld;

import com.dima.webrest.helloworld.HelloWorldBean;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {
    //GET
    //URI- /hello-world
    //method - "hello
    @GetMapping(path="/hello-world")
    public String helloWorld() {
        return "Hello world";
    }
    //hello-world-bean
    @GetMapping(path="/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello world bean");
    }
    //hello-world/path-var
    @GetMapping(path="/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathValirable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello world ,%s",name));
    }
}
