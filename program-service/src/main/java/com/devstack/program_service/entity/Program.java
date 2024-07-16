package com.devstack.program_service.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Document(value = "program")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder //
@Setter
public class Program {
    @Id
    private String id;

    private String name;

    private BigDecimal price;


    private Date startData;

    private List<Subject> subjects;
}
