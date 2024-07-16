package com.devstack.program_service.dto.requestDto;


import com.devstack.program_service.entity.Program;
import com.devstack.program_service.entity.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestRegistrationDto {

    private String email;

    private BigDecimal amount;

    private Date data;

    private Program program;
}
