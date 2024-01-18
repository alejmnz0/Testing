package org.springframework.samples.petclinic.repository.springdatajpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.repository.PetTypeRepository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles({"postgresql", "spring-data-jpa"})
@Testcontainers
@Sql(value = "classpath:insert-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//@Sql(value = "classpath:insert-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class SpringDataPetTypeRepositoryImplTest {

    @Autowired
     SpringDataPetTypeRepositoryImpl repository;

    @Autowired
    PetTypeRepository petTypeRepository;

    @Container
    @ServiceConnection
    static PostgreSQLContainer postgres = new PostgreSQLContainer(DockerImageName.parse("postgres:9.6.0"))
        .withUsername("postgres")
        .withPassword("petclinic")
        .withDatabaseName("POSTGRESQL");

    @Test
    void deleteTest() {

        PetType aBorrar = petTypeRepository.findById(10);

        int cantidadActual = petTypeRepository.findAll().size();

        repository.delete(aBorrar);

        assertEquals(cantidadActual -1, petTypeRepository.findAll().size());

        assertNull(petTypeRepository.findById(10));
    }
}

