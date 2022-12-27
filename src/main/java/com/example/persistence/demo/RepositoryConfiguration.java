package com.example.persistence.demo;

import org.hibernate.SessionFactory;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@ComponentScan
public class RepositoryConfiguration {

    @Bean
    static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/spring_data")
                .username(System.getenv("DATABASE_USER"))
                .password(System.getenv("DATABASE_PASSWORD"))
                .build();
    }

    @Bean
    JpaProperties jpaProperties() {
        final JpaProperties jpaProperties = new JpaProperties();
        jpaProperties.setDatabase(Database.MYSQL);
        jpaProperties.setShowSql(true);
        jpaProperties.setProperties(
                Map.of(
                        "hibernate.physical_naming_strategy", "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy",
                        "hibernate.dialect", "org.hibernate.dialect.MySQL57InnoDBDialect",
                        "hibernate.id.new_generator_mappings", "false"
                )
        );
        return jpaProperties;
    }

    @Bean
    HibernateProperties hibernateProperties() {
        final HibernateProperties hibernateProperties = new HibernateProperties();
        hibernateProperties.setDdlAuto("create-drop");
        hibernateProperties.setUseNewIdGeneratorMappings(false);
        return hibernateProperties;
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean localEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localEntityManagerFactoryBean.setDataSource(dataSource());
        localEntityManagerFactoryBean.setPackagesToScan("com.example.persistence.demo.domain");
        localEntityManagerFactoryBean.setPersistenceUnitName("default");
        localEntityManagerFactoryBean.setJpaPropertyMap(hibernateProperties()
                .determineHibernateProperties(jpaProperties().getProperties(), new HibernateSettings()));
        localEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return localEntityManagerFactoryBean;
    }

    @Bean
    EntityManagerFactory entityManagerFactory() {
        return entityManagerFactoryBean().getNativeEntityManagerFactory();
    }

    @Bean
    HibernateTransactionManager transactionManager() {
        return new HibernateTransactionManager((SessionFactory)entityManagerFactory());
    }
}
