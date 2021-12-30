package com.example.epamcourse.model.entity;


public class Applicant extends BaseEntity {
    private Long applicantId;
    private Long accountId;
    private Boolean isBeneficiary;
    //private Integer totalMarkSubjects;
    private String firstname;
    private String lastname;
    private String surname;

    public Applicant(Long applicantId, Long accountId,
                     Boolean isBeneficiary,
                     String firstname, String lastname, String surname) {
        this.applicantId = applicantId;
        this.accountId = accountId;
        this.isBeneficiary = isBeneficiary;
        //this.totalMarkSubjects = totalMarkSubjects;
        this.firstname = firstname;
        this.lastname = lastname;
        this.surname = surname;
    }

    public Long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Long applicantId) {
        this.applicantId = applicantId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Boolean getBeneficiary() {
        return isBeneficiary;
    }

    public void setBeneficiary(Boolean beneficiary) {
        isBeneficiary = beneficiary;
    }

    //public Integer getTotalMarkSubjects() {
        //return totalMarkSubjects;
    //}

    //public void setTotalMarkSubjects(Integer totalMarkSubjects) {
        //this.totalMarkSubjects = totalMarkSubjects;
    //}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Applicant)) return false;
        Applicant applicant = (Applicant) o;
        if (accountId != null ? !accountId.equals(applicant.accountId) : applicant.accountId != null) return false;
        if (isBeneficiary != null ? !isBeneficiary.equals(applicant.isBeneficiary) : applicant.isBeneficiary != null)
            return false;
        //if (totalMarkSubjects != null ? !totalMarkSubjects.equals(applicant.totalMarkSubjects) : applicant.totalMarkSubjects != null)
            //return false;
        if (firstname != null ? !firstname.equals(applicant.firstname) : applicant.firstname != null) return false;
        if (lastname != null ? !lastname.equals(applicant.lastname) : applicant.lastname != null) return false;
        return surname != null ? surname.equals(applicant.surname) : applicant.surname == null;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Long.hashCode(applicantId);
        result = prime * result + ((surname == null) ? 0 : surname.hashCode());
        result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
        result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
        result = prime * result + ((accountId == null) ? 0 : accountId.hashCode());
        result = prime * result + ((isBeneficiary == null) ? 0 : isBeneficiary.hashCode());
        //result = prime * result + ((totalMarkSubjects == null) ? 0 : totalMarkSubjects.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringData = new StringBuilder();
        stringData.append("Applicant{")
                .append("applicantId=")
                .append(applicantId)
                .append(", account_id=")
                .append(accountId)
                .append(", totalMarkSubjects=")
                .append(isBeneficiary)
                .append(", totalMarkSubjects=")
                //.append(totalMarkSubjects)
                .append(firstname)
                .append(", lastname='")
                .append(lastname)
                .append(", surname='")
                .append(surname)
                .append('}');
        return stringData.toString();
    }

    public static class ApplicantBuilder {

        private Long applicantId;
        private Long account_id;
        private Boolean isBeneficiary;
        //private Integer totalMarkSubjects;
        private String firstname;
        private String lastname;
        private String surname;

        public Applicant.ApplicantBuilder setApplicantId(Long applicantId) {
            this.applicantId = applicantId;
            return this;
        }

        public Applicant.ApplicantBuilder setFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public Applicant.ApplicantBuilder setLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public Applicant.ApplicantBuilder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Applicant.ApplicantBuilder setAccountId(Long account_id) {
            this.account_id = account_id;
            return this;
        }

        public Applicant.ApplicantBuilder setBeneficiary(Boolean isBeneficiary) {
            this.isBeneficiary = isBeneficiary;
            return this;
        }

       /* public Applicant.ApplicantBuilder setTotalMarkSubject(Integer totalMarkSubjects) {
            this.totalMarkSubjects = totalMarkSubjects;
            return this;
        }*/

        public Applicant createApplicant() {
            return new Applicant(applicantId, account_id, isBeneficiary, firstname, lastname, surname);
        }
    }
}
