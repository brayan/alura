package br.com.casadocodigo.loja.conf;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;

@Profile("prod")
public class JpaProductionConfiguration {

	@Autowired
	private Environment environment;
	
	@Bean
	public DriverManagerDataSource dataSource() throws URISyntaxException {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		// usuario:senha@host:port/path
		URI uri = new URI(environment.getProperty("DATABASE_URL"));
		
		dataSource.setUrl("jdbc:postgresql://" + uri.getHost() + ":" + uri.getPort() + uri.getPath());
		dataSource.setUsername(uri.getUserInfo().split(":")[0]);
		dataSource.setPassword(uri.getUserInfo().split(":")[1]);
		
		return dataSource;
	}

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}
	
	@Bean
	public Properties additionalProperties() {
		Properties props = new Properties();
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		props.setProperty("hibernate.show_sql", "true");
		props.setProperty("hibernate.hbm2ddl.auto", "update");
		return props;
	}
}
