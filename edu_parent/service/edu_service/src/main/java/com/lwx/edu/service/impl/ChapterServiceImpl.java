package com.lwx.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lwx.edu.entity.Chapter;
import com.lwx.edu.entity.Video;
import com.lwx.edu.entity.vo.ChapterVo;
import com.lwx.edu.entity.vo.VideoVo;
import com.lwx.edu.mapper.ChapterMapper;
import com.lwx.edu.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lwx.edu.service.VideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lwx
 * @since 2021-10-13
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    private VideoService videoService;

    @Override
    public List<ChapterVo> nestedList(String id) {
        ArrayList<ChapterVo> chapters = new ArrayList<>();

        // 获取章节
        QueryWrapper<Chapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", id);
        wrapper.orderByAsc("sort", "id");
        List<Chapter> chapterList = baseMapper.selectList(wrapper);

        // 获取课时
        QueryWrapper<Video> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("course_id", id);
        wrapper2.orderByAsc("sort", "id");
        List<Video> videoList = videoService.list(wrapper2);

        // 填充
        int count = chapterList.size();
        for (int i = 0; i < count; i++) {
            Chapter chapter = chapterList.get(i);
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);
            chapters.add(chapterVo);

            List<VideoVo> videos = new ArrayList<>();
            int num = videoList.size();
            for (int j = 0; j < num; j++) {
                Video video = videoList.get(j);
                if (chapter.getId().equals(video.getChapterId())) {
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video, videoVo);
                    videos.add(videoVo);
                }
            }
            chapterVo.setChildren(videos);
        }

        return chapters;
    }

    @Override
    public void removeChapterById(String id) throws Exception {
        if (videoService.getCountByChapterId(id)) {
            throw new Exception("该章节下存在小节数据，无法删除");
        }
        baseMapper.deleteById(id);
    }
}
