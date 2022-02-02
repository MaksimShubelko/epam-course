package com.example.epamcourse.model.entity;


public class Applicant extends BaseEntity {
    private Long applicantId;
    private Long accountId;
    private Boolean isBeneficiary = false;
    private Long certificateId;
    private String firstname;
    private String lastname;
    private String surname;

    public Applicant(Long applicantId, Long accountId,
                     Boolean isBeneficiary,
                     String firstname, String lastname, String surname, Long certificateId) {
        this.applicantId = applicantId;
        this.accountId = accountId;
        this.isBeneficiary = isBeneficiary;
        this.firstname = firstname;
        this.lastname = lastname;
        this.surname = surname;
        this.certificateId = certificateId;
    }

    public Applicant() {

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

    public Long getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(Long certificateId) {
        this.certificateId = certificateId;
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
                .append(", isBeneficiary=")
                .append(isBeneficiary)
                .append(", firstname=")
                .append(firstname)
                .append(", surname='")
                .append(surname)
                .append(", lastname='")
                .append(lastname)
                .append(", certificateId='")
                .append(certificateId)
                .append('}');
        return stringData.toString();
    }

    public static class ApplicantBuilder {

        private Long applicantId;
        private Long accountId;
        private Boolean isBeneficiary;
        private Long certificateId;
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

        public Applicant.ApplicantBuilder setAccountId(Long accountId) {
            this.accountId = accountId;
            return this;
        }

        public Applicant.ApplicantBuilder setBeneficiary(Boolean isBeneficiary) {
            this.isBeneficiary = isBeneficiary;
            return this;
        }

        public Applicant.ApplicantBuilder setCertificateId(Long certificateId) {
            this.certificateId = certificateId;
            return this;
        }

        public Applicant createApplicant() {
            return new Applicant(applicantId, accountId, isBeneficiary,
                    firstname, lastname, surname, certificateId);
        }
    }
}
