package com.dima.webrest.versioning;

public class PersonV2 {
    public PersonV2(Name name) {
        this.name = name;
    }

    public PersonV2() {
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Name getName() {
        return name;
    }

    private Name name;
}
