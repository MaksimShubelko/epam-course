package com.example.epamcourse.model.entity;

/**
 * class Certificate
 *
 * @author M.Shubelko
 */
public class Certificate extends BaseEntity {
    private long certificateId;
    private double totalMark;

    /**
     * The public constructor
     */
    public Certificate(long certificateId, double totalMark) {
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
        if (o == null) return false;
        if (!(o instanceof Certificate)) return false;
        Certificate certificate = (Certificate) o;
        if (certificateId != certificate.certificateId) return false;
        return totalMark == certificate.totalMark;
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
        result = prime * result + Double.hashCode(totalMark);
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
