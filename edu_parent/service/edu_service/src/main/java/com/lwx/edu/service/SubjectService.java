package com.lwx.edu.service;

import com.lwx.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lwx.edu.entity.vo.SubjectNestedVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author lwx
 * @since 2021-10-10
 */
public interface SubjectService extends IService<Subject> {

    void importSubjects(MultipartFile file, SubjectService subjectService);

    List<Subject> getParentSubject();

    List<SubjectNestedVo> getSubjects();
}
