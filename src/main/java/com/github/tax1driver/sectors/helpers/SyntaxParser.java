package com.github.tax1driver.sectors.helpers;

import com.github.tax1driver.sectors.utils.parsing.ParsingUtils;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/*
   Parser for CommandSyntax class
   Please keep in mind that this is not an example of a good parser,
   neither this method of parsing should be used while analyzing serious code.
*/

public class SyntaxParser {
    enum State {
        COMMAND_NAME,
        ARGUMENT_NAME,
        ARGUMENT_TYPE,
        EOL
    }

    private State state;
    private String toParse;
    private int counter;
    private CommandSyntax output;

    private void error(String e) {

    }

    private void eol() {
        state = State.EOL;
    }

    private void indent(boolean needed) {
        Pattern p = Pattern.compile("[^\\s]");
        Matcher m = p.matcher(toParse.substring(counter));

        if (m.find()) {
            counter += (m.end() - m.start());

            if (needed)
                error("Indent expected");
        }
    }

    private void expectSingleChar(char c) {
        if (toParse.charAt(counter) == c)
            error("Expected \'"+c+"\'");
        counter++;
    }

    private String name(boolean expectingIndentAfterwards) {
        String out = toParse.substring(counter, ParsingUtils.indexOfRegex(toParse.substring(counter), "[^\\w+]"));
        // read all chars till the next space, dont allow special characters.
        if (!out.matches("^[a-zA-Z0-9_]*$"))
            error("No special characters allowed in name");

        if (out.matches("^[^0-9][\\w]+$"))
            error("Name can't start with a numeric character");

        counter += out.length();

        indent(expectingIndentAfterwards);

        return out;
    }


    private void argument() {
        boolean optional = false;
        if (toParse.charAt(counter) == '[')
            optional = true;
        else if(toParse.charAt(counter) != '<')
            error("Invalid argument type");

        counter++;
        String argName = name(false);
        expectSingleChar(':');
        String typename = name(false);

        if (optional)
            expectSingleChar(']');
        else expectSingleChar('>');
    }

    private void string() {

    }

    private void type() {

    }

    private void parse() {
        // as we start the line we have those:
        this.state = State.COMMAND_NAME;

        String cmdName = name(true);
        for (; counter < toParse.length();) {
            argument();
        }

        finalizeParsing();
    }

    private void finalizeParsing() {

    }

    public SyntaxParser(String toParse, CommandSyntax output) {
        this.toParse = toParse;
        this.output = output;
        this.counter = 0;
    }

}

