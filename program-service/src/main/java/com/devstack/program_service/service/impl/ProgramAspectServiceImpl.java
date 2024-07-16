package com.devstack.program_service.service.impl;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
@RequiredArgsConstructor
public class ProgramAspectServiceImpl {

    private final WebClient webClient;

    int count;

    @CircuitBreaker(name = "subjectService",fallbackMethod = "fallbackMethod")
    public  Boolean checkSubject(String ids){
        System.out.println("count "+count++ );
        return webClient.get().uri("http://localhost:8082/api/v1/subjects/{id}",ids)
                .retrieve().bodyToMono(Boolean.class).block();
    }

    // subjectService method request falls return fallbackMethod false

    public Boolean fallbackMethod(String ids,Exception e){
        System.out.println("Failed");
        System.out.println("count : "+count);
        return  false;
    }
}
