package com.example.epamcourse.model.entity;

/**
 * class Account
 *
 * @author M.Shubelko
 */
public class Administrator extends BaseEntity {
    private Long administratorId;
    private String firstname;
    private String lastname;
    private String surname;
    private Long accountId;

    /**
     * The public constructor
     */
    public Administrator(Long administratorId, String firstname, String lastname, String surname, Long accountId) {
        this.administratorId = administratorId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.surname = surname;
        this.accountId = accountId;
    }

    /**
     * The public constructor
     */
    public Administrator() {
    }

    /**
     * Get administrator id
     *
     * @return administratorId the administrator id
     */
    public Long getAdministratorId() {
        return administratorId;
    }

    /**
     * Set administrator id
     *
     * @param administratorId the administrator id
     */
    public void setAdministratorId(Long administratorId) {
        this.administratorId = administratorId;
    }

    /**
     * Get firstname
     *
     * @return firstname the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Set firstname
     *
     * @param firstname the firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Get firstname
     *
     * @return firstname the firstname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Set lastname
     *
     * @param lastname the lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Get surname
     *
     * @return surname the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Set surname
     *
     * @param surname the surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Get account id
     *
     * @return accountId the accountId
     */
    public Long getAccountId() {
        return accountId;
    }

    /**
     * Set account id
     *
     * @param accountId the accountId
     */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * The equals
     *
     * @param o the object
     * @return equaling
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Administrator)) return false;
        Administrator administrator = (Administrator) o;
        if (firstname != null ? !firstname.equals(administrator.firstname) : administrator.firstname != null) return false;
        if (lastname != null ? !lastname.equals(administrator.lastname) : administrator.lastname != null) return false;
        if (surname != null ? !surname.equals(administrator.surname) : administrator.surname != null) return false;
        return accountId != null ? accountId.equals(administrator.accountId) : administrator.accountId == null;
    }

    /**
     * The hashcode
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Long.hashCode(administratorId);
        result = prime * result + ((surname == null) ? 0 : surname.hashCode());
        result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
        result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
        result = prime * result + ((accountId == null) ? 0 : accountId.hashCode());
        return result;
    }

    /**
     * The toString
     *
     * @return string
     */
    @Override
    public String toString() {
        StringBuilder stringData = new StringBuilder();
        stringData.append("Administrator{")
                .append("administratorId=")
                .append(administratorId)
                .append(", firstname='")
                .append(firstname)
                .append(", lastname='")
                .append(lastname)
                .append(", surname='")
                .append(surname)
                .append(", account_id=")
                .append(accountId)
                .append('}');
        return stringData.toString();
    }

    /**
     * static class AdministratorBuilder
     */
    public static class AdministratorBuilder {

        private Long administratorId;
        private String firstname;
        private String lastname;
        private String surname;
        private Long account_id;

        /**
         * Set administrator id
         *
         * @param administratorId the administrator id
         * @return AdministratorBuilder
         */
        public Administrator.AdministratorBuilder setAdministratorId(Long administratorId) {
            this.administratorId = administratorId;
            return this;
        }

        /**
         * Set firstname
         *
         * @param firstname the firstname
         * @return AdministratorBuilder
         */
        public Administrator.AdministratorBuilder setFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        /**
         * Set lastname
         *
         * @param lastname the lastname
         * @return AdministratorBuilder
         */
        public Administrator.AdministratorBuilder setLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        /**
         * Set surname
         *
         * @param surname the surname
         * @return AdministratorBuilder
         */
        public Administrator.AdministratorBuilder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        /**
         * Set account
         *
         * @param accountId the account id
         * @return AdministratorBuilder
         */
        public Administrator.AdministratorBuilder setAccountId(Long accountId) {
            this.account_id = accountId;
            return this;
        }

        /**
         * Create administrator
         *
         * @return Administrator
         */
        public Administrator createAdministrator() {
            return new Administrator(administratorId, firstname, lastname, surname, account_id);
        }
    }
}
