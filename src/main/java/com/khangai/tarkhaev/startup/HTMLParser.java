package com.khangai.tarkhaev.startup;


import com.khangai.tarkhaev.name.BinomialName;
import com.khangai.tarkhaev.name.FullName;
import com.khangai.tarkhaev.name.Name;
import com.khangai.tarkhaev.name.PrintStrategy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.w3c.dom.Node;
import org.w3c.tidy.Tidy;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.*;

public class HTMLParser {

    private Parser parser;

    {
       parser = Parser.htmlParser().setTrackErrors(1);
    }

    private Document document;

    HTMLParser(String html) {
        document = Jsoup.parse(html,"", parser);
        System.out.println(parser.getErrors());
    }

    HTMLParser(File html) {
        try {
            document = Jsoup.parse(new FileInputStream(html), "UTF-8", "", parser);
            System.out.println(parser.getErrors());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
//        try {
//            //document = tidy.parseDOM(new FileReader(html), null);
//        } catch (FileNotFoundException e) {
//            System.out.println("Файл не найден.");
//        }
    }

    public void parse() {
        Element element = document.getElementsByAttribute("p").hasClass("full_name");
        XPath xPath = XPathFactory.newInstance().newXPath();
        XPathExpression xPathExpression = null;
//        try {
//            xPathExpression = xPath.compile("//p[@class='full_name']");
//            Node node = (Node) xPathExpression.evaluate(document, XPathConstants.NODE);
//            if (node == null) {
//                System.out.println("Не найдено тега <p> с классом \"full_name\"");
//                return;
//            }
//            System.out.println(node.getFirstChild().getNodeType() == Node.TEXT_NODE);
//            String text = node.getFirstChild().getNodeValue();
//            if (text == null) {
//                System.out.println("Тег пуст");
//                return;
//            }
//            String[] fullNameArray = text.split("\\s+");
//            int fullNameSize = fullNameArray.length;
//            if (!(fullNameSize <= 3)) {
//                System.out.println("Упс! Кажется, что-то слишком сложное :(");
//                return;
//            }
//            PrintStrategy printStrategy = fullNameSize == 1 ? new Name(fullNameArray[0]) :
//                    fullNameSize == 2 ? new BinomialName(fullNameArray) : new FullName(fullNameArray);
//            printStrategy.print();
//        } catch (XPathExpressionException e) {
//        }
    }
}


