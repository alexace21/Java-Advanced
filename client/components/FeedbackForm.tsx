"use client";

import { useAuthContext } from "@/context/AuthContext";
import { submitFeedbackForm } from "@/utils";
import { useRouter } from "next/navigation";
import React, { useEffect, useState } from "react";
import StarRatings from "react-star-ratings";

const FeedbackForm = () => {
  const { internationalization } = useAuthContext();
  const [headlineText, setHeadlineText] = useState("Car Service Satisfaction Survey");
  const [subHeadlineText, setSubHeadlineText] = useState("Please take a few minutes to provide feedback on your recent car experience with carHub");
  const [satisfactionQuestion, setSatisfactionQuestion] = useState("How satisfied are you with the car service you received?");

  const [verySatisfiedText, setVerySatisfiedText] = useState("Very Satisfied");
  const [satisfiedText, setSatisfiedText] = useState("Satisfied");
  const [neutralText, setNeutralText] = useState("Neutral");
  const [dissatisfiedText, setDissatisfiedText] = useState("Dissatisfied");
  const [veryDissatisfiedText, setVeryDissatisfiedText] = useState("Very Dissatisfied");

  const [reasonQuestion, setReasonQuestion] = useState("What was the reason for your car service?");

  const [qualityText, setQualityText] = useState("Quality of Service");
  const [timelinessText, setTimelinessText] = useState("Timeliness");
  const [customerServiceText, setCustomerServiceText] = useState("Customer Service");
  const [priceText, setPriceText] = useState("Price");
  const [cleanlinessText, setCleanlinessText] = useState("Cleanliness");

  const [adviceQuestion, setAdviceQuestion] = useState("Do you have any suggestions or comments for improving our car service?");
  const [recommendQuestion, setRecommendQuestion] = useState("Would you recommend our car service to others?");

  const [recommendSelectText, setRecommendSelectText] = useState("Please Select");
  const [recommendOptionOne, setRecommendOptionOne] = useState("Definitely");
  const [recommendOptionTwo, setRecommendOptionTwo] = useState("Probably");
  const [recommendOptionThree, setRecommendOptionThree] = useState("Not Sure");
  const [recommendOptionFour, setRecommendOptionFour] = useState("Probably Not");
  const [recommendOptionFive, setRecommendOptionFive] = useState("Definitely Not");

  const [contactInfoText, setContactInfoText] = useState("Please provide your contact information if you would like us to follow up with you");
  const [firstNameText, setFirstNameText] = useState("First Name");
  const [lastNameText, setLastNameText] = useState("Last Name");
  const [submitText, setSubmitText] = useState("Submit");

  useEffect(() => {
    console.log("Internationalization switched! " + internationalization);

    if (internationalization === "Български") {
      if (feedbackFormError != null) {
        setFeedbackFormError("Моля попълнете всички полета!");
      }

      setHeadlineText("Анкета за удовлетвореност от автосервиз");
      setSubHeadlineText("Моля, отделете няколко минути, за да дадете обратна връзка за вашето последно преживяване с автомагазина на carHub");
      setSatisfactionQuestion("Колко сте удовлетворени от услугата, която получихте?");
      setVerySatisfiedText("Много удовлетворен");
      setSatisfiedText("Удовлетворен");
      setNeutralText("Неутрален");
      setDissatisfiedText("Недоволен");
      setVeryDissatisfiedText("Много недоволен");
      setReasonQuestion("Каква е причината за вашето посещение на автосервиза?");
      setQualityText("Качество на услугата");
      setTimelinessText("Своевременност");
      setCustomerServiceText("Обслужване на клиенти");
      setPriceText("Цена");
      setCleanlinessText("Чистота");
      setAdviceQuestion("Имате ли предложения или коментари за подобряване на нашата услуга?");
      setRecommendQuestion("Бихте ли препоръчали нашия автосервиз на други?");
      setRecommendSelectText("Моля изберете")
      setRecommendOptionOne("Определено");
      setRecommendOptionTwo("Вероятно");
      setRecommendOptionThree("Не съм сигурен");
      setRecommendOptionFour("Вероятно не");
      setRecommendOptionFive("Определено не");
      setContactInfoText("Моля, предоставете контактна информация, ако искате да се свържем с вас");
      setFirstNameText("Име");
      setLastNameText("Фамилия");
      setSubmitText("Изпрати");

    } else {
      if (feedbackFormError != null) {
        setFeedbackFormError("Please fill out all fields!");
      }
      setHeadlineText("Car Service Satisfaction Survey");
      setSubHeadlineText("Please take a few minutes to provide feedback on your recent car experience with carHub");
      setSatisfactionQuestion("How satisfied are you with the car service you received?");
      setVerySatisfiedText("Very Satisfied");
      setSatisfiedText("Satisfied");
      setNeutralText("Neutral");
      setDissatisfiedText("Dissatisfied");
      setVeryDissatisfiedText("Very Dissatisfied");
      setReasonQuestion("What was the reason for your car service?");
      setQualityText("Quality of Service")
      setTimelinessText("Timeliness");
      setCustomerServiceText("Customer Service");
      setPriceText("Price");
      setCleanlinessText("Cleanliness");
      setAdviceQuestion("Do you have any suggestions or comments for improving our car service?");
      setRecommendQuestion("Would you recommend our car service to others?");
      setRecommendSelectText("Please Select");
      setRecommendOptionOne("Definitely");
      setRecommendOptionTwo("Probably");
      setRecommendOptionThree("Not Sure");
      setRecommendOptionFour("Probably Not");
      setRecommendOptionFive("Definitely Not");
      setContactInfoText("Please provide your contact information if you would like us to follow up with you");
      setFirstNameText("First Name");
      setLastNameText("Last Name");
      setSubmitText("Submit");
    }
    
  }, [internationalization])

  const router = useRouter();  
  const [selectedOption, setSelectedOption] = useState("satisfied");
  const [feedbackFormError, setFeedbackFormError] = useState<string | null>(
    null
  );

  const [reasonDescription, setReasonDescription] = useState("");
  const [adviceDescription, setAdviceDescription] = useState("")

  const [qualityServiceRate, setQualityServiceRate] = useState(0);
  const [timelinessRate, setTimelinessRate] = useState(0);
  const [customerServiceRate, setCustomerServiceRate] = useState(0);
  const [priceRate, setPriceRate] = useState(0);
  const [cleanlinessRate, setCleanlinessRate] = useState(0);

  const [recommendOption, setRecommendOption] = useState("");

  const [inputFirstName, setInputFirstName] = useState("");
  const [inputLastName, setInputLastName] = useState("");

  const handleInputFirstName = (event: any) => {
    setInputFirstName(event.target.value);
  }

  const handleInputLastName = (event: any) => {
    setInputLastName(event.target.value);
  }

  const handleOptionChange = (event: any) => {
    setSelectedOption(event.target.value);
  };

  const handleTextareaChange = (event: any) => {
    setReasonDescription(event.target.value);
  };

  const handleAdviceTextareaChange = (event: any) => {
    setAdviceDescription(event.target.value);
  };

  const handleQualityServiceRate = (rate: number) => {
    setQualityServiceRate(rate);
  };

  const handleTimelinessRate = (rate: number) => {
    setTimelinessRate(rate);
  };

  const handleCustomerServiceRate = (rate: number) => {
    setCustomerServiceRate(rate);
  };

  const handlePriceRate = (rate: number) => {
    setPriceRate(rate);
  };

  const handleCleaninessRate = (rate: number) => {
    setCleanlinessRate(rate);
  };

  const handleRecommendOption = (event: any) => {
    setRecommendOption(event.target.value);
  };

  const handleSubmitFeedbackForm = async(event: any) => {
    event.preventDefault();

    if (
        !inputFirstName ||
        !selectedOption ||
        !reasonDescription ||
        !adviceDescription ||
        !qualityServiceRate ||
        !timelinessRate ||
        !customerServiceRate ||
        !priceRate ||
        !cleanlinessRate ||
        !recommendOption 
    ) {
        setFeedbackFormError("Please fill out all fields!");
    } else {
        const loggedUser = window.localStorage.getItem("auth_user");
        const result = await submitFeedbackForm(
            inputFirstName, 
            selectedOption,
            reasonDescription,
            adviceDescription,
            qualityServiceRate,
            timelinessRate,
            customerServiceRate,
            priceRate,
            cleanlinessRate,
            recommendOption,
            loggedUser
        )

        if (result === 200) {
            alert("Your feedback has been successfully submitted and received by the Customer Service team, thank you! ")
            router.push("/");
        } else {
            alert("Something went wrong with submitting your Feedback! Please try again!")
            console.log(result)
        }
        setFeedbackFormError(null);
    }

  }



  return (
    
      <form
        onSubmit={(event) => handleSubmitFeedbackForm(event)}
        className="feedback-form"
        autoComplete="on"
        noValidate={true}
        acceptCharset="utf-8"
        method="post"
      >
        <div className="form-all">
          <ul className="form-section page-section">
            <li className="form-input-wide">
              <div className="form-header-group header-large">
                <div className="header-text text-center">
                  <h2 className="form-header">
                    {headlineText}
                  </h2>
                  <div className="form-subHeader">
                    {subHeadlineText}
                  </div>
                </div>
              </div>
            </li>

            <li className="form-line">
              <label
                htmlFor="input_2_0"
                className="form-label form-label-top form-label-auto"
              >
                {satisfactionQuestion}
              </label>
              <div id="cid_2" className="form-input-wide" data-layout="full">
                <div className="form-single-column" role="group">
                  <span className="form-radio-item">
                    <span className="dragger-item"></span>
                    <label>
                      <input
                        type="radio"
                        value="very_satisfied"
                        checked={selectedOption === "very_satisfied"}
                        onChange={handleOptionChange}
                        className="mr-5 w-4 h-4"
                      />
                      {verySatisfiedText}
                    </label>
                  </span>

                  <span className="form-radio-item">
                    <span className="dragger-item"></span>
                    <label>
                      <input
                        type="radio"
                        value="satisfied"
                        checked={selectedOption === "satisfied"}
                        onChange={handleOptionChange}
                        className="mr-5 w-4 h-4"
                      />
                      {satisfiedText}
                    </label>
                  </span>

                  <span className="form-radio-item">
                    <span className="dragger-item"></span>
                    <label>
                      <input
                        type="radio"
                        value="neutral"
                        checked={selectedOption === "neutral"}
                        onChange={handleOptionChange}
                        className="mr-5 w-4 h-4"
                      />
                      {neutralText}
                    </label>
                  </span>

                  <span className="form-radio-item">
                    <span className="dragger-item"></span>
                    <label>
                      <input
                        type="radio"
                        value="dissatisfied"
                        checked={selectedOption === "dissatisfied"}
                        onChange={handleOptionChange}
                        className="mr-5 w-4 h-4"
                      />
                      {dissatisfiedText}
                    </label>
                  </span>

                  <span className="form-radio-item">
                    <span className="dragger-item"></span>
                    <label>
                      <input
                        type="radio"
                        value="very_dissatisfied"
                        checked={selectedOption === "very_dissatisfied"}
                        onChange={handleOptionChange}
                        className="mr-5 w-4 h-4"
                      />
                      {veryDissatisfiedText}
                    </label>
                  </span>
                </div>
              </div>
            </li>

            <li className="form-line">
              <label
                htmlFor="input_3"
                className="form-label form-label-top form-label-auto"
              >
                {reasonQuestion}
              </label>
              <div className="form-input-wide" data-layout="full">
                <textarea
                  className="form-textarea"
                  style={{ width: "648px", height: "163px" }}
                  aria-labelledby="label_3"
                  value={reasonDescription}
                  onChange={handleTextareaChange}
                ></textarea>
              </div>
            </li>

            <li
              className="form-line"
              data-type="control_rating"
              id="id_9"
              data-css-selector="id_9"
            >
              <label
                className="form-label form-label-top form-label-auto"
                id="label_9"
                htmlFor="input_9"
                aria-hidden="false"
              >
                {qualityText}
              </label>
              
              <div id="cid_9" className="form-input-wide" data-layout="full">
              
                <div
                  id="input_9"
                  data-component="rating"
                  data-version="v2"
                  className="form-star-rating"
                  role="radiogroup"
                  style={{ cursor: "default" }}
                >
                  <StarRatings
                    rating={qualityServiceRate} // Initial rating value
                    starRatedColor="orange"
                    changeRating={(newRating) => handleQualityServiceRate(newRating)}
                    numberOfStars={5}
                    name="quality_of_service_rate"
                    starHoverColor="blue"
                  />
                </div>
              </div>
            </li>

            <li
              className="form-line"
              data-type="control_rating"
              id="id_10"
              data-css-selector="id_10"
            >
              <label
                className="form-label form-label-top form-label-auto"
                id="label_10"
                htmlFor="input_10"
                aria-hidden="false"
              >
                {timelinessText}
              </label>
              <div className="form-input-wide" data-layout="full">
                <div
                  data-component="rating"
                  data-version="v2"
                  className="form-star-rating"
                  role="radiogroup"
                  aria-labelledby="label_10"
                  style={{ cursor: "default" }}
                >
                  <StarRatings
                    rating={timelinessRate} // Initial rating value
                    starRatedColor="orange"
                    changeRating={(newRating) => handleTimelinessRate(newRating)}
                    numberOfStars={5}
                    name="timeliness_rate"
                    starHoverColor="blue"
                  />
                </div>
              </div>
            </li>

            <li
              className="form-line"
              data-type="control_rating"
              id="id_12"
              data-css-selector="id_12"
            >
              <label
                className="form-label form-label-top form-label-auto"
                id="label_12"
                htmlFor="input_12"
                aria-hidden="false"
              >
                {customerServiceText}
              </label>
              <div id="cid_12" className="form-input-wide" data-layout="full">
                <div
                  id="input_12"
                  data-component="rating"
                  data-version="v2"
                  className="form-star-rating cursor-default"
                  role="radiogroup"
                  aria-labelledby="label_12"
                >
                  <StarRatings
                    rating={customerServiceRate} // Initial rating value
                    starRatedColor="orange"
                    changeRating={(newRating) => handleCustomerServiceRate(newRating)}
                    numberOfStars={5}
                    name="customer_service_rate"
                    starHoverColor="blue"
                  />
                </div>
              </div>
            </li>

            <li
              className="form-line"
              data-type="control_rating"
              id="id_11"
              data-css-selector="id_11"
            >
              <label
                className="form-label form-label-top form-label-auto"
                id="label_11"
                htmlFor="input_11"
                aria-hidden="false"
              >
                {priceText}
              </label>
              <div id="cid_11" className="form-input-wide" data-layout="full">
                <div
                  id="input_11"
                  data-component="rating"
                  data-version="v2"
                  className="form-star-rating cursor-default"
                  role="radiogroup"
                  aria-labelledby="label_11"
                >
                  <StarRatings
                    rating={priceRate} // Initial rating value
                    starRatedColor="orange"
                    changeRating={(newRating) => handlePriceRate(newRating)}
                    numberOfStars={5}
                    name="price_rate"
                    starHoverColor="blue"
                  />
                </div>
              </div>
            </li>

            <li
              className="form-line"
              data-type="control_rating"
              id="id_14"
              data-css-selector="id_14"
            >
              <label
                className="form-label form-label-top form-label-auto"
                id="label_14"
                htmlFor="input_14"
                aria-hidden="false"
              >
                {cleanlinessText}
              </label>
              <div id="cid_14" className="form-input-wide" data-layout="full">
                <div
                  id="input_14"
                  data-component="rating"
                  data-version="v2"
                  className="form-star-rating cursor-default"
                  role="radiogroup"
                  aria-labelledby="label_14"
                >
                  <StarRatings
                    rating={cleanlinessRate} // Initial rating value
                    starRatedColor="orange"
                    changeRating={(newRating) => handleCleaninessRate(newRating)}
                    numberOfStars={5}
                    name="cleanliness_rate"
                    starHoverColor="blue"
                  />
                </div>
              </div>
            </li>

            <li
              className="form-line"
              data-type="control_textarea"
              id="id_5"
              data-css-selector="id_5"
            >
              <label
                className="form-label form-label-top form-label-auto"
                id="label_5"
                htmlFor="input_5"
                aria-hidden="false"
              >
                {adviceQuestion}
              </label>
              <div id="cid_5" className="form-input-wide" data-layout="full">
                <textarea
                  id="input_5"
                  className="form-textarea w-[648px] h-[163px]"
                  name="q5_longText_4"
                  data-component="textarea"
                  aria-labelledby="label_5"
                  value={adviceDescription}
                  onChange={handleAdviceTextareaChange}
                ></textarea>
              </div>
            </li>

            <li
              className="form-line"
              data-type="control_dropdown"
              id="id_6"
              data-css-selector="id_6"
            >
              <label
                className="form-label form-label-top form-label-auto"
                id="label_6"
                htmlFor="input_6"
                aria-hidden="false"
              >
                {recommendQuestion}
              </label>
              <div id="cid_6" className="form-input-wide" data-layout="half">
                <select
                  className="form-dropdown"
                  id="input_6"
                  name="q6_dropdown_5"
                  data-component="dropdown"
                  aria-label="Would you recommend our car service to others?"
                  value={recommendOption}
                  onChange={handleRecommendOption}
                >
                  <option value="">{recommendSelectText}</option>
                  <option value="Definitely">{recommendOptionOne}</option>
                  <option value="Probably">{recommendOptionTwo}</option>
                  <option value="Not Sure">{recommendOptionThree}</option>
                  <option value="Probably Not">{recommendOptionFour}</option>
                  <option value="Definitely Not">{recommendOptionFive}</option>
                </select>
              </div>
            </li>

            <li
              className="form-line"
              data-type="control_fullname"
              id="id_7"
              data-css-selector="id_7"
            >
              <label
                className="form-label form-label-top form-label-auto"
                id="label_7"
                htmlFor="first_7"
                aria-hidden="false"
              >
                {contactInfoText}:
              </label>
              <div id="cid_7" className="form-input-wide" data-layout="full">
                <div data-wrapper-react="true">
                  <span
                    className="form-sub-label-container align-top"
                    data-input-type="first"
                  >
                    <input
                      type="text"
                      id="first_7"
                      name="q7_fullName_6[first]"
                      className="form-textbox"
                      data-defaultvalue=""
                      data-component="first"
                      aria-labelledby="label_7 sublabel_7_first"
                      value={inputFirstName}
                      onChange={(event) => handleInputFirstName(event)}
                    />
                    <label
                      className="form-sub-label min-h-3"
                      htmlFor="first_7"
                      id="sublabel_7_first"
                    >
                      {firstNameText}
                    </label>
                  </span>
                  <span
                    className="form-sub-label-container align-top"
                    data-input-type="last"
                  >
                    <input
                      type="text"
                      id="last_7"
                      name="q7_fullName_6[last]"
                      className="form-textbox"
                      data-defaultvalue=""
                      data-component="last"
                      aria-labelledby="label_7 sublabel_7_last"
                      value={inputLastName}
                      onChange={(event) => handleInputLastName(event)}
                    />
                    <label
                      className="form-sub-label min-h-3"
                      htmlFor="last_7"
                      id="sublabel_7_last"
                    >
                      {lastNameText}
                    </label>
                  </span>
                </div>
              </div>
            </li>

            {feedbackFormError && <p className="text-red-700 font-bold text-xl">{feedbackFormError}</p>}

            <li
              className="form-line"
              data-type="control_button"
              id="id_8"
              data-css-selector="id_8"
            >
              <div id="cid_8" className="form-input-wide" data-layout="full">
                <div
                  data-align="auto"
                  className="form-buttons-wrapper form-buttons-auto jsTest-button-wrapperField"
                >
                  <button
                    id="input_8"
                    type="submit"
                    className="form-submit-button submit-button"
                    data-component="button"
                    data-content=""
                    aria-live="polite"
                  >
                    {submitText}
                  </button>
                </div>
              </div>
            </li>

          </ul>
        </div>
      </form>
    
  );
};

export default FeedbackForm;
