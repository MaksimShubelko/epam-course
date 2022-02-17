package com.example.epamcourse.controller.command;

import com.example.epamcourse.controller.command.impl.*;
import com.example.epamcourse.controller.command.impl.go.*;
import com.example.epamcourse.model.entity.Account;

import java.util.Set;

import static com.example.epamcourse.model.entity.Account.Role.*;


public enum CommandType {
    LOGIN(new LoginCommand(), NOT_AUTOMATED, APPLICANT, ADMIN),
    CREATE_ACCOUNT(new CreateAccountCommand(), NOT_AUTOMATED),
    GO_TO_LOGIN_PAGE(new GoToLoginPageCommand(), NOT_AUTOMATED, APPLICANT, ADMIN),
    GO_TO_REGISTRATION_PAGE(new GoToRegistrationPageCommand(), NOT_AUTOMATED, APPLICANT, ADMIN),
    TO_EXCEPTION_PAGE(new GoToExceptionPageCommand(), NOT_AUTOMATED, APPLICANT, ADMIN),
    LOCALE_COMMAND(new LocaleCommand(), NOT_AUTOMATED, APPLICANT, ADMIN),
    APPLICANT_ADD_SECURE_INFORMATION(new AddApplicantSecureInformationCommand(), APPLICANT),
    ADMINISTRATOR_ADD_SECURE_INFORMATION(new AddAdministratorSecureInformationCommand(), ADMIN),
    GO_TO_MAIN_PAGE_APPLICANT(new GoToMainPageApplicantCommand(), APPLICANT),
    GO_TO_ADD_REQUEST_PAGE(new GoToAddRequestPageCommand(), APPLICANT),
    ADD_REQUEST_COMMAND(new AddRequestCommand(), APPLICANT),
    SHOW_REQUESTS_COMMAND(new ShowRequestsCommand(), APPLICANT),
    GO_TO_EDIT_APPLICANT_DATA(new GoToEditApplicantDataCommand(), APPLICANT),
    LOGOUT(new LogoutCommand(), APPLICANT, ADMIN),
    UPDATE_APPLICANT_DATA(new UpdateApplicantDataCommand(), APPLICANT),
    GO_TO_EDIT_FACULTY(new GoToEditFacultyPageCommand(), ADMIN),
    EDIT_FACULTY(new EditFacultyCommand(), ADMIN),
    DELETE_FACULTY(new DeleteFacultyCommand(), ADMIN),
    GO_TO_ADD_FACULTY_PAGE(new GoToAddFacultyPageCommand(), ADMIN),
    ADD_FACULTY(new AddFacultyCommand(), ADMIN),
    CHANGE_ACCOUNT_STATUS(new ChangeAccountStatusCommand(), ADMIN),
    DELETE_ACCOUNT(new DeleteAccountCommand(), ADMIN),
    GO_TO_ADD_ADMIN_ACCOUNT(new GoToAddingAdminAccountPageCommand(), ADMIN),
    GO_TO_UPLOAD_IMAGE(new GoToUploadImagePageCommand(), APPLICANT, ADMIN),
    ADD_ADMIN_ACCOUNT(new AddAdminAccountCommand(), ADMIN),
    GO_TO_SHOW_APPLICANTS(new GoToShowApplicantsPageCommand(), ADMIN),
    GO_TO_SHOW_ACCOUNTS(new GoToShowAccountsPageCommand(), ADMIN),
    CONFIRM_EMAIL(new SendEmailCodeCommand(), NOT_AUTOMATED),
    GO_TO_SENDER_PAGE(new GoToSenderPageCommand(), ADMIN),
    SEND_MESSAGE(new SendMessageCommand(), ADMIN),
    UPLOAD_IMAGE(new UploadImageCommand(), APPLICANT, ADMIN),
    GO_TO_CONFIRM_EMAIL_PAGE(new SendMessageCommand(), APPLICANT),
    GO_TO_PROFILE_PAGE(new GoToProfilePageCommand(), APPLICANT, ADMIN),
    PICK_UP_DOCUMENTS(new PickUpDocumentsCommand(), APPLICANT),
    GO_TO_EDIT_RECRUITMENT_PAGE(new GoToEditRecruitmentPageCommand(), ADMIN),
    EDIT_RECRUITMENT(new UpdateRecruitmentCommand(), ADMIN),
    GO_TO_EDIT_ADMINISTRATOR_DATA(new GoToEditAdministratorDataCommand(), ADMIN),
    UPDATE_ADMINISTRATOR_DATA(new UpdateAdministratorDataCommand(), ADMIN),
    GO_TO_SHOW_FACULTIES(new GoToShowFacultiesPageCommand(), ADMIN);

    private Set<Account.Role> roles;
    private Command command;

    CommandType(Command command, Account.Role... roles) {
        this.command = command;
        this.roles = Set.of(roles);
    }


    public Command getCommand() {
        return command;
    }


    public boolean hasAccountRole(Account.Role role) {
        return roles.stream().anyMatch(r -> r == role);

    }
}
