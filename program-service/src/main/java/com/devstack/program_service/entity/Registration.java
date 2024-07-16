package com.devstack.program_service.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Document(value = "registration")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder //
@Setter
@ToString
public class Registration {
    @Id
    private String id;
    private String email;
    private Program program;
    private BigDecimal amount;
    private Date date;


}
