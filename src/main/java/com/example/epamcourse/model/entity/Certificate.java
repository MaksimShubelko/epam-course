package com.example.epamcourse.model.entity;

public class Certificate extends BaseEntity {
    private Long certificateId;
    private Double totalMark;

    public Certificate(Long certificateId, Double totalMark) {
        this.certificateId = certificateId;
        this.totalMark = totalMark;
    }

    public Certificate() {
    }

    public Certificate(Double totalMark) {
        this.totalMark = totalMark;
    }

    public Long getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(Long certificateId) {
        this.certificateId = certificateId;
    }

    public Double getTotalMark() {
        return totalMark;
    }

    public void setTotalMark(Double totalMark) {
        this.totalMark = totalMark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Certificate)) return false;
        Certificate certificate = (Certificate) o;
        return totalMark != null ? totalMark.equals(certificate.totalMark) : certificate.totalMark == null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Long.hashCode(certificateId);
        result = prime * result + ((totalMark == null) ? 0 : totalMark.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringData = new StringBuilder();
        stringData.append("Certificate{")
                .append("certificateId=")
                .append(certificateId)
                .append(", totalMark='")
                .append(totalMark)
                .append('}');

        return stringData.toString();
    }
}
