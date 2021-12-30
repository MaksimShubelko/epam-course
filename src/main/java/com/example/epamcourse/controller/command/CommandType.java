package com.example.epamcourse.controller.command;

import com.example.epamcourse.controller.command.impl.CreateAccountCommand;
import com.example.epamcourse.controller.command.impl.LoginCommand;
import com.example.epamcourse.model.entity.Account;

import java.util.Set;

import static com.example.epamcourse.model.entity.Account.Role.APPLICANT;


public enum CommandType {
    LOGIN(new LoginCommand()),
    CREATE_ACCOUNT(new CreateAccountCommand());

    private Set<Account.Role> roles;
    private Command command;

    CommandType(Command command, Account.Role ... roles) {
        this.command = command;
        this.roles = Set.of(roles);
    }

    public Command getCommand() {
        return command;
    }

    public boolean isValidRole(Account.Role role) {
        return roles.stream().anyMatch(r -> r == role);
    }
}
