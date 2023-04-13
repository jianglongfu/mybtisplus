package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entiy.Order;
import com.example.demo.entiy.Student;
import com.example.demo.mapper.StudentDao;
import com.example.demo.service.OrderService;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentDao, Student> implements StudentService {

    @Autowired
    private OrderService orderService;

    @Override
    @Transactional
    public void ceTranstanion() {
        Student student=new Student();
        student.setAge(23);
        student.setName("大");
        this.save(student);
        Order order=new Order();
        order.setOrderName("测试");
        order.setOrderPrice(new BigDecimal(50));
        orderService.save(order);
        int i= 1/0;
    }
}
