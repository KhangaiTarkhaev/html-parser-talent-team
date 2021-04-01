package com.khangai.tarkhaev.name;

public class Name implements PrintStrategy {

    private String firstName;

    public Name(String firstname) {
        firstName = firstname;
    }

    public String getFirstName() {
        return firstName;
    }

    @Override
    public void print() {
        System.out.print("Ура! мы нашли имя: " + firstName);
    }
}

