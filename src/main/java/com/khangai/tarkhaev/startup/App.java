package com.khangai.tarkhaev.startup;


import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.io.File;


public class App {

    @Parameter
    private String html;

    @Parameter(names = {"--path", "-p"}, converter = HtmlFileConverter.class)
    private File file;

    public static void main(String[] args) {
        App app = new App();
        JCommander.newBuilder().
                addObject(app).
                build().
                parse(args);
        app.run();

    }

    private void run() {
        System.out.println(html);
        HTMLParser htmlParser = null;
        if (html != null) {
            htmlParser = new HTMLParser(html);
        } else if (file != null) {
            htmlParser = new HTMLParser(file);
        }
        if (htmlParser != null) {
            htmlParser.parse();
        } else {
            System.out.println("Illegal arguments");
        }
    }
}


