package com.example.persistence.demo.domain;

import javax.persistence.*;

@Entity
public class ChildEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int serialNumber;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private BasicEntity parent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public BasicEntity getParent() {
        return parent;
    }

    public void setParent(BasicEntity parent) {
        this.parent = parent;
    }
}
