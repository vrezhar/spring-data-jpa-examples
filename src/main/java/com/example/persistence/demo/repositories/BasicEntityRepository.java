package com.example.persistence.demo.repositories;

import com.example.persistence.demo.domain.BasicEntity;
import com.example.persistence.demo.projections.SimpleProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BasicEntityRepository extends JpaRepository<BasicEntity, Long> {
    List<BasicEntity> findAllByData(String data);

    Optional<SimpleProjection> findByData(String data);

    @Query(name = "BasicEntity.findByCompanionName")
    List<BasicEntity> findAllByCompanionName(@Param("name") String name);

    @Query(name = "BasicEntity.findByCountOfSomething")
    List<BasicEntity> findAllByCount(@Param("count") int count);
}
