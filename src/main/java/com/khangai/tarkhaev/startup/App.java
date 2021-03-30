package com.khangai.tarkhaev.startup;


import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class App {

    @Parameter
    String html;

    @Parameter(names = {"--path", "-p"}, converter = HtmlFileConverter.class)
    File file;

    @Parameter(names = {"--url", "-u"})
    String url;

    public static void main(String[] args) {
        App app = new App();
        JCommander.newBuilder().
                addObject(app).
                build().
                parse(args);
        app.run();
    }

    public void run() {
        Document document = null;
        if (Objects.nonNull(html)) {
            document = Jsoup.parse(html);
        } else if (Objects.nonNull(file)) {
            try {
                document = Jsoup.parse(file, "UTF-8");
            } catch (IOException ioException) {
                System.out.println("Cannot read file : " + ioException.getMessage());
            }
        } else if (Objects.nonNull(url)){
            document = Jsoup.parse(url);
        } else {
            System.out.println("Illegal arguments. Valid args : --path or -p <absolute path to html> \\n " +
                    "--url or -u <url to parse>\\n"
                     + "pass html string as argument");
        }
        Optional<Element> element = document.getElementsByTag("p").stream().
                filter(elem -> elem.hasClass("full_name")).findAny();
        String fullName = null;
        if (element.isPresent()) {
            fullName = element.get().text();
            String[] fullNameArray = fullName.split("\\s+");

        } else {
            System.out.println("");
        }
    }
}


