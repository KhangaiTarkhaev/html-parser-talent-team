package com.khangai.tarkhaev.startup;

import com.beust.jcommander.IStringConverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class HtmlFileConverter implements IStringConverter<File> {
    @Override
    public File convert(String s) {
        return new File(s);
    }
}
