package com.example.epamcourse.model.entity;

/**
 * class Account
 *
 * @author M.Shubelko
 */
public class Account extends BaseEntity {
    private long accountId;
    private String login;
    private String email;
    private Role role;
    private Status status;
    private String ip;
    private String imagePath;

    /**
     * Account's roles
     */
    public enum Role {
        NOT_AUTOMATED,
        ADMIN,
        APPLICANT;
    }

    /**
     * Account's statuses
     */
    public enum Status {
        ACTIVE,
        BLOCKED;
    }

    /**
     * The public constructor
     */
    public Account(Long accountId, String login,
                   String email, String password,
                   Role role, Status status,
                   String ip, String imagePath) {
        this.accountId = accountId;
        this.login = login;
        this.email = email;
        this.role = role;
        this.status = status;
        this.ip = ip;
        this.imagePath = imagePath;
    }

    public Account() {
    }

    /**
     * Get accountId
     *
     * @return accountId the account id
     */
    public Long getAccountId() {
        return accountId;
    }

    /**
     * Set account id
     *
     * @param accountId the account id
     */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * Get login
     *
     * @return accountId the account id
     */
    public String getLogin() {
        return login;
    }

    /**
     * Get image path
     *
     * @return imagePath the image path
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Set image path
     *
     * @param imagePath the image path
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * Set login
     *
     * @param login the login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Get image path
     *
     * @return imagePath the image path
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set email
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get role
     *
     * @return role the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Set role
     *
     * @param role the role
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Get status
     *
     * @return status the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Set status
     *
     * @return status the status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Get ip
     *
     * @return ip the image ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * Set ip
     *
     * @param ip the ip
     */
    public void setIp(String ip) {
        this.ip = ip;
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
        if (o == null) return false;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        if (accountId != account.accountId) return false;
        if (login != null ? !login.equals(account.login) : account.login != null) return false;
        if (imagePath != null ? !imagePath.equals(account.imagePath) : account.imagePath != null) return false;
        if (role != null ? !role.equals(account.role) : account.role != null) return false;
        if (status != null ? status.equals(account.status) : account.status != null) return false;
        if (ip != null ? !ip.equals(account.ip) : account.ip != null) return false;
        return (imagePath != null ? !imagePath.equals(account.imagePath) : account.imagePath != null);

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
        result = prime * result + Long.hashCode(accountId);
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((ip == null) ? 0 : ip.hashCode());
        result = prime * result + ((imagePath == null) ? 0 : imagePath.hashCode());
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
        stringData.append("Account{")
                .append("accountId=").append(accountId)
                .append(", login='").append(login)
                .append(", email='").append(email)
                .append(", role=").append(role)
                .append(", status=").append(status)
                .append(", imagePath=").append(imagePath)
                .append(", ip=").append(ip)
                .append('}');
        return stringData.toString();
    }

    /**
     * static class AccountBuilder
     */
    public static class AccountBuilder {
        private Account account = new Account();

        /**
         * Set account id
         *
         * @param accountId the account id
         * @return AccountBuilder
         */
        public AccountBuilder setAccountId(Long accountId) {
            account.setAccountId(accountId);
            return this;
        }

        /**
         * Set login
         *
         * @param login the login
         * @return AccountBuilder
         */
        public AccountBuilder setLogin(String login) {
            account.setLogin(login);
            return this;
        }

        /**
         * Set email
         *
         * @param email the email
         * @return AccountBuilder
         */
        public AccountBuilder setEmail(String email) {
            account.setEmail(email);
            return this;
        }

        /**
         * Set role
         *
         * @param role the role
         * @return AccountBuilder
         */
        public AccountBuilder setRole(Role role) {
            account.setRole(role);
            return this;
        }

        /**
         * Set status
         *
         * @param status the status
         * @return AccountBuilder
         */
        public AccountBuilder setStatus(Status status) {
            account.setStatus(status);
            return this;
        }

        /**
         * Set ip
         *
         * @param ip the ip
         * @return AccountBuilder
         */
        public AccountBuilder setIp(String ip) {
            account.setIp(ip);
            return this;
        }

        /**
         * Set image path
         *
         * @param imagePath the image path
         * @return AccountBuilder
         */
        public AccountBuilder setImagePath(String imagePath) {
            account.setImagePath(imagePath);
            return this;
        }

        /**
         * Create account
         *
         * @return Account
         */
        public Account createAccount() {
            return account;
        }

    }
}

