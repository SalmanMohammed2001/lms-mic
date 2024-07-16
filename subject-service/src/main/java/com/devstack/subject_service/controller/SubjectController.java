package com.devstack.subject_service.controller;



import com.devstack.subject_service.dto.request.RequestSubject;
import com.devstack.subject_service.service.SubjectService;
import com.devstack.subject_service.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/subjects")

public class SubjectController {

    private final SubjectService subjectService;
    @PostMapping
    private ResponseEntity<StandardResponse> createSubject(@RequestBody RequestSubject requestSubject){
        subjectService.createSubject(requestSubject);
        return  new ResponseEntity<>(
                new StandardResponse(201,"subject was save",requestSubject.getName()), HttpStatus.CREATED
        );
    }
    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    private boolean isListAvailable(@PathVariable List<Long> id){
     return   subjectService.isListAvailable(id);
    }


    @GetMapping(path = "/list")
    private ResponseEntity<StandardResponse> allSubject(){

        return  new ResponseEntity<>(
                new StandardResponse(201,"subject was save",subjectService.findAll()), HttpStatus.CREATED
        );
    }


}
