package softuni.defense.project.model.dtos;


import java.time.LocalDate;
import java.util.Objects;

public class FeedbackLogDTO {
    private String id;
    private String status;
    private String firstName;
    private String lastName;
    private String ownerEmail;
    private String satisfaction;
    private String recommendation;
    private String submitDate;

    public FeedbackLogDTO() {
    }

    public FeedbackLogDTO(String id, String status, String firstName, String lastName, String ownerEmail, String satisfaction, String recommendation, String submitDate) {
        this.id = id;
        this.status = status;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ownerEmail = ownerEmail;
        this.satisfaction = satisfaction;
        this.recommendation = recommendation;
        this.submitDate = submitDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(String satisfaction) {
        this.satisfaction = satisfaction;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(String submitDate) {
        this.submitDate = submitDate;
    }

}
