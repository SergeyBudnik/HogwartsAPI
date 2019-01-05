package com.bdev.hogwarts_api.config

import com.bdev.hogwarts_api.properties.PropertiesConfig.config
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.*
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.bdev.hogwarts_api.dao")
open class DatabaseConfig {
    @Bean
    open fun dataSource(): DataSource {
        val dataSourceConfig = HikariConfig()

        run {
            dataSourceConfig.poolName = "Hogwarts"
            dataSourceConfig.isRegisterMbeans = true
            dataSourceConfig.driverClassName = config.databaseDriver
            dataSourceConfig.jdbcUrl = config.databaseUrl
            dataSourceConfig.username = config.databaseUsername
            dataSourceConfig.password = config.databasePassword
            dataSourceConfig.maximumPoolSize = 10
            dataSourceConfig.maxLifetime = 60000
        }

        return HikariDataSource(dataSourceConfig)
    }

    @Bean
    open fun entityManagerFactory(dataSource: DataSource): LocalContainerEntityManagerFactoryBean {
        val entityManagerFactoryBean = LocalContainerEntityManagerFactoryBean()

        entityManagerFactoryBean.dataSource = dataSource
        entityManagerFactoryBean.jpaVendorAdapter = HibernateJpaVendorAdapter()
        entityManagerFactoryBean.setPackagesToScan("com.bdev.hogwarts_api.data.model")

        val jpaProperties = Properties()

        jpaProperties.put("hibernate.dialect", config.databaseDialect)
        jpaProperties.put("hibernate.hbm2ddl.auto", config.databaseStartupAction)
        jpaProperties.put("hibernate.show_sql", config.databaseShowSql)
        jpaProperties.put("hibernate.format_sql", config.databaseFormatSql)
        jpaProperties.put("hibernate.id.new_generator_mappings", config.databaseIdNewGeneratorMappings)
        jpaProperties.put("hibernate.connection.release_mode", config.databaseConnectionReleaseMode)

        entityManagerFactoryBean.setJpaProperties(jpaProperties)

        return entityManagerFactoryBean
    }

    @Bean
    open  fun transactionManager(entityManagerFactory: EntityManagerFactory): JpaTransactionManager {
        val transactionManager = JpaTransactionManager()

        transactionManager.entityManagerFactory = entityManagerFactory

        return transactionManager
    }
}