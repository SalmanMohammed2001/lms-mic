package com.devstack.program_service.controller;


import com.devstack.program_service.dto.requestDto.RequestProgramDto;
import com.devstack.program_service.service.ProgramService;
import com.devstack.program_service.service.impl.JwtService;
import com.devstack.program_service.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/programs")

@RequiredArgsConstructor

public class ProgramController {

    private final ProgramService programService;
    private final JwtService jwtService;

    @PostMapping
    public ResponseEntity<StandardResponse> createProgram(@RequestBody RequestProgramDto requestProgramDto){

        programService.createProgram(requestProgramDto);
        return  new ResponseEntity<>(
                new StandardResponse(201,"program was save",requestProgramDto.getName()), HttpStatus.CREATED
        );
    }

    @GetMapping
//    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<StandardResponse> findAllProgram(){
        return  new ResponseEntity<>(
                new StandardResponse(200,"All Program", programService.findAllProgram()), HttpStatus.OK
        );
    }

    @GetMapping(path ="/my-list")
    public ResponseEntity<StandardResponse> findAllMyProgram(@RequestHeader("Authorization") String tokenHeader){
       String token= tokenHeader.replace("Bearer ","");
        String email = jwtService.getEmail(token);
        return  new ResponseEntity<>(
                new StandardResponse(200,"All Program", programService.findAllMyProgram(email)), HttpStatus.OK
        );
    }
}
