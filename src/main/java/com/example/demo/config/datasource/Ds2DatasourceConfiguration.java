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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.example.demo.testmapper"}, sqlSessionTemplateRef = "ds2SqlSessionTemplate")
public class Ds2DatasourceConfiguration {

    @Autowired
    private Ds2DatasourceProperties ds2DatasourceProperties;

    @Bean(name = "ds2Datasource")
    public DataSource driverDruidDatasource() {
        DruidXADataSource xaDataSource=new DruidXADataSource();
        BeanUtils.copyProperties(ds2DatasourceProperties,xaDataSource);
        AtomikosDataSourceBean xtdataSource=new AtomikosDataSourceBean();
        xtdataSource.setXaDataSource(xaDataSource);
        xtdataSource.setUniqueResourceName("ds2Datasource");
        return xtdataSource;
    }

    //配置数据源
    @Bean(name = "ds2SessionFactory")
    public MybatisSqlSessionFactoryBean dsSessionFactory(@Qualifier("ds2Datasource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean=new MybatisSqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setDataSource(dataSource);
        mybatisSqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:/testmapper/*.xml"));
        return mybatisSqlSessionFactoryBean;
    }
    // 事务管理器
//    @Bean(name = "ds2TransactionManger")
//    public DataSourceTransactionManager driverTransactionManger(@Qualifier("ds2Datasource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }

    @Bean(name = "ds2SqlSessionTemplate")
    public SqlSessionTemplate dsSessionTemplate(@Qualifier("ds2SessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }



}
