package com.bdev.hogwarts_api.config;

import com.bdev.hogwarts_api.properties.PropertiesConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

import static com.bdev.hogwarts_api.properties.PropertiesConfig.config;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.bdev.hogwarts_api.dao")
public class DatabaseConfig {
    @Bean
    DataSource dataSource() {
        HikariConfig dataSourceConfig = new HikariConfig(); {
            dataSourceConfig.setPoolName("Hogwarts");
            dataSourceConfig.setRegisterMbeans(true);
            dataSourceConfig.setDriverClassName(config.getDatabaseDriver());
            dataSourceConfig.setJdbcUrl(config.getDatabaseUrl());
            dataSourceConfig.setUsername(config.getDatabaseUsername());
            dataSourceConfig.setPassword(config.getDatabasePassword());
            dataSourceConfig.setMaximumPoolSize(10);
            dataSourceConfig.setMaxLifetime(60000);
        }

        return new HikariDataSource(dataSourceConfig);
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean =
                new LocalContainerEntityManagerFactoryBean();

        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("com.bdev.hogwarts_api.data.model");

        Properties jpaProperties = new Properties();

        jpaProperties.put("hibernate.dialect", config.getDatabaseDialect());
        jpaProperties.put("hibernate.hbm2ddl.auto", config.getDatabaseStartupAction());
        jpaProperties.put("hibernate.show_sql", config.getDatabaseShowSql());
        jpaProperties.put("hibernate.format_sql", config.getDatabaseFormatSql());
        jpaProperties.put("hibernate.id.new_generator_mappings", config.getDatabaseIdNewGeneratorMappings());
        jpaProperties.put("hibernate.connection.release_mode", config.getDatabaseConnectionReleaseMode());

        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        return entityManagerFactoryBean;
    }

    @Bean
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();

        transactionManager.setEntityManagerFactory(entityManagerFactory);

        return transactionManager;
    }
}