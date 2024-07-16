package com.devstack.program_service.service.impl;

import com.devstack.program_service.dto.responseDto.ResponsePaymentDto;
import com.devstack.program_service.dto.responseDto.ResponseProgramDto;
import com.devstack.program_service.entity.Program;
import com.devstack.program_service.entity.Registration;
import com.devstack.program_service.entity.Subject;
import com.devstack.program_service.repo.RegistrationRepository;
import com.devstack.program_service.service.RegistrationService;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.Date;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final RegistrationRepository registrationRepository;

    public RegistrationServiceImpl(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @Override
    public void register(Program program, String email, BigDecimal amount, Date date) {
        Registration registration = Registration.builder().
                program(program)
                .email(email)
                .amount(amount)
                .date(date)
                .build();


            registrationRepository.save(registration);

    }

    @Override
    public List<ResponseProgramDto> findAllMyProgram(String email) {

        List<Registration> registration = registrationRepository.findByEmail(email);
        System.out.println(registration);
        return registration.stream().map(this::mapToResponseProgramDto).collect(Collectors.toList());

    }

    @Override
    public List<ResponsePaymentDto> findAllMyPayment(String email) {
        List<Registration> registration = registrationRepository.findByEmail(email);
        System.out.println(registration);
        return registration.stream().map(this::mapToResponsePaymentDto).collect(Collectors.toList());
    }

    private ResponsePaymentDto mapToResponsePaymentDto(Registration registration){
        return ResponsePaymentDto.builder()
                .transactionId(registration.getId())
                .date(registration.getProgram().getStartData())
                .amount(registration.getAmount())
                .programName(registration.getProgram().getName())

                .build();
    }
    private ResponseProgramDto mapToResponseProgramDto(Registration registration){
        return ResponseProgramDto.builder()
                .id(registration.getProgram().getId())
                .name(registration.getProgram().getName())
                .price(registration.getProgram().getPrice())
                .startData(registration.getProgram().getStartData())
                .subjects(registration.getProgram().getSubjects())
                .build();
    }
}
