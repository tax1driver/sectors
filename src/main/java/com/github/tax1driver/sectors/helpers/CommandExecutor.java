package com.github.tax1driver.sectors.helpers;

import com.github.tax1driver.sectors.objects.Command;

import java.util.HashMap;

public interface CommandExecutor {
    void onCommand(Command c, HashMap<String, String> arguments);
}
