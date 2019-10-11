package com.ecse321.group3.tutorME.domain;

import com.ecse321.group3.tutorME.repository.LessonRepository;
import com.ecse321.group3.tutorME.utils.TestSuiteUtils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import org.springframework.transaction.annotation.Transactional;
@SpringBootTest
@RunWith(SpringRunner.class)
public class LessonTest {

    @Autowired
    private LessonRepository lessonRepo;
    @Autowired
    private TestSuiteUtils testUtils;
    
    @Before
    public void init(){
        testUtils.truncateDatabase();
    }

    @Test
    @Transactional
    public void createLesson(){
        Lesson lesson = new Lesson();

        try{
            lessonRepo.save(lesson);
        } catch(Exception e){
            Assert.fail(e.getMessage());
        }
    }

    @Test
    @Transactional
    public void readLesson(){
        createLesson();
        Assert.assertEquals(1, lessonRepo.findAll().size());
    }
}