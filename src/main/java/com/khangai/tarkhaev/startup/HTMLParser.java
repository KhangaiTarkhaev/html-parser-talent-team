package com.khangai.tarkhaev.startup;


import com.khangai.tarkhaev.name.BinomialName;
import com.khangai.tarkhaev.name.FullName;
import com.khangai.tarkhaev.name.Name;
import com.khangai.tarkhaev.name.PrintStrategy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import java.io.*;
import java.util.Optional;

public class HTMLParser {

    private Parser parser;

    {
       parser = Parser.htmlParser().setTrackErrors(1);
    }

    private Document document;

    public HTMLParser() {
    }

    public HTMLParser setDocument(String html) {
        document = Jsoup.parse(html,"", parser);
        return this;
    }

    public HTMLParser setDocument(File html) {
        try {
            document = Jsoup.parse(new FileInputStream(html), "UTF-8", "", parser);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return this;
    }

    public void parse() {
        if (parser.getErrors().size() > 0) {
            System.out.println("Кривой HTML");
            return;
        }
        Optional<Element> pFullNameOpt = document.getElementsByTag("p").stream().
                filter(elem -> elem.hasClass("full_name")).findAny();
        if (pFullNameOpt.isEmpty()) {
            System.out.println("Не найдено тега <p> с классом \"full_name\"");
            return;
        }
        Element pFullName = pFullNameOpt.get();
        if (!pFullName.hasText()) {
            System.out.println("Тег пуст");
            return;
        }
        String[] fullNameArray = pFullName.text().split("\\s+");
        int fullNameSize = fullNameArray.length;
        if (!(fullNameSize <= 3)) {
            System.out.println("Упс! Кажется, что-то слишком сложное :(");
            return;
        }
        PrintStrategy printStrategy = fullNameSize == 1 ? new Name(fullNameArray[0]) :
                fullNameSize == 2 ? new BinomialName(fullNameArray) : new FullName(fullNameArray);
        printStrategy.print();
    }
}


