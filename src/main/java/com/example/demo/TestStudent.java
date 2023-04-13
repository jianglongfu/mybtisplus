package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entiy.Student;
import com.example.demo.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestStudent {

    @Autowired
    private StudentService stuService;




    @Test
    public void  test(){
        short i=1;
        i+=1;
        Student s=new Student();
        s.setAge(23);
        s.setName("大");
        stuService.save(s);
        //   stuService.update(s,new UpdateWrapper<Student>().eq("name","小"));
        //  stuService.remove(new QueryWrapper<Student>().eq("name","大"));
       // List<Student> studentList=stuService.list(new QueryWrapper<Student>());
       // return studentList;
    }
}
