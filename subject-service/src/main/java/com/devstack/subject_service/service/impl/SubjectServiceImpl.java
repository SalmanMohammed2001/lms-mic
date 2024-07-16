package com.devstack.subject_service.service.impl;


import com.devstack.subject_service.dto.request.RequestSubject;
import com.devstack.subject_service.dto.response.ResponseSubjectDto;
import com.devstack.subject_service.entity.Subject;
import com.devstack.subject_service.repository.SubjectRepository;
import com.devstack.subject_service.service.SubjectService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {

    public final SubjectRepository subjectRepository;

    @Override
    public void createSubject(RequestSubject requestSubject) {
        Subject subject = Subject.builder().name(requestSubject.getName()).status(requestSubject.isStatus()).build();
        subjectRepository.save(subject);

    }

    public boolean isListAvailable(List<Long> ids) {
        for (Subject subject : subjectRepository.findByIdIn(ids)) {
            if (!subject.isStatus()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<ResponseSubjectDto> findAll() {
        List<Subject> all = subjectRepository.findAll();
        List<ResponseSubjectDto> responseSubjectDtoList=new ArrayList<>();
        for (Subject sub:all) {
            responseSubjectDtoList.add(new ResponseSubjectDto(sub.getId(),sub.getName(),sub.isStatus()));
        }
        return  responseSubjectDtoList;
    }
}
