
package com.dtcc.stockexchange.config;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * ExchangeAppConfig.java - this class configure all the necessary beans required for the application
 * @author Vinoth
 *
 */
@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@EnableTransactionManagement
@MapperScan(basePackages="com.dtcc.stockexchange.dao")
@ComponentScan(basePackages = "com.dtcc.stockexchange.*")
public class ExchangeAppConfig {
	
	@Bean
	public DataSource dataSource() {
		JndiObjectFactoryBean dataSource = new JndiObjectFactoryBean();
		dataSource.setJndiName("java:comp/env/jdbc/db");
		try {
			dataSource.afterPropertiesSet();
		} catch (IllegalArgumentException | NamingException e) {
			throw new RuntimeException(e);
		}
		return (DataSource) dataSource.getObject();
	}
	
	@Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        return sessionFactory;
    }
}
