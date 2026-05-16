module com.validation {
    exports com.validation;
    exports com.validation.annotation;
    exports com.validation.exception;
    exports com.validation.strategy;
    exports com.validation.validator;
    opens com.validation to com.validation.validator;
}