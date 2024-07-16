package com.devstack.program_service.service;



import com.devstack.program_service.dto.requestDto.RequestProgramDto;
import com.devstack.program_service.dto.responseDto.ResponsePaymentDto;
import com.devstack.program_service.dto.responseDto.ResponseProgramDto;
import com.devstack.program_service.entity.Program;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public interface RegistrationService {


    public void register(Program program, String email, BigDecimal amount, Date date);



    public List<ResponseProgramDto> findAllMyProgram(String email);

    List<ResponsePaymentDto> findAllMyPayment(String email);
}
