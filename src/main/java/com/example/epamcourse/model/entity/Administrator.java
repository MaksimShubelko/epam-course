package com.example.epamcourse.model.entity;

public class Administrator extends BaseEntity {
    private Long administratorId;
    private String firstname;
    private String lastname;
    private String surname;
    private Long account_id;

    public Administrator(Long administratorId, String firstname, String lastname, String surname, Long account_id) {
        this.administratorId = administratorId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.surname = surname;
        this.account_id = account_id;
    }

    public Long getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(Long administratorId) {
        this.administratorId = administratorId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Administrator)) return false;
        Administrator administrator = (Administrator) o;
        if (firstname != null ? !firstname.equals(administrator.firstname) : administrator.firstname != null) return false;
        if (lastname != null ? !lastname.equals(administrator.lastname) : administrator.lastname != null) return false;
        if (surname != null ? !surname.equals(administrator.surname) : administrator.surname != null) return false;
        return account_id != null ? account_id.equals(administrator.account_id) : administrator.account_id == null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Long.hashCode(administratorId);
        result = prime * result + ((surname == null) ? 0 : surname.hashCode());
        result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
        result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
        result = prime * result + ((account_id == null) ? 0 : account_id.hashCode());
        return result;
    }

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
                .append(account_id)
                .append('}');
        return stringData.toString();
    }

    public static class AdministratorBuilder {

        private Long administratorId;
        private String firstname;
        private String lastname;
        private String surname;
        private Long account_id;

        public Administrator.AdministratorBuilder setAdministratorId(Long administratorId) {
            this.administratorId = administratorId;
            return this;
        }

        public Administrator.AdministratorBuilder setFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public Administrator.AdministratorBuilder setLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public Administrator.AdministratorBuilder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Administrator.AdministratorBuilder setAccountId(Long account_id) {
            this.account_id = account_id;
            return this;
        }


        public Administrator createAdministrator() {
            return new Administrator(administratorId, firstname, lastname, surname, account_id);
        }
    }
}
