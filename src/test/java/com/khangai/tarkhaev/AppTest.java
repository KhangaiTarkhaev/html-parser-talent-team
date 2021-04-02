package com.khangai.tarkhaev;

import com.khangai.tarkhaev.startup.App;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.FileSystems;

public class AppTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private String output;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    String getAbsolutePath(String testFileName) {
        String relativePath = "src/test/resources/sampleHtml/" + testFileName;
        return FileSystems.getDefault().
                getPath(relativePath).normalize().
                toAbsolutePath().toString();
    }

    @Test
    void parse_Name() {
        String[] args = {"-p",
                getAbsolutePath("OnlyName.html")};
        App.main(args);
        Assertions.assertEquals("Ура! мы нашли имя: Василий",
                outputStreamCaptor.toString().trim());
    }

    @Test
    void parse_BinomialName() {
        String[] args = {"-p",
                getAbsolutePath("BinomialName.html")};
        App.main(args);
        Assertions.assertEquals("Ура! мы нашли фамилию: Иванов, имя: Василий",
                outputStreamCaptor.toString().trim());
    }

    @Test
    void parse_FullName() {
        String[] args = {"-p",
                getAbsolutePath("FullName.html")};
        App.main(args);
        Assertions.assertEquals("Ура! мы нашли фамилию: Васильев, имя: Анатолий, отчество: Петрович", outputStreamCaptor.toString().trim());
    }

    @Test
    void parse_InvalidHtml() {
        String[] args = {"-p",
                getAbsolutePath("InvalidHtml.html")};
        App.main(args);
        Assertions.assertEquals("Кривой HTML", outputStreamCaptor.toString().trim());
    }

    @Test
    void parse_WithoutClass() {
        String[] args = {"-p",
                getAbsolutePath("WithoutClass.html")};
        App.main(args);
        Assertions.assertEquals("Не найдено тега <p> с классом \"full_name\"", outputStreamCaptor.toString().trim());
    }

    @Test
    void parse_WithoutName() {
        String[] args = {"-p",
                getAbsolutePath("WithoutName.html")};
        App.main(args);
        Assertions.assertEquals("Тег пуст", outputStreamCaptor.toString().trim());
    }

    @Test
    void parse_WrongClass() {
        String[] args = {"-p",
                getAbsolutePath("WrongClass.html")};
        App.main(args);
        Assertions.assertEquals("Не найдено тега <p> с классом \"full_name\"", outputStreamCaptor.toString().trim());
    }

    @Test
    void parse_WrongTag() {
        String[] args = {"-p",
                getAbsolutePath("WrongTag.html")};
        App.main(args);
        Assertions.assertEquals("Не найдено тега <p> с классом \"full_name\"", outputStreamCaptor.toString().trim());
    }

    @Test
    void parse_ToLongName() {
        String[] args = {"-p",
                getAbsolutePath("ToLongName.html")};
        App.main(args);
        Assertions.assertEquals("Упс! Кажется, что-то слишком сложное :(", outputStreamCaptor.toString().trim());
    }
}
