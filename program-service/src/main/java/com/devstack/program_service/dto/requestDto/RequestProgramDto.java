package com.devstack.program_service.dto.requestDto;


import com.devstack.program_service.entity.Subject;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RequestProgramDto {

    private String name;

    private BigDecimal price;
//    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd'T'HH:mm:sss.SSSXXX")
    private Date startData;

    private List<Subject> subjects;
}
