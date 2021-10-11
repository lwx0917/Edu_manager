package com.lwx.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lwx.edu.entity.Subject;
import com.lwx.edu.entity.vo.ExcelSubject;
import com.lwx.edu.service.SubjectService;

import java.util.Map;

public class ExcelListener extends AnalysisEventListener<ExcelSubject> {

    private final SubjectService subjectService;

    public ExcelListener(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void invoke(ExcelSubject excelSubject, AnalysisContext analysisContext) {
        if (excelSubject == null) {
            try {
                throw new Exception("添加失败");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 添加一级分类
        Subject parentSubject = this.existOneSubject(this.subjectService, excelSubject.getOneSubjectName());
        if (parentSubject == null) {
            Subject subject = new Subject();
            subject.setTitle(excelSubject.getOneSubjectName());
            subject.setParentId("0");
            subjectService.save(subject);
        }

        // 添加二级分类
        String pid = parentSubject.getId();
        Subject childrenSubject = this.existTwoSubject(this.subjectService, excelSubject.getTwoSubjectName(), pid);
        if (childrenSubject == null) {
            Subject subject = new Subject();
            subject.setTitle(excelSubject.getTwoSubjectName());
            subject.setParentId(pid);
            subjectService.save(subject);
        }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {

    }

    private Subject existTwoSubject(SubjectService subjectService, String name, String pid) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", pid);
        return subjectService.getOne(wrapper);
    }

    private Subject existOneSubject(SubjectService subjectService, String name) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", 0);
        return subjectService.getOne(wrapper);
    }

}
