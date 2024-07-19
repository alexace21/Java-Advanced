package softuni.defense.project.model.entities;

import jakarta.persistence.*;
import softuni.defense.project.model.enums.FeedbackRatingEnum;
import softuni.defense.project.model.enums.FeedbackStatusEnum;
import softuni.defense.project.model.enums.FeedbackTypeEnum;
import softuni.defense.project.model.entities.base.BaseEntity;

import java.time.LocalDate;

@Entity
@Table(name = "feedbacks")
public class FeedbackEntity extends BaseEntity {

    @ManyToOne
    private UserEntity user;

    private String email;

    private String name;

    private String subject;

    private String message;
    @Column(name = "submit_date")
    private LocalDate submitDate;
    @Enumerated(EnumType.STRING)
    private FeedbackStatusEnum status;
    @Enumerated(EnumType.STRING)
    private FeedbackRatingEnum rating;

    @Enumerated(EnumType.STRING)
    private FeedbackTypeEnum type;

    @ManyToOne
    private CarEntity car;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(LocalDate submitDate) {
        this.submitDate = submitDate;
    }

    public FeedbackStatusEnum getStatus() {
        return status;
    }

    public void setStatus(FeedbackStatusEnum status) {
        this.status = status;
    }

    public FeedbackRatingEnum getRating() {
        return rating;
    }

    public void setRating(FeedbackRatingEnum rating) {
        this.rating = rating;
    }

    public FeedbackTypeEnum getType() {
        return type;
    }

    public void setType(FeedbackTypeEnum type) {
        this.type = type;
    }

    public CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }
}
