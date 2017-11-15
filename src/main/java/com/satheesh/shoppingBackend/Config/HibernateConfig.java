package com.satheesh.shoppingBackend.Config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {"com.satheesh.shoppingBackend.domain"})
@EnableTransactionManagement
public class HibernateConfig {
	
	private final static String DATABASE_URL = "jdbc:mysql://satheesh-database.c3wl8cxgbntx.us-east-2.rds.amazonaws.com/ecommerce";
	private final static String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
	private final static String DATABASE_DIALECT = "org.hibernate.dialect.MySQL5Dialect";
	private final static String DATABASE_USERNAME ="satheesh";
	private final static String DATABASE_PASSWORD = "Sakhi4us";

	// dataSource bean will be available
		@Bean("dataSource")
		public DataSource getDataSource() {
			
			BasicDataSource dataSource = new BasicDataSource();
			
			// Providing the database connection information
			dataSource.setDriverClassName(DATABASE_DRIVER);
			dataSource.setUrl(DATABASE_URL);
			dataSource.setUsername(DATABASE_USERNAME);
			dataSource.setPassword(DATABASE_PASSWORD);
					
			
			return dataSource;
			
		}
		
		// sessionFactory bean will be available
		
		@Bean
		public SessionFactory getSessionFactory(DataSource dataSource) {
			
			LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);
			
			builder.addProperties(getHibernateProperties());
			builder.scanPackages("com.satheesh.shoppingBackend.domain");
			
			return builder.buildSessionFactory();
			
		}

		
		
		// All the hibernate properties will be returned in this method	
		private Properties getHibernateProperties() {
			
			Properties properties = new Properties();
			
			
			properties.put("hibernate.dialect", DATABASE_DIALECT);	
			properties.put("hibernate.hbm2ddl.auto", "update");
			properties.put("hibernate.show_sql", "true");
			properties.put("hibernate.format_sql", "true");
			
			//properties.put("hibernate.hbm2ddl.auto", "create");
			
			
			return properties;
		}
		
		// transactionManager bean
		@Bean
		public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
			HibernateTransactionManager transactionManager = new HibernateTransactionManager();
			transactionManager.setSessionFactory(sessionFactory);
			return transactionManager;
		}
		
		
	}
