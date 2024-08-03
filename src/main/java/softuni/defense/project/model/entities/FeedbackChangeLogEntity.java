package softuni.defense.project.model.entities;

import jakarta.persistence.*;
import softuni.defense.project.model.entities.base.BaseEntity;
import softuni.defense.project.model.enums.FeedbackRatingEnum;
import softuni.defense.project.model.enums.FeedbackStatusEnum;

import java.time.LocalDate;

@Entity
@Table(name = "feedback_change_log")
public class FeedbackChangeLogEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private FeedbackStatusEnum status;
    @ManyToOne
    private UserEntity owner;

    @Enumerated(EnumType.STRING)
    private FeedbackRatingEnum satisfaction;

    private String recommendation;
    @Column(name = "submit_date")
    private LocalDate submitDate;
    public FeedbackChangeLogEntity() {
    }

    public FeedbackChangeLogEntity(FeedbackStatusEnum status, UserEntity owner, FeedbackRatingEnum satisfaction, String recommendation, LocalDate submitDate) {
        this.status = status;
        this.owner = owner;
        this.satisfaction = satisfaction;
        this.recommendation = recommendation;
        this.submitDate = submitDate;
    }

    public FeedbackStatusEnum getStatus() {
        return status;
    }

    public void setStatus(FeedbackStatusEnum status) {
        this.status = status;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public FeedbackRatingEnum getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(FeedbackRatingEnum satisfaction) {
        this.satisfaction = satisfaction;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public LocalDate getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(LocalDate submitDate) {
        this.submitDate = submitDate;
    }
}
