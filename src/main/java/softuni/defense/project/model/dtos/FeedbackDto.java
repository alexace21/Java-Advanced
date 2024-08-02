package softuni.defense.project.model.dtos;

public class FeedbackDto {

    private String inputFirstName;
    private String selectedOption;
    private String reasonDescription;
    private String adviceDescription;
    private Integer qualityServiceRate;
    private Integer timelinessRate;
    private Integer customerServiceRate;
    private Integer priceRate;
    private Integer cleanlinessRate;
    private String recommendOption;
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
