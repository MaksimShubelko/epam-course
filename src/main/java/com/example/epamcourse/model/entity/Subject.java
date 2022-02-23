package com.example.epamcourse.model.entity;

/**
 * class Subject
 *
 * @author M.Shubelko
 */
public class Subject extends BaseEntity {
    private Long subjectId;
    private Long applicantId;
    private Type subjectType;
    private Integer mark;

    /**
     * The public constructor
     */
    public Subject() {

    }

    /**
     * The types of subjects
     */
    public enum Type {
        MATH,
        PHYSIC,
        ENGLISH;
    }

    /**
     * The public constructor
     */
    public Subject(Long subjectId, Long applicantId, Type subjectType, Integer mark) {
        this.subjectId = subjectId;
        this.applicantId = applicantId;
        this.subjectType = subjectType;
        this.mark = mark;
    }

    /**
     * Get subject id
     *
     * @return subjectId the subject id
     */
    public Long getSubjectId() {
        return subjectId;
    }

    /**
     * Set subject id
     *
     * @param subjectId the subject id
     */
    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * Get subject type
     *
     * @return subjectType the subject type
     */
    public Type getSubjectType() {
        return subjectType;
    }

    /**
     * Set subject type
     *
     * @param subjectType the subject type
     */
    public void setSubjectType(Type subjectType) {
        this.subjectType = subjectType;
    }

    /**
     * Get mark
     *
     * @return mark the mark
     */
    public Integer getMark() {
        return mark;
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
     * Set mark
     *
     * @param mark the mark
     */
    public void setMark(Integer mark) {
        this.mark = mark;
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
        Subject subject = (Subject) o;
        if (subjectType != null ? !subjectType.equals(subject.subjectType) : subject.subjectType != null) return false;
        if (applicantId != null ? !applicantId.equals(subject.applicantId) : subject.applicantId != null) return false;
        return mark != null ? mark.equals(subject.mark) : subject.mark == null;
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
        result = prime * result + Long.hashCode(subjectId);
        result = prime * result + ((subjectType == null) ? 0 : subjectType.hashCode());
        result = prime * result + ((mark == null) ? 0 : mark.hashCode());
        result = prime * result + ((applicantId == null) ? 0 : applicantId.hashCode());

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
