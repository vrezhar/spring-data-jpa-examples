package com.example.persistence.demo.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "BasicEntity.findByCompanionName",
                query = "SELECT b FROM BasicEntity b WHERE b.companionEntity.name=:name"
        )
})
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "BasicEntity.findByCountOfSomething",
                query = "SELECT * FROM basic_entity WHERE count_of_something=:count"
        )
})
public class BasicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String data;

    @Embedded
    private EmbeddableData embeddableData = new EmbeddableData();

    @OneToOne(mappedBy = "basicEntity", fetch = FetchType.LAZY)
    private CompanionEntity companionEntity;

    @Column(name = "`value`")
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "`attached_values`", joinColumns = @JoinColumn(name = "basic_entity_id"))
    private List<String> attachedValues = new ArrayList<>();

    @OneToMany(mappedBy = "parent")
    private List<ChildEntity> childEntities = new ArrayList<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "`attached_counts`", joinColumns = @JoinColumn(name = "basic_entity_id"))
    @AttributeOverride(name = "countOfSomethingElse", column = @Column(name = "count_of_something_entirely_else"))
    private List<EmbeddableData> attachedCounts = new ArrayList<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @MapKeyColumn(name = "`word`")
    @Column(name = "`explanation`")
    @CollectionTable(name = "`dictionary`")
    @MapKeyJoinColumn(name = "parent_id")
    private Map<String, String> simpleAssDictionary = new HashMap<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @MapKeyJoinColumn(name = "parent_id")
    @MapKeyColumn(name = "`count_descriptor`")
    @CollectionTable(name = "`count_dictionary`")
    private Map<String, EmbeddableData> countDictionary = new HashMap<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @MapKeyJoinColumn(name = "parent_id")
    @CollectionTable(name = "`count_to_count_dictionary`")
    private Map<EmbeddableData, String> countToDescriptionDictionary = new HashMap<>();

    @OneToMany(fetch = FetchType.LAZY)
    @MapKeyColumn(name = "`entity_key`")
    @MapKeyJoinColumn(name = "`value_id`")
    @CollectionTable(name = "entity_string_dictionary")
    private Map<String, EntityMapValue> entityStringDictionary = new HashMap<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "b2b",
            joinColumns = @JoinColumn(name = "first_basic_id"),
            inverseJoinColumns = @JoinColumn(name = "second_basic_id")
    )
    private List<BasicEntity> otherBasics = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public EmbeddableData getEmbeddableData() {
        return embeddableData;
    }

    public void setEmbeddableData(EmbeddableData embeddableData) {
        this.embeddableData = embeddableData;
    }

    public CompanionEntity getCompanionEntity() {
        return companionEntity;
    }

    public void setCompanionEntity(CompanionEntity companionEntity) {
        this.companionEntity = companionEntity;
    }

    public List<String> getAttachedValues() {
        return attachedValues;
    }

    public void setAttachedValues(List<String> attachedValues) {
        this.attachedValues = attachedValues;
    }

    public List<ChildEntity> getChildEntities() {
        return childEntities;
    }

    public void setChildEntities(List<ChildEntity> childEntities) {
        this.childEntities = childEntities;
    }

    public List<EmbeddableData> getAttachedCounts() {
        return attachedCounts;
    }

    public void setAttachedCounts(List<EmbeddableData> attachedCounts) {
        this.attachedCounts = attachedCounts;
    }

    public Map<String, String> getSimpleAssDictionary() {
        return simpleAssDictionary;
    }

    public void setSimpleAssDictionary(Map<String, String> simpleAssDictionary) {
        this.simpleAssDictionary = simpleAssDictionary;
    }

    public Map<String, EmbeddableData> getCountDictionary() {
        return countDictionary;
    }

    public void setCountDictionary(Map<String, EmbeddableData> countDictionary) {
        this.countDictionary = countDictionary;
    }

    public Map<EmbeddableData, String> getCountToDescriptionDictionary() {
        return countToDescriptionDictionary;
    }

    public void setCountToDescriptionDictionary(Map<EmbeddableData, String> countToDescriptionDictionary) {
        this.countToDescriptionDictionary = countToDescriptionDictionary;
    }

    public Map<String, EntityMapValue> getEntityStringDictionary() {
        return entityStringDictionary;
    }

    public void setEntityStringDictionary(Map<String, EntityMapValue> entityStringDictionary) {
        this.entityStringDictionary = entityStringDictionary;
    }

    public List<BasicEntity> getOtherBasics() {
        return otherBasics;
    }

    public void setOtherBasics(List<BasicEntity> otherBasics) {
        this.otherBasics = otherBasics;
    }
}
