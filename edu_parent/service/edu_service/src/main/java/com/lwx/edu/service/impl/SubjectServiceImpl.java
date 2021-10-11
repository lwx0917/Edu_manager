package com.lwx.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lwx.edu.entity.Subject;
import com.lwx.edu.entity.vo.ExcelSubject;
import com.lwx.edu.entity.vo.SubjectNestedVo;
import com.lwx.edu.entity.vo.SubjectVo;
import com.lwx.edu.listener.ExcelListener;
import com.lwx.edu.mapper.SubjectMapper;
import com.lwx.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author lwx
 * @since 2021-10-10
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Autowired
    private SubjectMapper subjectMapper;

    @Override
    public void importSubjects(MultipartFile file, SubjectService subjectService) {
        InputStream stream = null;
        try {
            stream = file.getInputStream();
            EasyExcel.read(stream, ExcelSubject.class, new ExcelListener(subjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Subject> getParentSubject() {
        return subjectMapper.getParentSubject();
    }

    @Override
    public List<SubjectNestedVo> getSubjects() {
        ArrayList<SubjectNestedVo> list = new ArrayList<>();
        // 获取所有一级分类
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", 0);
        wrapper.orderByAsc("sort", "id");
        List<Subject> subjects = baseMapper.selectList(wrapper);

        // 获取所有二级分类
        QueryWrapper<Subject> wrapper2 = new QueryWrapper<>();
        wrapper2.ne("parent_id", 0);
        wrapper.orderByAsc("sort", "id");
        List<Subject> childrenSubjects = baseMapper.selectList(wrapper2);

        int count = subjects.size();
        for (int i = 0; i < count; i++) {
            // 添加一级分类
            Subject subject = subjects.get(i);
            SubjectNestedVo subjectNestedVo = new SubjectNestedVo();
            BeanUtils.copyProperties(subject, subjectNestedVo);
            list.add(subjectNestedVo);

            ArrayList<SubjectVo> subjectVos = new ArrayList<>();
            int count2 = childrenSubjects.size();
            for (int j = 0; j < count2; j++) {
                Subject sub = childrenSubjects.get(j);
                // 判断二级分类所属
                if (subject.getId().equals(sub.getParentId())) {
                    // 添加二级分类
                    SubjectVo subjectVo = new SubjectVo();
                    BeanUtils.copyProperties(sub, subjectVo);
                    subjectVos.add(subjectVo);
                }
            }
            // 将二级分类加到一级分类中
            subjectNestedVo.setChildren(subjectVos);
        }
        return list;
    }
}
