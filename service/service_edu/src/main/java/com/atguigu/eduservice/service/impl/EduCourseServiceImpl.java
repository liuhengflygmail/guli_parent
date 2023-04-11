package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduCourseDescription;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.mapper.EduCourseMapper;
import com.atguigu.eduservice.service.EduCourseDescriptionService;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author liuheng
 * @since 2023-04-06
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    // 课程描述的注入
    @Autowired
    private EduCourseDescriptionService courseDescriptionService;
    //添加课程基本信息的方法
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        // 1.向课程表添加课程基本信息
        //CourseInfoVo对象转换成eduCourse对象
        EduCourse eduCourse = new EduCourse();
        // BeanUtils.copyProperties:将courseInfoVo中的值放入eduCourse中
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        // insert表示添加的条数
        int insert = baseMapper.insert(eduCourse);
        if (insert <= 0) {
            // 添加失败
            throw new GuliException(20001,"添加课程信息失败");
        }
        // 获取添加之后的课程id
        String cid = eduCourse.getId();

        // 2.向课程简介表添加课程简介   edu_course_description
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        // 设置描述id就是课程id
        courseDescription.setId(cid);
        courseDescriptionService.save(courseDescription);

        return cid;
    }
}
