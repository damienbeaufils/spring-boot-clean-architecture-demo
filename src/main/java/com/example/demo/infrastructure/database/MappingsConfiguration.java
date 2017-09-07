package com.example.demo.infrastructure.database;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.boot.jdbc.SchemaManagementProvider;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import java.util.List;

@Configuration
public class MappingsConfiguration extends HibernateJpaAutoConfiguration {

    public MappingsConfiguration(DataSource dataSource, JpaProperties jpaProperties, ObjectProvider<JtaTransactionManager> jtaTransactionManager, ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers, ObjectProvider<List<SchemaManagementProvider>> providers) {
        super(dataSource, jpaProperties, jtaTransactionManager, transactionManagerCustomizers, providers);
    }

    @Bean
    @Override
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder factoryBuilder) {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = getSuperEntityManagerFactory(factoryBuilder);
        localContainerEntityManagerFactoryBean.setMappingResources(
                "db/mappings/dummy.xml"
        );
        return localContainerEntityManagerFactoryBean;
    }

    LocalContainerEntityManagerFactoryBean getSuperEntityManagerFactory(EntityManagerFactoryBuilder factoryBuilder) {
        return super.entityManagerFactory(factoryBuilder);
    }
}
