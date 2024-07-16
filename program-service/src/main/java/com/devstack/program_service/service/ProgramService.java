package com.devstack.program_service.service;



import com.devstack.program_service.dto.requestDto.RequestProgramDto;
import com.devstack.program_service.dto.responseDto.ResponseProgramDto;

import java.util.List;

public interface ProgramService {


    public void  createProgram(RequestProgramDto programDto);

    public List<ResponseProgramDto>findAllProgram();

    public List<ResponseProgramDto> findAllMyProgram(String email);
}
