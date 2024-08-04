package softuni.defense.project.model.entities;

import jakarta.persistence.*;
import softuni.defense.project.model.entities.base.BaseEntity;
import softuni.defense.project.model.enums.FeedbackRatingEnum;
import softuni.defense.project.model.enums.FeedbackStatusEnum;
import softuni.defense.project.model.enums.TypeChangeEnum;

import java.time.LocalDate;

@Entity
@Table(name = "feedback_change_log")
public class FeedbackChangeLogEntity extends BaseEntity {
    @ManyToOne
    private FeedbackEntity feedback;
    @ManyToOne
    private UserEntity owner;

    @Enumerated(EnumType.STRING)
    private FeedbackRatingEnum satisfaction;

    private String recommendation;
    @Column(name = "submit_date")
    private LocalDate submitDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_of_change")
    private TypeChangeEnum typeChange;
    @Column(name = "old_value")
    private String oldValue;
    @Column(name = "new_value")
    private String newValue;
    public FeedbackChangeLogEntity() {
    }

    public FeedbackChangeLogEntity(TypeChangeEnum typeChange, FeedbackEntity feedback, UserEntity owner, FeedbackRatingEnum satisfaction, String recommendation, LocalDate submitDate, String oldValue, String newValue) {
        this.typeChange = typeChange;
        this.feedback = feedback;
        this.owner = owner;
        this.satisfaction = satisfaction;
        this.recommendation = recommendation;
        this.submitDate = submitDate;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public TypeChangeEnum getTypeChange() {
        return typeChange;
    }

    public void setTypeChange(TypeChangeEnum typeChange) {
        this.typeChange = typeChange;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public FeedbackEntity getFeedback() {
        return feedback;
    }

    public void setFeedback(FeedbackEntity feedback) {
        this.feedback = feedback;
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
