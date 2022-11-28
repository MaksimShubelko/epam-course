package com.example.epamcourse.model.entity;

/**
 * class Subject
 *
 * @author M.Shubelko
 */
public class Subject extends BaseEntity {
    private long subjectId;
    private long applicantId;
    private SubjectType subjectType;
    private int mark;

    /**
     * The public constructor
     */
    public Subject() {

    }

    /**
     * The types of subjects
     */
    public enum SubjectType {
        MATH,
        PHYSIC,
        ENGLISH;
    }

    /**
     * The public constructor
     */
    public Subject(long subjectId, long applicantId, SubjectType subjectType, int mark) {
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
     * Get subject subjectType
     *
     * @return subjectType the subject subjectType
     */
    public SubjectType getSubjectType() {
        return subjectType;
    }

    /**
     * Set subject subjectType
     *
     * @param subjectType the subject subjectType
     */
    public void setSubjectType(SubjectType subjectType) {
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
        if (o == null) return false;
        if (!(o instanceof Subject)) return false;
        Subject subject = (Subject) o;
        if (subjectId != subject.subjectId) return false;
        if (subjectType != null ? !subjectType.equals(subject.subjectType) : subject.subjectType != null) return false;
        if (applicantId != subject.applicantId) return false;
        return mark == subject.mark;
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
        result = prime * result + Long.hashCode(applicantId);
        result = prime * result + Integer.hashCode(mark);
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
