package com.example.demo.infrastructure.database.repositories;

import com.example.demo.domain.Dummy;
import com.example.demo.domain.DummyRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DummyJpaRepository extends JpaRepository<Dummy, Long>, DummyRepository {
}
