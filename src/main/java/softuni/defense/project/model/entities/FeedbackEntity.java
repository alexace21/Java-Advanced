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
    @ManyToOne(optional = false)
    private UserEntity ownerUser;
    @Column(name = "user_first_name")
    private String inputFirstName;
    @Column(name = "satisfaction")
    private String selectedOption;
    @Column(name = "usage_reason")

    private String reasonDescription;
    @Column(name = "user_advice")

    private String adviceDescription;
    @Column(name = "quality_service_rate")

    private Integer qualityServiceRate;
    @Column(name = "timeliness_rate")

    private Integer timelinessRate;
    @Column(name = "customer_service_rate")

    private Integer customerServiceRate;
    @Column(name = "price_rate")

    private Integer priceRate;
    @Column(name = "cleanliness_rate")

    private Integer cleanlinessRate;
    @Column(name = "recommendation")
    private String recommendOption;

    @Column(name = "submit_date")
    private LocalDate submitDate;
    @Enumerated(EnumType.STRING)
    private FeedbackStatusEnum status;

    public FeedbackEntity() {
    }

    public UserEntity getOwnerUser() {
        return ownerUser;
    }

    public void setOwnerUser(UserEntity ownerUser) {
        this.ownerUser = ownerUser;
    }

    public String getInputFirstName() {
        return inputFirstName;
    }

    public void setInputFirstName(String inputFirstName) {
        this.inputFirstName = inputFirstName;
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }

    public String getReasonDescription() {
        return reasonDescription;
    }

    public void setReasonDescription(String reasonDescription) {
        this.reasonDescription = reasonDescription;
    }

    public String getAdviceDescription() {
        return adviceDescription;
    }

    public void setAdviceDescription(String adviceDescription) {
        this.adviceDescription = adviceDescription;
    }

    public Integer getQualityServiceRate() {
        return qualityServiceRate;
    }

    public void setQualityServiceRate(Integer qualityServiceRate) {
        this.qualityServiceRate = qualityServiceRate;
    }

    public Integer getTimelinessRate() {
        return timelinessRate;
    }

    public void setTimelinessRate(Integer timelinessRate) {
        this.timelinessRate = timelinessRate;
    }

    public Integer getCustomerServiceRate() {
        return customerServiceRate;
    }

    public void setCustomerServiceRate(Integer customerServiceRate) {
        this.customerServiceRate = customerServiceRate;
    }

    public Integer getPriceRate() {
        return priceRate;
    }

    public void setPriceRate(Integer priceRate) {
        this.priceRate = priceRate;
    }

    public Integer getCleanlinessRate() {
        return cleanlinessRate;
    }

    public void setCleanlinessRate(Integer cleanlinessRate) {
        this.cleanlinessRate = cleanlinessRate;
    }

    public String getRecommendOption() {
        return recommendOption;
    }

    public void setRecommendOption(String recommendOption) {
        this.recommendOption = recommendOption;
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

}
