package com.khangai.tarkhaev.name;

public class BinomialName extends Name {

    private String surname;

    public BinomialName(String[] nameArgs) {
        super(nameArgs[1]);
        surname = nameArgs[0];
    }

    public String getSurname() {
        return surname;
    }

    public void print() {
        System.out.println("Ура! мы нашли фамилию: " + surname + ", имя: " + super.getFirstName());
    }

}
