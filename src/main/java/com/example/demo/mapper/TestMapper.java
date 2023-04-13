package com.example.demo.mapper;

import cn.easyes.core.conditions.interfaces.BaseEsMapper;
import com.example.demo.entiy.Test;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.Mapping;

@Component
public interface TestMapper extends BaseEsMapper<Test> {
}
