package com.khangai.tarkhaev.startup;


import com.khangai.tarkhaev.name.PrintableName;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.ParseError;
import org.jsoup.parser.ParseErrorList;
import org.jsoup.parser.Parser;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

public class HTMLParser {

    private final Parser parser = Parser.htmlParser().setTrackErrors(1);

    private Document document;

    HTMLParser(String html) {
        document = Jsoup.parse(html, "", parser);
    }

    HTMLParser(File html) {
        try {
            document = Jsoup.parse(new FileInputStream(html), "UTF-8", "", parser);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void parse() {
        ParseErrorList errors = parser.getErrors();
        if (errors.size() > 0) {
            System.out.println("Кривой HTML");
            for (ParseError parseError : errors) {
                System.out.println(parseError.getErrorMessage());
            }
            return;
        }
        Optional<Element> elementOptional = document.getElementsByTag("p").stream().
                filter(elem -> elem.hasClass("full_name")).findAny();
        if (elementOptional.isPresent()) {
            Element element = elementOptional.get();
            if (element.hasText()) {
                String[] fullNameArray = element.text().split("\\s+");
                if (fullNameArray.length <= 3) {
                    PrintableName.getInstance(fullNameArray).print();
                } else {
                    System.out.println("Упс! Кажется, что-то слишком сложное :(");
                }
            } else {
                System.out.println("Тег пуст");
            }
        } else {
            System.out.println("Не найдено тега <p> с классом \"full_name\"");
        }
    }
}
