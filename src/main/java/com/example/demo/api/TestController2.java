package com.example.demo.api;

import com.example.demo.entiy.Test;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"员工相关接口"})
@RestController
@RequestMapping("/enp")
public class TestController2 {


    @ApiImplicitParams({@ApiImplicitParam(name = "param", value = "员工信息")})
    @ApiOperation(value = "新增员工", notes = "新增员工信息")
    @PostMapping("/save")
    public Test saveEmploye(@RequestBody Test param) {
        return null;
    }

}
