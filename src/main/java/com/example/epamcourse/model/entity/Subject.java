package com.example.epamcourse.model.entity;

public class Subject extends BaseEntity {
    private Long subjectId;
    private Long applicantId;
    private Type subjectType;
    private Integer mark;

    public Subject() {

    }

    public enum Type {
        MATH,
        PHYSIC,
        ENGLISH;
    }

    public Subject(Long subjectId, Long applicantId, Type subjectType, Integer mark) {
        this.subjectId = subjectId;
        this.applicantId = applicantId;
        this.subjectType = subjectType;
        this.mark = mark;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Type getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(Type subjectType) {
        this.subjectType = subjectType;
    }

    public Integer getMark() {
        return mark;
    }

    public Long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Long applicant_id) {
        this.applicantId = applicant_id;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subject)) return false;
        Subject subject = (Subject) o;
        if (subjectType != null ? !subjectType.equals(subject.subjectType) : subject.subjectType != null) return false;
        if (applicantId != null ? !applicantId.equals(subject.applicantId) : subject.applicantId != null) return false;
        return mark != null ? mark.equals(subject.mark) : subject.mark == null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Long.hashCode(subjectId);
        result = prime * result + ((subjectType == null) ? 0 : subjectType.hashCode());
        result = prime * result + ((mark == null) ? 0 : mark.hashCode());
        result = prime * result + ((applicantId == null) ? 0 : applicantId.hashCode());

        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringData = new StringBuilder();
        stringData.append("Subject{")
                .append("subjectId=")
                .append(subjectId)
                .append(", subjectType=")
                .append(subjectType)
                .append(", applicantId=")
                .append(applicantId)
                .append(", mark=")
                .append(mark)
                .append('}');

        return stringData.toString();
    }
}
