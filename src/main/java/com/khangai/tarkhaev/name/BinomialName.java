package com.khangai.tarkhaev.name;

public class BinomialName extends Name {

    private String surname;

    public BinomialName(String... nameArgs) {
        super(nameArgs);
        this.surname = nameArgs[1];
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public void print() {
        System.out.println("Ура! мы нашли фамилию: " + surname + ", имя: " + super.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        BinomialName binomialName = (BinomialName) o;
        return surname.equals(binomialName.surname);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + surname.hashCode();
        return result;
    }
}
