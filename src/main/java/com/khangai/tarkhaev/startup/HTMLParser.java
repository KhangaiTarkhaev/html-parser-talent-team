package com.khangai.tarkhaev.startup;


import com.khangai.tarkhaev.name.BinomialName;
import com.khangai.tarkhaev.name.FullName;
import com.khangai.tarkhaev.name.Name;
import com.khangai.tarkhaev.name.PrintStrategy;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

public class HTMLParser {

    //Using DOM - HTML Parsers auto clean dirty html code: cannot detect invalid html code.
    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

    private Document document;

    HTMLParser(String html) {
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(new InputSource(new StringReader(html)));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println("Кривой HTML");
            System.out.println(e.getMessage());
        }
    }

    HTMLParser(File html) {
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(html);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }

    public void parse() {
        XPath xPath = XPathFactory.newInstance().newXPath();
        XPathExpression xPathExpression = null;
        try {
            xPathExpression = xPath.compile("//p[@class='full_name']");
            Node node = (Node) xPathExpression.evaluate(document, XPathConstants.NODE);
            if (node == null) {
                System.out.println("Не найдено тега <p> с классом \"full_name\"");
                return;
            }
            System.out.println(node.getFirstChild().getNodeType() == Node.TEXT_NODE);
            String text = node.getFirstChild().getNodeValue();
            if (text == null) {
                System.out.println("Тег пуст");
                return;
            }
            String[] fullNameArray = text.split("\\s+");
            int fullNameSize = fullNameArray.length;
            if (!(fullNameSize <= 3)) {
                System.out.println("Упс! Кажется, что-то слишком сложное :(");
                return;
            }
            PrintStrategy printStrategy = fullNameSize == 1 ? new Name(fullNameArray[0]) :
                    fullNameSize == 2 ? new BinomialName(fullNameArray) : new FullName(fullNameArray);
            printStrategy.print();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }
}


