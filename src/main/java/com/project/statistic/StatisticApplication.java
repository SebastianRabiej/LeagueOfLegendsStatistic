package com.project.statistic;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.vavr.jackson.datatype.VavrModule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class StatisticApplication {

    public static void main(String[] args) {
        SpringApplication.run(StatisticApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, mappingJacksonHttpMessageConverter);
        return restTemplate;
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter(ObjectMapper objectMaper) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMaper);
        return converter;
    }

    @Bean
    public ObjectMapper jacksonBuilder() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.registerModule(new VavrModule());
    }
}
