package com.example.epamcourse.model.entity;

/**
 * class Account
 *
 * @author M.Shubelko
 */
public class Applicant extends BaseEntity {
    private Long applicantId;
    private Long accountId;
    private Boolean isBeneficiary = false;
    private Long certificateId;
    private String firstname;
    private String lastname;
    private String surname;

    /**
     * The public constructor
     */
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

    /**
     * The public constructor
     */
    public Applicant() {

    }

    /**
     * Get applicant id
     *
     * @return applicantId the applicant id
     */
    public Long getApplicantId() {
        return applicantId;
    }

    /**
     * Set applicant id
     *
     * @param applicantId the applicant id
     */
    public void setApplicantId(Long applicantId) {
        this.applicantId = applicantId;
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
     * Get beneficiary
     *
     * @return isBeneficiary the beneficiary
     */
    public Boolean getBeneficiary() {
        return isBeneficiary;
    }

    /**
     * Set beneficiary
     *
     * @param beneficiary the beneficiary
     */
    public void setBeneficiary(Boolean beneficiary) {
        isBeneficiary = beneficiary;
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
     * Get certificate id
     *
     * @return certificateId the certificate id
     */
    public Long getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(Long certificateId) {
        this.certificateId = certificateId;
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
        if (!(o instanceof Applicant)) return false;
        Applicant applicant = (Applicant) o;
        if (accountId != null ? !accountId.equals(applicant.accountId) : applicant.accountId != null) return false;
        if (isBeneficiary != null ? !isBeneficiary.equals(applicant.isBeneficiary) : applicant.isBeneficiary != null)
            return false;
        if (firstname != null ? !firstname.equals(applicant.firstname) : applicant.firstname != null) return false;
        if (lastname != null ? !lastname.equals(applicant.lastname) : applicant.lastname != null) return false;
        return surname != null ? surname.equals(applicant.surname) : applicant.surname == null;
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
        result = prime * result + Long.hashCode(applicantId);
        result = prime * result + ((surname == null) ? 0 : surname.hashCode());
        result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
        result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
        result = prime * result + ((accountId == null) ? 0 : accountId.hashCode());
        result = prime * result + ((isBeneficiary == null) ? 0 : isBeneficiary.hashCode());
        //result = prime * result + ((totalMarkSubjects == null) ? 0 : totalMarkSubjects.hashCode());
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

    /**
     * static class ApplicantBuilder
     */
    public static class ApplicantBuilder {

        private Long applicantId;
        private Long accountId;
        private Boolean isBeneficiary;
        private Long certificateId;
        private String firstname;
        private String lastname;
        private String surname;

        /**
         * Set applicant id
         *
         * @param applicantId the applicant id
         * @return AdministratorBuilder
         */
        public Applicant.ApplicantBuilder setApplicantId(Long applicantId) {
            this.applicantId = applicantId;
            return this;
        }

        /**
         * Set firstname
         *
         * @param firstname the firstname
         * @return ApplicantBuilder
         */
        public Applicant.ApplicantBuilder setFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        /**
         * Set lastname
         *
         * @param lastname the lastname
         * @return ApplicantBuilder
         */
        public Applicant.ApplicantBuilder setLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        /**
         * Set surname
         *
         * @param surname the surname
         * @return ApplicantBuilder
         */
        public Applicant.ApplicantBuilder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        /**
         * Set account
         *
         * @param accountId the account id
         * @return ApplicantBuilder
         */
        public Applicant.ApplicantBuilder setAccountId(Long accountId) {
            this.accountId = accountId;
            return this;
        }

        /**
         * Set beneficiary
         *
         * @param isBeneficiary the beneficiary
         * @return ApplicantBuilder
         */
        public Applicant.ApplicantBuilder setBeneficiary(Boolean isBeneficiary) {
            this.isBeneficiary = isBeneficiary;
            return this;
        }

        /**
         * Set certificate id
         *
         * @param certificateId the certificate id
         * @return ApplicantBuilder
         */
        public Applicant.ApplicantBuilder setCertificateId(Long certificateId) {
            this.certificateId = certificateId;
            return this;
        }

        /**
         * Create applicant
         *
         * @return Applicant
         */
        public Applicant createApplicant() {
            return new Applicant(applicantId, accountId, isBeneficiary,
                    firstname, lastname, surname, certificateId);
        }
    }
}
