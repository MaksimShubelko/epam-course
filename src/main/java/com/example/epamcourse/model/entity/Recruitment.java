package com.example.epamcourse.model.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Recruitment extends BaseEntity {
    Long recruitmentId;
    Boolean recruitmentStatus;
    LocalDateTime finishRecruitment;

    public Recruitment(Long recruitmentId, Boolean recruitmentStatus, LocalDateTime finishRecruitment) {
        this.recruitmentId = recruitmentId;
        this.recruitmentStatus = recruitmentStatus;
        this.finishRecruitment = finishRecruitment;
    }

    public Long getRecruitmentId() {
        return recruitmentId;
    }

    public Boolean getRecruitmentStatus() {
        return recruitmentStatus;
    }

    public LocalDateTime getFinishRecruitment() {
        return finishRecruitment;
    }

    public void setRecruitmentId(Long recruitmentId) {
        this.recruitmentId = recruitmentId;
    }

    public void setRecruitmentStatus(Boolean recruitmentStatus) {
        this.recruitmentStatus = recruitmentStatus;
    }

    public void setFinishRecruitment(LocalDateTime finishRecruitment) {
        this.finishRecruitment = finishRecruitment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subject)) return false;
        Recruitment recruitment = (Recruitment) o;
        if (recruitmentStatus != null ? !recruitmentStatus.equals(recruitment.recruitmentStatus) : recruitment.recruitmentStatus != null) return false;
        return (finishRecruitment != null ? !finishRecruitment.equals(recruitment.finishRecruitment) : recruitment.finishRecruitment != null);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Long.hashCode(recruitmentId);
        result = prime * result + ((recruitmentStatus == null) ? 0 : recruitmentStatus.hashCode());
        result = prime * result + ((finishRecruitment == null) ? 0 : finishRecruitment.hashCode());
        return result;
    }

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
