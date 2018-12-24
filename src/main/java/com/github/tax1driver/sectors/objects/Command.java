package com.github.tax1driver.sectors.objects;



import com.github.tax1driver.sectors.helpers.CommandExecutor;
import com.github.tax1driver.sectors.helpers.CommandSyntax;

import java.util.List;

public class Command {
    private String name;
    private List<CommandExecutor> executors;
    private CommandSyntax syntax;


    public Command(String name, String syntaxPattern) {
        this.name = name;

    }

    public Command(String name, String syntaxPattern, CommandExecutor... executors) {

    }

    public void addExecutors(CommandExecutor... executors) {

    }

    public String getName() {
        return name;
    }

}
