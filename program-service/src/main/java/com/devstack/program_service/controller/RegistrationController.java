package com.devstack.program_service.controller;


import com.devstack.program_service.dto.requestDto.RequestProgramDto;
import com.devstack.program_service.dto.requestDto.RequestRegistrationDto;
import com.devstack.program_service.service.ProgramService;
import com.devstack.program_service.service.RegistrationService;
import com.devstack.program_service.service.impl.JwtService;
import com.devstack.program_service.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/registrations")
@RequiredArgsConstructor

public class RegistrationController {


    private final RegistrationService registrationService;
    private final JwtService jwtService;

    @PostMapping
    private ResponseEntity<StandardResponse> register(
            @RequestBody RequestRegistrationDto data
            ){

        registrationService.register(data.getProgram(),data.getEmail(),data.getAmount(),data.getData());
        return  new ResponseEntity<>(
                new StandardResponse(201,"registration was save",data.getEmail()), HttpStatus.CREATED
        );
    }



    @GetMapping(path ="/my-list")
    private ResponseEntity<StandardResponse> findAllMyProgram(@RequestHeader("Authorization") String tokenHeader){
       String token= tokenHeader.replace("Bearer ","");
        String email = jwtService.getEmail(token);
        return  new ResponseEntity<>(
                new StandardResponse(200,"All Program", registrationService.findAllMyProgram(email)), HttpStatus.OK
        );
    }

    @GetMapping(path ="/my-payment")
    private ResponseEntity<StandardResponse> findAllMyPayment(@RequestHeader("Authorization") String tokenHeader){
        String token= tokenHeader.replace("Bearer ","");
        String email = jwtService.getEmail(token);
        return  new ResponseEntity<>(
                new StandardResponse(200,"All Program", registrationService.findAllMyPayment(email)), HttpStatus.OK
        );
    }
}
