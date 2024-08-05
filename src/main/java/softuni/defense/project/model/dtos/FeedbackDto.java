package softuni.defense.project.model.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class FeedbackDto {
    @NotBlank(message = "First Name is required!")
    private String inputFirstName;
    @NotBlank(message = "Satisfaction option is required!")
    private String selectedOption;
    @NotBlank(message = "Reason Description is required!")
    private String reasonDescription;
    @NotBlank(message = "Advice Description is required!")
    private String adviceDescription;
    @Positive(message = "Quality Service Rate should be a positive number!")
    @Min(value = 1, message = "Quality Service Rate cannot be 0!")
    @Max(value = 5, message = "Quality Service Rate cannot exceed 5!")
    private Integer qualityServiceRate;
    @Positive(message = "Timeliness Rate should be a positive number!")
    @Min(value = 1, message = "Timeliness Rate cannot be 0!")
    @Max(value = 5, message = "Timeliness Rate cannot exceed 5!")
    private Integer timelinessRate;
    @Positive(message = "Customer Service Rate should be a positive number!")
    @Min(value = 1, message = "Customer Service Rate cannot be 0!")
    @Max(value = 5, message = "Customer Service Rate cannot exceed 5!")
    private Integer customerServiceRate;
    @Positive(message = "Price Rate should be a positive number!")
    @Min(value = 1, message = "Price Rate cannot be 0!")
    @Max(value = 5, message = "Price Rate cannot exceed 5!")
    private Integer priceRate;
    @Positive(message = "Cleanliness Rate should be a positive number!")
    @Min(value = 1, message = "Cleanliness Rate cannot be 0!")
    @Max(value = 5, message = "Cleanliness Rate cannot exceed 5!")
    private Integer cleanlinessRate;
    @NotBlank(message = "Recommend option is required!")
    private String recommendOption;
    @NotBlank(message = "User owner is required!")
    private String ownerUser;


    public FeedbackDto() {
    }

    public FeedbackDto(String inputFirstName, String selectedOption, String reasonDescription, String adviceDescription, Integer qualityServiceRate, Integer timelinessRate, Integer customerServiceRate, Integer priceRate, Integer cleanlinessRate, String recommendOption, String ownerUser) {
        this.inputFirstName = inputFirstName;
        this.selectedOption = selectedOption;
        this.reasonDescription = reasonDescription;
        this.adviceDescription = adviceDescription;
        this.qualityServiceRate = qualityServiceRate;
        this.timelinessRate = timelinessRate;
        this.customerServiceRate = customerServiceRate;
        this.priceRate = priceRate;
        this.cleanlinessRate = cleanlinessRate;
        this.recommendOption = recommendOption;
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

    public String getOwnerUser() {
        return ownerUser;
    }

    public void setOwnerUser(String ownerUser) {
        this.ownerUser = ownerUser;
    }
}
