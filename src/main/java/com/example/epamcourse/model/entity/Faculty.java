package com.example.epamcourse.model.entity;

public class Faculty extends BaseEntity {
    private Long facultyId;
    private Integer markPass;
    private String facultyName;
    private Integer recruitmentPlanFree;
    private Integer recruitmentPlanCanvas;

    public Faculty(Long facultyId, Integer markPass,
                   String facultyName, Integer recruitmentPlanFree,
                   Integer recruitmentPlanCanvas) {
        this.facultyId = facultyId;
        this.markPass = markPass;
        this.facultyName = facultyName;
        this.recruitmentPlanFree = recruitmentPlanFree;
        this.recruitmentPlanCanvas = recruitmentPlanCanvas;
    }

    public Long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public Integer getMarkPass() {
        return markPass;
    }

    public void setMarkPass(Integer markPass) {
        this.markPass = markPass;
    }

    public Integer getRecruitmentPlanFree() {
        return recruitmentPlanFree;
    }

    public void setRecruitmentPlanFree(Integer recruitmentPlanFree) {
        this.recruitmentPlanFree = recruitmentPlanFree;
    }

    public Integer getRecruitmentPlanCanvas() {
        return recruitmentPlanCanvas;
    }

    public void setRecruitmentPlanCanvas(Integer recruitmentPlanCanvas) {
        this.recruitmentPlanCanvas = recruitmentPlanCanvas;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Faculty)) return false;
        Faculty faculty = (Faculty) o;
        if (markPass != null ? !markPass.equals(faculty.markPass) : faculty.markPass != null) return false;
        if (recruitmentPlanFree != null ? !recruitmentPlanFree.equals(faculty.recruitmentPlanFree) : faculty.recruitmentPlanFree != null)
            return false;
        if (recruitmentPlanCanvas != null ? !recruitmentPlanCanvas.equals(faculty.recruitmentPlanCanvas) : faculty.recruitmentPlanCanvas != null)
            return false;
        return facultyName != null ? facultyName.equals(faculty.facultyName) : faculty.facultyName == null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Long.hashCode(facultyId);
        result = prime * result + ((facultyName == null) ? 0 : facultyName.hashCode());
        result = prime * result + ((markPass == null) ? 0 : markPass.hashCode());
        result = prime * result + ((recruitmentPlanFree == null) ? 0 : recruitmentPlanFree.hashCode());
        result = prime * result + ((recruitmentPlanCanvas == null) ? 0 : recruitmentPlanCanvas.hashCode());
        result = prime * result + ((facultyId == null) ? 0 : facultyId.hashCode());
        return prime;
    }

    @Override
    public String toString() {
        StringBuilder stringData = new StringBuilder();
        stringData.append("Faculty{")
                .append("facultyId=")
                .append(facultyId)
                .append(", markPass=")
                .append(markPass)
                .append(", facultyName='")
                .append(facultyName)
                .append(", facultyName='")
                .append(facultyName)
                .append(", recruitmentPlanFree=")
                .append(recruitmentPlanFree)
                .append(", recruitmentPlanCanvas=")
                .append(recruitmentPlanCanvas)
                .append('}');
        return stringData.toString();
    }

    public static class FacultyBuilder {

        private Long facultyId;
        private Integer markPass;
        private String facultyName;
        private Integer recruitmentPlanFree;
        private Integer recruitmentPlanCanvas;

        public Faculty.FacultyBuilder setFacultyId(Long facultyId) {
            this.facultyId = facultyId;
            return this;
        }

        public Faculty.FacultyBuilder setMarkPass(Integer markPass) {
            this.markPass = markPass;
            return this;
        }

        public Faculty.FacultyBuilder setFacultyName(String facultyName) {
            this.facultyName = facultyName;
            return this;
        }

        public Faculty.FacultyBuilder setRecruitmentPlanFree(Integer recruitmentPlanFree) {
            this.recruitmentPlanFree = recruitmentPlanFree;
            return this;
        }

        public Faculty.FacultyBuilder setRecruitmentPlanCanvas(Integer recruitmentPlanCanvas) {
            this.recruitmentPlanCanvas = recruitmentPlanCanvas;
            return this;
        }

        public Faculty createFaculty() {
            return new Faculty(facultyId, markPass,
                    facultyName, recruitmentPlanFree,
                    recruitmentPlanCanvas);
        }
    }

}
