package com.example.demo.infrastructure.database;

import org.junit.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MappingsConfigurationUTest {

    @Test
    public void entityManagerFactory_should_create_new_entity_manager_factory_using_superclass_and_configure_mapping_resources() {
        // Given
        EntityManagerFactoryBuilder entityManagerFactoryBuilder = mock(EntityManagerFactoryBuilder.class);
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = mock(LocalContainerEntityManagerFactoryBean.class);
        MappingsConfiguration mappingsConfiguration = new MappingsConfiguration(mock(DataSource.class), mock(JpaProperties.class), mock(ObjectProvider.class), mock(ObjectProvider.class), mock(ObjectProvider.class)) {
            @Override
            LocalContainerEntityManagerFactoryBean getSuperEntityManagerFactory(EntityManagerFactoryBuilder factoryBuilder) {
                return factoryBuilder == entityManagerFactoryBuilder ? localContainerEntityManagerFactoryBean : null;
            }
        };

        // When
        LocalContainerEntityManagerFactoryBean result = mappingsConfiguration.entityManagerFactory(entityManagerFactoryBuilder);

        // Then
        verify(localContainerEntityManagerFactoryBean).setMappingResources(
                "db/mappings/dummy.xml"
        );
        assertThat(result).isEqualTo(localContainerEntityManagerFactoryBean);
    }
}
