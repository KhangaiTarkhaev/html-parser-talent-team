package com.khangai.tarkhaev.name;

public class FullName extends BinomialName {

    private String patronymic;

    public FullName(String... nameArgs) {
        super(nameArgs);
        patronymic = nameArgs[2];
    }

    public String getPatronymic() {
        return patronymic;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        FullName fullName = (FullName) o;
        return patronymic.equals(fullName.patronymic);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + patronymic.hashCode();
        return result;
    }
}
