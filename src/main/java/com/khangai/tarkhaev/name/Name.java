package com.khangai.tarkhaev.name;

public class Name implements PrintableName {

    private String firstName;

    public Name(String... nameArgs) {
        firstName = nameArgs[0];
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getName() {
        return firstName;
    }

    public void print() {
        System.out.print("Ура! мы нашли имя: " + firstName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;

        return firstName.equals(name1.firstName);
    }

    @Override
    public int hashCode() {
        return firstName.hashCode();
    }
}

