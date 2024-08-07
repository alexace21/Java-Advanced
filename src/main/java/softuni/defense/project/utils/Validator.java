package softuni.defense.project.utils;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import softuni.defense.project.config.exception.AppException;

@Component
public class Validator {

    public void validateRegistration(BindingResult bindingResult) {
        if (bindingResult.getFieldError("email") != null) {
            throw new AppException(bindingResult.getFieldError("email").getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        if (bindingResult.getFieldError("password") != null) {
            throw new AppException(bindingResult.getFieldError("password").getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        if (bindingResult.getFieldError("firstName") != null) {
            throw new AppException(bindingResult.getFieldError("firstName").getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        if (bindingResult.getFieldError("lastName") != null) {
            throw new AppException(bindingResult.getFieldError("lastName").getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public void validateCreateOffer(BindingResult bindingResult) {
        if (bindingResult.getFieldError("city_mpg") != null) {
            throw new AppException(bindingResult.getFieldError("city_mpg").getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        if (bindingResult.getFieldError("combination_mpg") != null) {
            throw new AppException(bindingResult.getFieldError("combination_mpg").getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        if (bindingResult.getFieldError("cylinders") != null) {
            throw new AppException(bindingResult.getFieldError("cylinders").getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        if (bindingResult.getFieldError("displacement") != null) {
            throw new AppException(bindingResult.getFieldError("displacement").getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        if (bindingResult.getFieldError("drive") != null) {
            throw new AppException(bindingResult.getFieldError("drive").getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        if (bindingResult.getFieldError("fuel_type") != null) {
            throw new AppException(bindingResult.getFieldError("fuel_type").getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        if (bindingResult.getFieldError("highway_mpg") != null) {
            throw new AppException(bindingResult.getFieldError("highway_mpg").getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        if (bindingResult.getFieldError("make") != null) {
            throw new AppException(bindingResult.getFieldError("make").getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        if (bindingResult.getFieldError("model") != null) {
            throw new AppException(bindingResult.getFieldError("model").getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        if (bindingResult.getFieldError("transmission") != null) {
            throw new AppException(bindingResult.getFieldError("transmission").getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        if (bindingResult.getFieldError("year") != null) {
            throw new AppException(bindingResult.getFieldError("year").getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        if (bindingResult.getFieldError("price") != null) {
            throw new AppException(bindingResult.getFieldError("price").getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        if (bindingResult.getFieldError("owner") != null) {
            throw new AppException(bindingResult.getFieldError("owner").getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public void validateFeedback(BindingResult bindingResult) {
        if (bindingResult.getFieldError("inputFirstName") != null) {
            throw new AppException(bindingResult.getFieldError("inputFirstName").getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        if (bindingResult.getFieldError("selectedOption") != null) {
            throw new AppException(bindingResult.getFieldError("selectedOption").getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        if (bindingResult.getFieldError("reasonDescription") != null) {
            throw new AppException(bindingResult.getFieldError("reasonDescription").getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        if (bindingResult.getFieldError("adviceDescription") != null) {
            throw new AppException(bindingResult.getFieldError("adviceDescription").getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        if (bindingResult.getFieldError("qualityServiceRate") != null) {
            throw new AppException(bindingResult.getFieldError("qualityServiceRate").getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        if (bindingResult.getFieldError("timelinessRate") != null) {
            throw new AppException(bindingResult.getFieldError("timelinessRate").getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        if (bindingResult.getFieldError("customerServiceRate") != null) {
            throw new AppException(bindingResult.getFieldError("customerServiceRate").getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        if (bindingResult.getFieldError("priceRate") != null) {
            throw new AppException(bindingResult.getFieldError("priceRate").getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        if (bindingResult.getFieldError("cleanlinessRate") != null) {
            throw new AppException(bindingResult.getFieldError("cleanlinessRate").getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        if (bindingResult.getFieldError("recommendOption") != null) {
            throw new AppException(bindingResult.getFieldError("recommendOption").getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }

        if (bindingResult.getFieldError("ownerUser") != null) {
            throw new AppException(bindingResult.getFieldError("ownerUser").getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
