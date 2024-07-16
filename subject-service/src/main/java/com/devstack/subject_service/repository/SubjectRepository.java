package com.devstack.subject_service.repository;


import com.devstack.subject_service.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject,Long> {

    List<Subject> findByIdIn(List<Long> ids);
}
