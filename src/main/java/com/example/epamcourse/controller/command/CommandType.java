package com.example.epamcourse.controller.command;

import com.example.epamcourse.controller.command.impl.*;
import com.example.epamcourse.controller.command.impl.go.*;
import com.example.epamcourse.model.entity.Account;

import java.util.Set;


public enum CommandType {
    LOGIN(new LoginCommand()),
    CREATE_ACCOUNT(new CreateAccountCommand()),
    GO_TO_LOGIN_PAGE(new GoToLoginPageCommand()),
    GO_TO_REGISTRATION_PAGE(new GoToRegistrationPageCommand()),
    TO_EXCEPTION_PAGE(new GoToExceptionPageCommand()),
    LOCALE_COMMAND(new LocaleCommand()),
    APPLICANT_ADD_SECURE_INFORMATION(new AddApplicantSecureInformationCommand()),
    ADMINISTRATOR_ADD_SECURE_INFORMATION(new AddAdministratorSecureInformationCommand()),
    GO_TO_MAIN_PAGE_ADMINISTRATOR(new GoToMainPageAdministratorCommand()),
    GO_TO_MAIN_PAGE_APPLICANT(new GoToMainPageApplicantCommand());

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
