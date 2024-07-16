package com.devstack.subject_service.service;



import com.devstack.subject_service.dto.request.RequestSubject;
import com.devstack.subject_service.dto.response.ResponseSubjectDto;

import java.util.List;

public interface SubjectService {

    public  void  createSubject(RequestSubject requestSubject);
    public boolean isListAvailable(List<Long> ids);

    public List<ResponseSubjectDto> findAll();
}
