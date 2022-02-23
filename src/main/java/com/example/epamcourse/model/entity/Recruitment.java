package com.example.epamcourse.model.entity;

import java.time.LocalDateTime;

/**
 * class Recruitment
 *
 * @author M.Shubelko
 */
public class Recruitment extends BaseEntity {
    Long recruitmentId;
    Boolean recruitmentStatus;
    LocalDateTime finishRecruitment;

    /**
     * The public constructor
     */
    public Recruitment(Long recruitmentId, Boolean recruitmentStatus, LocalDateTime finishRecruitment) {
        this.recruitmentId = recruitmentId;
        this.recruitmentStatus = recruitmentStatus;
        this.finishRecruitment = finishRecruitment;
    }

    /**
     * Get recruitment id
     *
     * @return recruitmentId the recruitment id
     */
    public Long getRecruitmentId() {
        return recruitmentId;
    }

    /**
     * Get recruitment status
     *
     * @return recruitmentStatus the recruitment status
     */
    public Boolean getRecruitmentStatus() {
        return recruitmentStatus;
    }

    /**
     * Get finish recruitment
     *
     * @return finishRecruitment the finish recruitment
     */
    public LocalDateTime getFinishRecruitment() {
        return finishRecruitment;
    }

    /**
     * Set recruitment id
     *
     * @param recruitmentId the recruitment id
     */
    public void setRecruitmentId(Long recruitmentId) {
        this.recruitmentId = recruitmentId;
    }

    /**
     * Set recruitment status
     *
     * @param recruitmentStatus the recruitment status
     */
    public void setRecruitmentStatus(Boolean recruitmentStatus) {
        this.recruitmentStatus = recruitmentStatus;
    }

    /**
     * Set finish recruitment
     *
     * @param finishRecruitment the finish recruitment
     */
    public void setFinishRecruitment(LocalDateTime finishRecruitment) {
        this.finishRecruitment = finishRecruitment;
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
        if (!(o instanceof Subject)) return false;
        Recruitment recruitment = (Recruitment) o;
        if (recruitmentStatus != null ? !recruitmentStatus.equals(recruitment.recruitmentStatus) : recruitment.recruitmentStatus != null) return false;
        return (finishRecruitment != null ? !finishRecruitment.equals(recruitment.finishRecruitment) : recruitment.finishRecruitment != null);
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
        result = prime * result + Long.hashCode(recruitmentId);
        result = prime * result + ((recruitmentStatus == null) ? 0 : recruitmentStatus.hashCode());
        result = prime * result + ((finishRecruitment == null) ? 0 : finishRecruitment.hashCode());
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
        stringData.append("Recruitment{")
                .append("recruitmentId=")
                .append(recruitmentId)
                .append(", recruitmentStatus=")
                .append(recruitmentStatus)
                .append(", finishRecruitment=")
                .append(finishRecruitment)
                .append('}');
        return stringData.toString();
    }
}
