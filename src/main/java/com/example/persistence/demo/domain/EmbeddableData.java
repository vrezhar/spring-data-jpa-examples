package com.example.persistence.demo.domain;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class EmbeddableData {
    private int countOfSomething;
    private int countOfSomethingElse;

    public EmbeddableData() {

    }

    public EmbeddableData(int countOfSomething, int countOfSomethingElse) {
        this.countOfSomething = countOfSomething;
        this.countOfSomethingElse = countOfSomethingElse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmbeddableData that = (EmbeddableData) o;
        return countOfSomething == that.countOfSomething && countOfSomethingElse == that.countOfSomethingElse;
    }

    @Override
    public int hashCode() {
        return Objects.hash(countOfSomething, countOfSomethingElse);
    }

    public int getCountOfSomething() {
        return countOfSomething;
    }

    public void setCountOfSomething(int countOfSomething) {
        this.countOfSomething = countOfSomething;
    }

    public int getCountOfSomethingElse() {
        return countOfSomethingElse;
    }

    public void setCountOfSomethingElse(int countOfSomethingElse) {
        this.countOfSomethingElse = countOfSomethingElse;
    }
}
