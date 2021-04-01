package com.khangai.tarkhaev.name;

public class FullName extends BinomialName implements PrintStrategy {

    private String patronymic;

    public FullName(String[] nameArgs) {
        super(nameArgs);
        patronymic = nameArgs[2];
    }

    @Override
    public void print() {
        System.out.println("Ура! мы нашли фамилию: " + super.getSurname() + ", имя: " + super.getFirstName() + ", отчество: " + patronymic);
    }

    public String getPatronymic() {
        return patronymic;
    }
}
