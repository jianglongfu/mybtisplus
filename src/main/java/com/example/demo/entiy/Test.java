package com.example.demo.entiy;


import cn.easyes.annotation.IndexField;
import cn.easyes.annotation.IndexName;
import cn.easyes.common.constants.Analyzer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@IndexName("test")
public class Test {


        private String id;


        private String uid;


        private String lastName;

        @IndexField(value="firstname", analyzer= Analyzer.IK_MAX_WORD)
        private String firstName;


        private String ctiy;

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date date;


}
