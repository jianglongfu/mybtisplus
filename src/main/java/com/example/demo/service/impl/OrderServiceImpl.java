package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entiy.Order;

import com.example.demo.service.OrderService;
import com.example.demo.service.StudentService;
import com.example.demo.testmapper.OrderMapper;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl  extends ServiceImpl<OrderMapper, Order> implements OrderService {
}
