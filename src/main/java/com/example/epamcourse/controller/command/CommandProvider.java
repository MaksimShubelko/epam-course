package com.example.epamcourse.controller.command;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

/**
 * class CommandProvider
 *
 * @author M.Shubelko
 */
public class  CommandProvider {

    /** The logger. */
    public static final Logger logger = LogManager.getLogger();

    /**
     * Execute.
     *
     * @param commandName the commandName
     * @return Router
     */
    public static Optional<Command> defineCommand(String commandName) {
        Optional<Command> current;
        if (commandName == null || commandName.isBlank()) {
            return Optional.empty();
        }
        try {
            CommandType type = CommandType.valueOf(commandName.toUpperCase());
            current = Optional.of(type.getCommand());
        } catch (IllegalArgumentException e) {
            logger.log(Level.ERROR, "Command {} is not found. {}", commandName, e);
            current = Optional.empty();
        }
        return current;
    }
}
