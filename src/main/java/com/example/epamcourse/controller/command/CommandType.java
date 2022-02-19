package com.example.epamcourse.controller.command;

import com.example.epamcourse.controller.command.impl.*;
import com.example.epamcourse.controller.command.impl.go.*;
import com.example.epamcourse.model.entity.Account;

import java.util.Set;

import static com.example.epamcourse.model.entity.Account.Role.*;

/**
 * class CommandType
 *
 * @author M.Shubelko
 */
public enum CommandType {

    /** The login into account **/
    LOGIN(new LoginCommand(), NOT_AUTOMATED, APPLICANT, ADMIN),

    /** The registration **/
    CREATE_ACCOUNT(new CreateAccountCommand(), NOT_AUTOMATED),

    /** Go to login page **/
    GO_TO_LOGIN_PAGE(new GoToLoginPageCommand(), NOT_AUTOMATED, APPLICANT, ADMIN),

    /** Go to registration page **/
    GO_TO_REGISTRATION_PAGE(new GoToRegistrationPageCommand(), NOT_AUTOMATED),

    /** Go to exception page **/
    TO_EXCEPTION_PAGE(new GoToExceptionPageCommand(), NOT_AUTOMATED, APPLICANT, ADMIN),

    /** Change locale **/
    LOCALE_COMMAND(new LocaleCommand(), NOT_AUTOMATED, APPLICANT, ADMIN),

    /** The adding of secure information for applicant **/
    APPLICANT_ADD_SECURE_INFORMATION(new AddApplicantSecureInformationCommand(), APPLICANT),

    /** The adding of secure information for administrator **/
    ADMINISTRATOR_ADD_SECURE_INFORMATION(new AddAdministratorSecureInformationCommand(), ADMIN),

    /** Go to main page applicant **/
    GO_TO_MAIN_PAGE_APPLICANT(new GoToMainPageApplicantCommand(), APPLICANT),

    /** Go to adding request page **/
    GO_TO_ADD_REQUEST_PAGE(new GoToAddRequestPageCommand(), APPLICANT),

    /** Add request **/
    ADD_REQUEST(new AddRequestCommand(), APPLICANT),

    /** Show requests **/
    SHOW_REQUESTS_COMMAND(new ShowRequestsCommand(), APPLICANT),

    /** Go to edit applicant data page **/
    GO_TO_EDIT_APPLICANT_DATA(new GoToEditApplicantDataPageCommand(), APPLICANT),

    /** Logout **/
    LOGOUT(new LogoutCommand(), APPLICANT, ADMIN),

    /** Update applicant data **/
    UPDATE_APPLICANT_DATA(new UpdateApplicantDataCommand(), APPLICANT),

    /** Go to edit edition faculty page **/
    GO_TO_EDIT_FACULTY(new GoToEditFacultyPageCommand(), ADMIN),

    /** Edit faculty **/
    EDIT_FACULTY(new EditFacultyCommand(), ADMIN),

    /** Delete faculty **/
    DELETE_FACULTY(new DeleteFacultyCommand(), ADMIN),

    /** Go to adding faculty page **/
    GO_TO_ADD_FACULTY_PAGE(new GoToAddFacultyPageCommand(), ADMIN),

    /** Add faculty **/
    ADD_FACULTY(new AddFacultyCommand(), ADMIN),

    /** Change account status **/
    CHANGE_ACCOUNT_STATUS(new ChangeAccountStatusCommand(), ADMIN),

    /** Delete account **/
    DELETE_ACCOUNT(new DeleteAccountCommand(), ADMIN),

    /** Go to creation admin account **/
    GO_TO_ADD_ADMIN_ACCOUNT(new GoToAddingAdminAccountPageCommand(), ADMIN),

    /** Go to uploading image page **/
    GO_TO_UPLOAD_IMAGE_PAGE(new GoToUploadImagePageCommand(), APPLICANT, ADMIN),

    /** Add admin account **/
    ADD_ADMIN_ACCOUNT(new AddAdminAccountCommand(), ADMIN),

    /** Go to showing applicants page **/
    GO_TO_SHOW_APPLICANTS(new GoToShowApplicantsPageCommand(), ADMIN),

    /** Go to showing accounts **/
    GO_TO_SHOW_ACCOUNTS(new GoToShowAccountsPageCommand(), ADMIN),

    /** Confirm email **/
    CONFIRM_EMAIL(new SendEmailCodeCommand(), NOT_AUTOMATED),

    /** Go to sender page **/
    GO_TO_SENDER_PAGE(new GoToSenderPageCommand(), ADMIN),

    /** Send message **/
    SEND_MESSAGE(new SendMessageCommand(), ADMIN),

    /** Upload image **/
    UPLOAD_IMAGE(new UploadImageCommand(), APPLICANT, ADMIN),

    /** Go to confirm email page **/
    GO_TO_CONFIRM_EMAIL_PAGE(new SendMessageCommand(), APPLICANT),

    /** Go to profile page **/
    GO_TO_PROFILE_PAGE(new GoToProfilePageCommand(), APPLICANT, ADMIN),

    /** Pick up documents **/
    PICK_UP_DOCUMENTS(new PickUpDocumentsCommand(), APPLICANT),

    /** Go to edition recruitment page **/
    GO_TO_EDIT_RECRUITMENT_PAGE(new GoToEditRecruitmentPageCommand(), ADMIN),

    /** Edit recruitment **/
    EDIT_RECRUITMENT(new UpdateRecruitmentCommand(), ADMIN),

    /** Go to edition administrator data page **/
    GO_TO_EDIT_ADMINISTRATOR_DATA(new GoToEditAdministratorDataPageCommand(), ADMIN),

    /** Update administrator data **/
    UPDATE_ADMINISTRATOR_DATA(new UpdateAdministratorDataCommand(), ADMIN),

    /** Go to showing faculties page **/
    GO_TO_SHOW_FACULTIES(new GoToShowFacultiesPageCommand(), ADMIN);

    /** The roles for commands **/
    private Set<Account.Role> roles;

    /** Commands **/
    private Command command;

    /**
     * Instantiates a new command type and roles.
     *
     * @param command the command
     * @param roles the roles
     */
    CommandType(Command command, Account.Role... roles) {
        this.command = command;
        this.roles = Set.of(roles);
    }


    /**
     * Gets the command.
     *
     * @return the command
     */
    public Command getCommand() {
        return command;
    }

    /**
     * Gets the command.
     *
     * @return the command
     */
    public boolean hasAccountRole(Account.Role role) {
        return roles.stream().anyMatch(r -> r == role);

    }
}
