package com.khangai.tarkhaev.startup;


import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.io.File;

public class App {

    @Parameter(names = "-p")
    String htmlPath;

    @Parameter(names = "-f", converter = HtmlFileConverter.class)
    File file;

    public static void main(String[] args) {
        App app = new App();
        JCommander.newBuilder().
                addObject(app).
                build().
                parse(args);
        System.out.println(app.file);
        System.out.println(app.htmlPath);
    }


}
