package com.khangai.tarkhaev.name;

public class Name {

    private String name;

    public Name(String... nameArgs) {
        name = nameArgs[0];
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void print() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        System.out.println(getClass());
        Name name1 = (Name) o;

        return name.equals(name1.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}

