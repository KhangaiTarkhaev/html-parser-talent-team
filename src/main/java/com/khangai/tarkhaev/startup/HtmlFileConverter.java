package com.khangai.tarkhaev.startup;

import com.beust.jcommander.IStringConverter;

import java.io.File;

public class HtmlFileConverter implements IStringConverter<File> {
    @Override
    public File convert(String s) {
        return new File(s);
    }
}
