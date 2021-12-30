package com.example.epamcourse.model.entity;

public class FeedBack extends BaseEntity {
    private Long feedbackId;
    private Long administratorId;
    private Long applicantId;
    private String message;

    public FeedBack(Long feedbackId, Long administratorId, Long applicantId, String message) {
        this.feedbackId = feedbackId;
        this.administratorId = administratorId;
        this.applicantId = applicantId;
        this.message = message;
    }

    public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Long getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(Long administratorId) {
        this.administratorId = administratorId;
    }

    public Long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Long applicantId) {
        this.applicantId = applicantId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FeedBack)) return false;
        FeedBack feedBack = (FeedBack) o;
        if (administratorId != null ? !administratorId.equals(feedBack.administratorId) : feedBack.administratorId != null) return false;
        if (applicantId != null ? !applicantId.equals(feedBack.applicantId) : feedBack.applicantId != null) return false;
        return message != null ? message.equals(feedBack.message) : feedBack.message == null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Long.hashCode(feedbackId);
        result = prime * result + ((administratorId == null) ? 0 : administratorId.hashCode());
        result = prime * result + ((applicantId == null) ? 0 : applicantId.hashCode());
        result = prime * result + ((message == null) ? 0 : message.hashCode());

        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringData = new StringBuilder();
        stringData.append("FeedBack{")
                .append("feedbackId=")
                .append(feedbackId)
                .append(", administratorId=")
                .append(administratorId)
                .append(", applicantId=")
                .append(applicantId)
                .append(", message='")
                .append(message)
                .append('}');

        return stringData.toString();
    }
}
