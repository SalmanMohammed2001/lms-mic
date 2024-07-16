package com.devstack.program_service.repo;


import com.devstack.program_service.entity.Program;
import com.devstack.program_service.entity.Registration;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProgramRepository extends MongoRepository<Program,String> {


}
