package com.lwx.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lwx.edu.entity.Chapter;
import com.lwx.edu.entity.Course;
import com.lwx.edu.entity.Video;
import com.lwx.edu.entity.query.PageQuery;
import com.lwx.edu.entity.vo.*;
import com.lwx.edu.mapper.CourseMapper;
import com.lwx.edu.service.ChapterService;
import com.lwx.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwx.edu.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lwx
 * @since 2021-10-20
 */
@Slf4j
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Resource
    private CourseMapper courseMapper;

    @Resource
    private ChapterService chapterService;

    @Resource
    private VideoService videoService;

    @Override
    public List<HotCourseVo> getHotCourse() {
        return courseMapper.getHotCourse();
    }

    @Override
    public List<ImageVo> getHotImages() {
        return courseMapper.getHotImages();
    }

    @Override
    public List<HotCourseVo> getAllCourse() {
        return courseMapper.getAllCourse();
    }

    @Override
    public HotCourseVo getCourseById(String id) {
        return courseMapper.getCourseById(id);
    }

    @Override
    public List<HotCourseVo> getCourseBySubject(String id) {
        return courseMapper.getCourseBySubject(id);
    }

    @Override
    public CourseList getCourseList(String id) {
        CourseList course = new CourseList();
        List<ChapterList> chs = new ArrayList<>();
        List<Chapter> chapters = chapterService.getChapter(id);
        log.error(chapters.toString());
        for (Chapter chapter : chapters) {
            ChapterList chapterList = new ChapterList();
            BeanUtils.copyProperties(chapter,chapterList);
            List<Video> video = videoService.getVideo(chapter.getId());
            chapterList.setVideos(video);
            chs.add(chapterList);
        }
        course.setChapters(chs);
        return course;
    }

    @Override
    public List<HotCourseVo> getCourseClassify(String id) {
        return courseMapper.getCourseClassify(id);
    }

    @Override
    public IPage<SearchVo> searchCourse(String keyWord, PageQuery pageQuery) {
        QueryWrapper<SearchVo> wrapper = new QueryWrapper<>();
        wrapper.like("c.title",keyWord);
        Page<SearchVo> page = new Page<>(pageQuery.getCurrent(),pageQuery.getSize());
        return courseMapper.selectCourses(page,wrapper);
    }

    @Override
    public List<WordVo> getHotWords() {
        return courseMapper.getHotWords();
    }

}
