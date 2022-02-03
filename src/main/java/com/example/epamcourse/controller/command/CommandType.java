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
    GO_TO_MAIN_PAGE_APPLICANT(new GoToMainPageApplicantCommand()),
    GO_TO_ADD_REQUEST_PAGE(new GoToAddRequestPageCommand()),
    ADD_REQUEST_COMMAND(new AddRequestCommand()),
    SHOW_REQUESTS_COMMAND(new ShowRequestsCommand()),
    GO_TO_EDIT_APPLICANT_DATA(new GoToEditApplicantDataCommand()),
    LOGOUT(new LogoutCommand()),
    UPDATE_APPLICANT_DATA(new UpdateApplicantDataCommand()),
    GO_TO_EDIT_FACULTY(new GoToEditFacultyPageCommand()),
    EDIT_FACULTY(new EditFacultyCommand()),
    DELETE_FACULTY(new DeleteFacultyCommand()),
    GO_TO_ADD_FACULTY_PAGE(new GoToAddFacultyPageCommand()),
    ADD_FACULTY(new AddFacultyCommand()),
    SHOW_ACCOUNTS(new ShowAccountsCommand()),
    CHANGE_ACCOUNT_STATUS(new ChangeAccountStatusCommand()),
    DELETE_ACCOUNT(new DeleteAccountCommand()),
    GO_TO_ADD_ADMIN_ACCOUNT(new GoToAddingAdminAccountPageCommand()),
    ADD_ADMIN_ACCOUNT(new AddAdminAccountCommand()),
    SHOW_FACULTIES(new ShowFacultiesCommand()),
    CONFIRM_EMAIL(new SendEmailCodeCommand()),
    GO_TO_SHOW_APPLICANTS_PAGE(new GoToShowApplicantsPageCommand()),
    GO_TO_SENDER_PAGE(new GoToSenderPageCommand()),
    SEND_MESSAGE(new SendMessageCommand()),
    GO_TO_CONFIRM_EMAIL_PAGE(new SendMessageCommand()),
    GO_TO_EDIT_RECRUITMENT_PAGE(new GoToEditRecruitmentPageCommand()),
    EDIT_RECRUITMENT(new UpdateRecruitmentCommand()),
    GO_TO_EDIT_ADMINISTRATOR_DATA(new GoToEditAdministratorDataCommand()),
    UPDATE_ADMINISTRATOR_DATA(new UpdateAdministratorDataCommand()),
    SHOW_APPLICANTS(new ShowApplicantsCommand());

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
