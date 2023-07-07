package com.care.dbJava.config;

import java.io.IOException;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/*
<mybatis-spring:scan base-package="com.care.dbXml.repository"/>
*/
@MapperScan(basePackages = {"com.care.dbJava.repository"})
@Configuration
public class DataBaseConfig {
/*
<bean class="com.zaxxer.hikari.HikariConfig" id="hikariConfig">
		<property name="driverClassName" value="oracle.jdbc.OracleDriver"/>
		<property name="jdbcUrl" value="jdbc:oracle:thin:@localhost:1521:xe" />
		<property name="username" value="oracle"/>
		<property name="password" value="oracle"/>
</bean>
<bean class="com.zaxxer.hikari.HikariDataSource" id="dataSource">
		<constructor-arg ref="hikariConfig"/>
</bean>
*/
	@Bean
	public HikariDataSource dataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName("oracle.jdbc.OracleDriver");
		hikariConfig.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
		hikariConfig.setUsername("oracle");
		hikariConfig.setPassword("oracle");
		
		HikariDataSource dataSource = new HikariDataSource(hikariConfig);
		return dataSource;
	}
/*
	<bean id="sessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
		<property name="dataSource" ref="dataSource"/>
		<property name="mapperLocations" value="classpath:/mappers/*Mapper.xml"/>
	</bean>
*/
	@Bean
	public SqlSessionFactoryBean sessionFactory() throws IOException {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		
		PathMatchingResourcePatternResolver resolver = 
				new PathMatchingResourcePatternResolver();
		sessionFactory.setMapperLocations(resolver.getResources("classpath:/mappers/*Mapper.xml"));
		
		return sessionFactory;
	}

}



