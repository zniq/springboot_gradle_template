package com.example;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class AppConfig {

	private static final String ENTITY_PACKAGE = "com.example.domain";
	private static final String REPOSITORY_PACKAGE = "com.example.repository";

	@Bean
	@Autowired
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
		sqlSessionFactoryBean.setTypeAliasesPackage(ENTITY_PACKAGE);
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() throws Exception {
		final MapperScannerConfigurer msc = new MapperScannerConfigurer();
		msc.setBasePackage(REPOSITORY_PACKAGE);
		msc.afterPropertiesSet();
		return msc;
	}
	
}
