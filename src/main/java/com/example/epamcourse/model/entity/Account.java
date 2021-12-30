package com.example.epamcourse.model.entity;

public class Account extends BaseEntity {
    private Long accountId;
    private String login;
    private String password;
    private String email;
    private Role role;


    public enum Role {
        ADMIN,
        APPLICANT;
    }

    public Account(Long accountId, String login, String password, String email, Role role) {
        this.accountId = accountId;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        if (login != null ? !login.equals(account.login) : account.login != null) return false;
        if (password != null ? !password.equals(account.password) : account.password != null) return false;
        if (role != null ? !role.equals(account.role) : account.role != null) return false;
        return email != null ? email.equals(account.email) : account.email == null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Long.hashCode(accountId);
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringData = new StringBuilder();
        stringData.append("Account{")
                .append("accountId=")
                .append(accountId)
                .append(", login='")
                .append(login)
                .append(", password='")
                .append(password)
                .append(", email='")
                .append(email)
                .append(", role=")
                .append(role)
                .append('}');
        return stringData.toString();
    }

    public static class AccountBuilder {

        private Long accountId;
        private String login;
        private String password;
        private String email;
        private Role role;

        public AccountBuilder setAccountId(Long accountId) {
            this.accountId = accountId;
            return this;
        }

        public AccountBuilder setLogin(String login) {
            this.login = login;
            return this;
        }

        public AccountBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public AccountBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public AccountBuilder setRole(Role role) {
            this.role = role;
            return this;
        }


        public Account createAccount() {
            return new Account(accountId, login, password, email, role);
        }
    }
}

