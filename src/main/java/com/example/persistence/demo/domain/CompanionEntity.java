package com.example.persistence.demo.domain;

import javax.persistence.*;

@Entity
public class CompanionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne
    @JoinColumn(name = "basic_entity_id")
    private BasicEntity basicEntity;


    public CompanionEntity() {

    }

    public CompanionEntity(String name, BasicEntity basicEntity) {
        this.name = name;
        this.basicEntity = basicEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BasicEntity getBasicEntity() {
        return basicEntity;
    }

    public void setBasicEntity(BasicEntity basicEntity) {
        this.basicEntity = basicEntity;
    }
}
