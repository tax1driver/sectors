package com.github.tax1driver.sectors.helpers;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CommandSyntax {
    private List<CommandArgument> arguments;

    public List<CommandArgument> getArguments() {
        return arguments;
    }

    public CommandSyntax(CommandArgument... arguments) {
        this.arguments = Arrays.asList(arguments);

    }
    // huj huj huj
    // a: huj b: huj c: huj
    // <a> <b> <c> ...

    public HashMap<String, String> parse(String toParse) {
        HashMap<String, String> toReturn = new HashMap<>();
        String[] splitted = toParse.split(" ", arguments.size());

       for (int i = 0; i < arguments.size(); i++) {

           if (arguments.size()-1 == i) {
               // last argument in set, make it contain all the "arguments" contained in command
               String sumarized = "";
               for (int j = i; j < splitted.length; j++) {
                    sumarized += splitted[j];

                    if (j != splitted.length-1)
                        sumarized += " ";
               }

               toReturn.put(arguments.get(i).getName(), sumarized);
           }

           if (splitted.length >= i)
               toReturn.put(arguments.get(i).getName(), splitted[i]);
           else
               toReturn.put(arguments.get(i).getName(), null);


       }

        return toReturn;

    }

    public String toString() {
        String ret = "";

        for (CommandArgument arg:
             arguments) {
            ret += arg.getName() + ":text";
            ret += " ";
        }

        return ret;
    }



}
