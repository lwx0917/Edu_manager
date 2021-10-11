package com.lwx.edu.mapper;

import com.lwx.edu.entity.Subject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 课程科目 Mapper 接口
 * </p>
 *
 * @author lwx
 * @since 2021-10-10
 */
@Mapper
public interface SubjectMapper extends BaseMapper<Subject> {

    List<Subject> getParentSubject();
}
