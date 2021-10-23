package com.dima.webrest.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.hibernate.hql.internal.ast.util.ASTUtil;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {
    @GetMapping("/filtering")
    public SomeBean retrieveSomeBean(){
        return new SomeBean("value1","value2","value3");
    }
    @GetMapping("/filtering-list")
    public MappingJacksonValue retrieveListOfSomeBean(){
        SomeBean someBean=new SomeBean("value1","value2","value3");
        SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter
                .filterOutAllExcept("field2","field3");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("SomeBeanFilter",filter);
        MappingJacksonValue mappingJacksonValue= new MappingJacksonValue(someBean);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }
    @GetMapping("/filtering1")
    public SomeBean retrieveSomeBean1(){
        return new SomeBean("value1","value2","value3");
    }
    @GetMapping("/filtering-list1")
    public MappingJacksonValue retrieveListOfSomeBean1(){
        SomeBean someBean=new SomeBean("value1","value2","value3");
        SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter
                .filterOutAllExcept("field1","field2");
        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("SomeBeanFilter",filter);
        MappingJacksonValue mappingJacksonValue= new MappingJacksonValue(someBean);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }
}
