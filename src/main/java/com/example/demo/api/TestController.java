package com.example.demo.api;

import cn.easyes.common.constants.Analyzer;
import cn.easyes.common.enums.FieldType;
import cn.easyes.core.conditions.LambdaEsIndexWrapper;
import cn.easyes.core.conditions.LambdaEsQueryWrapper;
import com.example.demo.entiy.Test;
import com.example.demo.mapper.TestMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Api(tags = "这是一个控制器类")
//2.在具体请求方法上的注解:
@ApiOperation(value = "功能总述" , notes = "具体描述")
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestController {

    @Autowired
    private TestMapper testMapper;

    @GetMapping("/hello")
    @ResponseBody
    @ApiOperation(value = "hello", notes = "hello")
    public String hello(){
        return "hello world";
    }


    @GetMapping("/createIndex")
    public Object createIndex(){
        HashMap  hashMap=new HashMap();

        LambdaEsIndexWrapper<Test> wrapper = new LambdaEsIndexWrapper<>();
        // 此处简单起见 索引名称须保持和实体类名称一致,字母小写 后面章节会教大家更如何灵活配置和使用索引
        wrapper.indexName("test");

        // 此处将文章标题映射为keyword类型(不支持分词),文档内容映射为text类型,可缺省
        // 支持分词查询,内容分词器可指定,查询分词器也可指定,,均可缺省或只指定其中之一,不指定则为ES默认分词器(standard)
        wrapper.mapping(Test::getFirstName, FieldType.KEYWORD, Analyzer.IK_MAX_WORD);
        // 设置分片及副本信息,3个shards,2个replicas,可缺省
        wrapper.settings(3,2);

        // 设置别名信息,可缺省
        String aliasName = "daily";
        wrapper.createAlias(aliasName);

        // 创建索引
        return testMapper.createIndex(wrapper);
    }

    @GetMapping("/getIndex")
    public Object getIndex(){
        GetIndexResponse getIndexResponse =testMapper.getIndex() ;
        return testMapper.getIndex();
    }


    @GetMapping("/add")
    public Object add(Test test){
        Integer i=  testMapper.insert(test);
        return i;
    }

    @GetMapping("/list")
    public Object userList(Test test) {
        LambdaEsQueryWrapper<Test> wrapper = new LambdaEsQueryWrapper<>();
        //排序 年龄降序
        wrapper.orderByDesc(Test::getDate);
        if (test.getFirstName()!=null){
            wrapper.like(Test::getFirstName, test.getFirstName());

        }


        //返回值
        return testMapper.selectList(wrapper);
    }


}
