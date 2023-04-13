package com.example.demo.entiy;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;

//EasyExcl学生表
@TableName(value = "Student")
@Getter
@Setter
public class Student    {
    public Student(){

    }

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String no;

    private String name;

    private String dept;


    private Integer age;

    //此字段表没有
    @TableField(exist = false)
    private Date date;



}