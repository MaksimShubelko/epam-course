package com.example.epamcourse.model.entity;

/**
 * class Account
 *
 * @author M.Shubelko
 */
public class Bill extends BaseEntity {
    private long billId;
    private long facultyId;
    private long applicantId;
    private boolean archive;

    /**
     * The public constructor
     */
    public Bill(long billId, long facultyId, long applicantId, boolean archive) {
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
        if (o == null) return false;
        if (!(o instanceof Bill)) return false;
        Bill bill = (Bill) o;
        if (billId != bill.billId) return false;
        if (facultyId != bill.facultyId) return false;
        if (applicantId != bill.applicantId) return false;
        return archive != bill.archive;
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
        result = prime * result + Long.hashCode(facultyId);
        result = prime * result + Long.hashCode(applicantId);
        result = prime * result + Boolean.hashCode(archive);
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
