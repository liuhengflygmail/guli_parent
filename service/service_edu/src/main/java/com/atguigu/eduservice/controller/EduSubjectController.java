package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.subject.OneSubject;
import com.atguigu.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author liuheng
 * @since 2023-03-30
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;
    //添加课程分类
    // 获取上传过来的文件,把文件读取出来
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file) {
        //上传过来的Excel文件
        subjectService.saveSubject(file,subjectService);
        return R.ok();
    }
    // 课程分类列表(tree)
    @GetMapping("getAllSubject")
    public R getAllSubject() {
        //list集合中的泛型是一级分类
        List<OneSubject> list =  subjectService.getAllSubject();
        return R.ok().data("list",list);
    }
}

