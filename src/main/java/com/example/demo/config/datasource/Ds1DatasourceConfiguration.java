package com.example.demo.config.datasource;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import javax.sql.XADataSource;

@Configuration
@MapperScan(basePackages = {"com.example.demo.mapper"}, sqlSessionTemplateRef = "dsSqlSessionTemplate")
public class Ds1DatasourceConfiguration {

    @Autowired
    private Ds1DatasourceProperties ds1DatasourceProperties;


        @Bean(name = "dsDatasource")
        @Primary
        public DataSource driverDruidDatasource() {
            DruidXADataSource xaDataSource=new DruidXADataSource();
            BeanUtils.copyProperties(ds1DatasourceProperties,xaDataSource);
            AtomikosDataSourceBean xtdataSource=new AtomikosDataSourceBean();
            xtdataSource.setXaDataSource(xaDataSource);
            xtdataSource.setUniqueResourceName("dsDatasource");
            return xtdataSource;
        }

        //配置数据源 mybatisplus 使用 MybatisSqlSessionFactoryBean
        @Bean(name = "dsSessionFactory")
        @Primary
        public MybatisSqlSessionFactoryBean dsSessionFactory(@Qualifier("dsDatasource") DataSource dataSource) throws Exception {
            MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean=new MybatisSqlSessionFactoryBean();
            mybatisSqlSessionFactoryBean.setDataSource(dataSource);
            mybatisSqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:/mapper/*.xml"));
            return mybatisSqlSessionFactoryBean;
        }
        // 事务管理器
//        @Bean(name = "dsTransactionManger")
//        @Primary
//        public DataSourceTransactionManager driverTransactionManger(@Qualifier("dsDatasource") DataSource dataSource) {
//            return new DataSourceTransactionManager(dataSource);
//        }

        @Bean(name = "dsSqlSessionTemplate")
        @Primary
        public SqlSessionTemplate dsSessionTemplate(@Qualifier("dsSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
            return new SqlSessionTemplate(sqlSessionFactory);
        }



}
