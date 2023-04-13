package com.example.demo.config.datasource;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.example.demo.mapper"}, sqlSessionTemplateRef = "dsSqlSessionTemplate")
public class Ds1DatasourceConfiguration {

        @Bean(name = "dsDatasource")
        @Primary
        @ConfigurationProperties(prefix = "spring.datasource.ds1")
        public DataSource driverDruidDatasource() {

            return DataSourceBuilder.create().build();
            //return xaDataSource;
        }

        //配置数据源
        @Bean(name = "dsSessionFactory")
        @Primary
        public MybatisSqlSessionFactoryBean dsSessionFactory(@Qualifier("dsDatasource") DataSource dataSource) throws Exception {
            MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean=new MybatisSqlSessionFactoryBean();
            mybatisSqlSessionFactoryBean.setDataSource(dataSource);
            mybatisSqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:/mapper/*.xml"));
            return mybatisSqlSessionFactoryBean;
        }
        // 事务管理器
        @Bean(name = "dsTransactionManger")
        @Primary
        public DataSourceTransactionManager driverTransactionManger(@Qualifier("dsDatasource") DataSource dataSource) {
            return new DataSourceTransactionManager(dataSource);
        }

        @Bean(name = "dsSqlSessionTemplate")
        @Primary
        public SqlSessionTemplate dsSessionTemplate(@Qualifier("dsSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
            return new SqlSessionTemplate(sqlSessionFactory);
        }



}
