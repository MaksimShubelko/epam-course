package com.example.epamcourse.model.entity;

/**
 * class Certificate
 *
 * @author M.Shubelko
 */
public class Certificate extends BaseEntity {
    private Long certificateId;
    private Double totalMark;

    /**
     * The public constructor
     */
    public Certificate(Long certificateId, Double totalMark) {
        this.certificateId = certificateId;
        this.totalMark = totalMark;
    }

    /**
     * The public constructor
     */
    public Certificate() {
    }

    /**
     * Get certificate id
     *
     * @return certificateId the certificate id
     */
    public Long getCertificateId() {
        return certificateId;
    }

    /**
     * Set total mark
     *
     * @param certificateId the certificate id
     */
    public void setCertificateId(Long certificateId) {
        this.certificateId = certificateId;
    }

    /**
     * Get total mark
     *
     * @return totalMark the total mark
     */
    public Double getTotalMark() {
        return totalMark;
    }

    /**
     * Set total mark
     *
     * @param totalMark the total mark
     */
    public void setTotalMark(Double totalMark) {
        this.totalMark = totalMark;
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
        if (!(o instanceof Certificate)) return false;
        Certificate certificate = (Certificate) o;
        return totalMark != null ? totalMark.equals(certificate.totalMark) : certificate.totalMark == null;
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
        result = prime * result + Long.hashCode(certificateId);
        result = prime * result + ((totalMark == null) ? 0 : totalMark.hashCode());
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
        stringData.append("Certificate{")
                .append("certificateId=")
                .append(certificateId)
                .append(", totalMark='")
                .append(totalMark)
                .append('}');

        return stringData.toString();
    }
}
