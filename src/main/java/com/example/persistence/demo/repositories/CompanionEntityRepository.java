package com.example.persistence.demo.repositories;

import com.example.persistence.demo.domain.CompanionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanionEntityRepository extends JpaRepository<CompanionEntity, Long> {
}
