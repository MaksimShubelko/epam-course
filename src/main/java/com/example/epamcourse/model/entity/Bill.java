package com.example.epamcourse.model.entity;

public class Bill extends BaseEntity {
    private Long billId;
    private Long facultyId;
    private Long applicantId;

    public Bill(Long billId, Long facultyId, Long applicantId) {
        this.billId = billId;
        this.applicantId = applicantId;
        this.facultyId = facultyId;
    }

    public Bill(Long applicantId) {
        this.applicantId = applicantId;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public Long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Long applicantId) {
        this.applicantId = applicantId;
    }

    /*public Integer getTotalMark() {
        return totalMark;
    }

    public void setTotalMark(Integer totalMark) {
        this.totalMark = totalMark;
    }*/

    public Long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bill)) return false;
        Bill bill = (Bill) o;
        if (facultyId != null ? !facultyId.equals(bill.facultyId) : bill.facultyId != null) return false;
        return (applicantId != null ? !applicantId.equals(bill.applicantId) : bill.applicantId != null);
        /*totalMark != null ? totalMark.equals(bill.totalMark) : bill.totalMark == null;*/
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Long.hashCode(billId);
        result = prime * result + ((applicantId == null) ? 0 : applicantId.hashCode());
/*
        result = prime * result + ((totalMark == null) ? 0 : totalMark.hashCode());
*/
        result = prime * result + ((facultyId == null) ? 0 : facultyId.hashCode());

        return result;
    }

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
                .append('}');

        return stringData.toString();
    }
}
