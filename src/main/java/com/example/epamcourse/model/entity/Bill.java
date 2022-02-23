package com.example.epamcourse.model.entity;

/**
 * class Account
 *
 * @author M.Shubelko
 */
public class Bill extends BaseEntity {
    private Long billId;
    private Long facultyId;
    private Long applicantId;
    private Boolean archive;

    /**
     * The public constructor
     */
    public Bill(Long billId, Long facultyId, Long applicantId, Boolean archive) {
        this.billId = billId;
        this.applicantId = applicantId;
        this.facultyId = facultyId;
        this.archive = archive;
    }

    /**
     * The public constructor
     */
    public Bill(Long applicantId) {
        this.applicantId = applicantId;
    }

    /**
     * Get bill id
     *
     * @return billId the bill id
     */
    public Long getBillId() {
        return billId;
    }

    /**
     * Set bill id
     *
     * @param billId the bill id
     */
    public void setBillId(Long billId) {
        this.billId = billId;
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
     * Get faculty id
     *
     * @return facultyId the faculty id
     */
    public Long getFacultyId() {
        return facultyId;
    }

    /**
     * Set bill id
     *
     * @param facultyId the faculty id
     */
    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
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
        if (!(o instanceof Bill)) return false;
        Bill bill = (Bill) o;
        if (archive != null ? !archive.equals(bill.archive) : bill.archive != null) return false;
        if (facultyId != null ? !facultyId.equals(bill.facultyId) : bill.facultyId != null) return false;
        return (applicantId != null ? !applicantId.equals(bill.applicantId) : bill.applicantId != null);
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
        result = prime * result + Long.hashCode(billId);
        result = prime * result + ((applicantId == null) ? 0 : applicantId.hashCode());
        result = prime * result + Boolean.hashCode(archive);
        result = prime * result + ((facultyId == null) ? 0 : facultyId.hashCode());

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
        stringData.append("Bill{")
                .append("billId=")
                .append(billId)
                .append(", applicantId=")
                .append(applicantId)
                .append(", facultyId=")
                .append(facultyId)
                .append(", archive=")
                .append(archive)
                .append('}');

        return stringData.toString();
    }
}
