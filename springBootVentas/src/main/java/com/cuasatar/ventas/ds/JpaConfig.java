package com.cuasatar.ventas.ds;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class JpaConfig {
	 
		@Bean(name = "oracleDataSource")
		@Primary
		/*@ConfigurationProperties(prefix = "spring.datasource")*/
		public DataSource dataSource() {
			/*return DataSourceBuilder.create().build();*/
			DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
	        dataSourceBuilder.driverClassName("oracle.jdbc.driver.OracleDriver");
	        dataSourceBuilder.url("jdbc:oracle:thin:@localhost:1522:XE");
	        dataSourceBuilder.username("sventas");
	        dataSourceBuilder.password("Sventas123");
	        return dataSourceBuilder.build();
		}
		
		 @Bean(name = "jdbcTemplate")
		 public JdbcTemplate jdbcTemplate(@Qualifier("oracleDataSource") DataSource ds) {
		  return new JdbcTemplate(ds);
		 }
}

/*https://www.jackrutorial.com/2018/08/spring-boot-with-jasperreports-example.html*/

/*
Autowire primary datasource
@Autowired
DataSource dataSource;
*/

/*
Autowire NON-primary datasource
@Autowired
@Qualifier("h2DataSource") 
DataSource dataSource;
*/
