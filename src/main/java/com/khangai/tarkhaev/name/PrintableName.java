package com.khangai.tarkhaev.name;

public interface PrintableName {

    void print();

    static PrintableName getInstance(String ... args) {
        if (args.length==1) return new Name(args);
        else if (args.length==2) return new BinomialName(args);
        else return new FullName(args);
    }
}
