package com.example.epamcourse.model.entity;

/**
 * class Faculty
 *
 * @author M.Shubelko
 */
public class Faculty extends BaseEntity {
    private long facultyId;
    private String facultyName;
    private int recruitmentPlanFree;
    private int recruitmentPlanCanvas;

    /**
     * The public constructor
     */
    public Faculty(long facultyId,
                   String facultyName, int recruitmentPlanFree,
                   int recruitmentPlanCanvas) {
        this.facultyId = facultyId;
        this.facultyName = facultyName;
        this.recruitmentPlanFree = recruitmentPlanFree;
        this.recruitmentPlanCanvas = recruitmentPlanCanvas;
    }

    /**
     * The public constructor
     */
    public Faculty() {

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
     * Set faculty id
     *
     * @param facultyId the faculty id
     */
    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    /**
     * Get faculty name
     *
     * @return facultyName the facultyName
     */
    public String getFacultyName() {
        return facultyName;
    }

    /**
     * Get recruitment plan free
     *
     * @return recruitmentPlanFree the recruitment plan free
     */
    public Integer getRecruitmentPlanFree() {
        return recruitmentPlanFree;
    }

    /**
     * Set recruitment plan free
     *
     * @param recruitmentPlanFree the recruitment plan free
     */
    public void setRecruitmentPlanFree(Integer recruitmentPlanFree) {
        this.recruitmentPlanFree = recruitmentPlanFree;
    }

    /**
     * Get recruitment plan canvas
     *
     * @return recruitmentPlanCanvas the recruitment plan canvas
     */
    public Integer getRecruitmentPlanCanvas() {
        return recruitmentPlanCanvas;
    }

    /**
     * Set recruitment plan canvas
     *
     * @param recruitmentPlanCanvas the recruitment plan canvas
     */
    public void setRecruitmentPlanCanvas(Integer recruitmentPlanCanvas) {
        this.recruitmentPlanCanvas = recruitmentPlanCanvas;
    }

    /**
     * Set faculty name
     *
     * @param facultyName the facultyName
     */
    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
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
        if (!(o instanceof Faculty)) return false;
        Faculty faculty = (Faculty) o;
        if (facultyId != faculty.facultyId) return false;
        if (facultyName != null ? !facultyName.equals(faculty.facultyName) : faculty.facultyName != null) return false;
        if (recruitmentPlanFree == faculty.recruitmentPlanFree) return false;
        return recruitmentPlanCanvas == faculty.recruitmentPlanCanvas;
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
        result = prime * result + Long.hashCode(facultyId);
        result = prime * result + ((facultyName == null) ? 0 : facultyName.hashCode());
        result = prime * result + Integer.hashCode(recruitmentPlanFree);
        result = prime * result + Integer.hashCode(recruitmentPlanCanvas);
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
        stringData.append("Faculty{")
                .append("facultyId=")
                .append(facultyId)
                .append(", facultyName='")
                .append(facultyName)
                .append(", recruitmentPlanFree=")
                .append(recruitmentPlanFree)
                .append(", recruitmentPlanCanvas=")
                .append(recruitmentPlanCanvas)
                .append('}');
        return stringData.toString();
    }

    /**
     * static class FacultyBuilder
     */
    public static class FacultyBuilder {
        private Faculty faculty = new Faculty();

        /**
         * Set faculty id
         *
         * @param facultyId the faculty id
         * @return FacultyBuilder
         */
        public Faculty.FacultyBuilder setFacultyId(Long facultyId) {
            faculty.setFacultyId(facultyId);
            return this;
        }

        /**
         * Set faculty name
         *
         * @param facultyName the faculty name
         * @return FacultyBuilder
         */
        public Faculty.FacultyBuilder setFacultyName(String facultyName) {
            faculty.setFacultyName(facultyName);
            return this;
        }

        /**
         * Set recruitment plan free
         *
         * @param recruitmentPlanFree the recruitment plan free
         * @return FacultyBuilder
         */
        public Faculty.FacultyBuilder setRecruitmentPlanFree(Integer recruitmentPlanFree) {
            faculty.setRecruitmentPlanFree(recruitmentPlanFree);
            return this;
        }

        /**
         * Set recruitment plan canvas
         *
         * @param recruitmentPlanCanvas the recruitment plan canvas
         * @return FacultyBuilder
         */
        public Faculty.FacultyBuilder setRecruitmentPlanCanvas(Integer recruitmentPlanCanvas) {
            faculty.setRecruitmentPlanCanvas(recruitmentPlanCanvas);
            return this;
        }

        /**
         * Create faculty
         *
         * @return Faculty
         */
        public Faculty createFaculty() {
            return faculty;
        }
    }

}
