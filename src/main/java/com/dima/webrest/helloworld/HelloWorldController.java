package com.dima.webrest.helloworld;

import com.dima.webrest.helloworld.HelloWorldBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class HelloWorldController {
    //GET
    //URI- /hello-world
    //method - "hello
    @Autowired
    private MessageSource messageSource;
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
    @GetMapping(path="/hello-world-internationalized")
    public String helloWorldInternationalized(@RequestHeader(name="Accept-Language",required = false)
                                              Locale locale) {
        return messageSource.getMessage("good.morning.message",
                null,"Default Message",locale);
    }
    @GetMapping(path="/hello-world-internationalized1")
    public String helloWorldInternationalized1() {
        return messageSource.getMessage("good.morning.message",
                null,"Default Message", LocaleContextHolder.getLocale());
    }
}
