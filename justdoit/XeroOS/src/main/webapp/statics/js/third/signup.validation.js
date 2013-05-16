/* signup.validation v1.0
   Provides submit/blur event handler ecapsulating a set of validation rules for the signup page.

*/
var signup = signup || {};
signup.validation = signup.validation || {

    invalidClass: "incorrect",
    validationErrorClass: "validationError",
    focusFirstInvalidFieldOnInvalidSubmit: true,

    blurHandler: function (event) {
        // check if event is raised by an element that does not use the standard rule definition system and process accordingly
        if (event.target.id === "phonearea" || event.target.id === "phone-3" || event.target.id === "phone-4") {
            // do domestic phone number validation if parent div is visible
            if ($("#phone").is(":visible")) {
                var domesticPhoneValidationResult = null;
                var domesticPhoneVal = $("#phonearea").val() + $("#phone-3").val() + $("#phone-4").val();
                var domesticPhoneRegex = /^\d{10}$/;
                if (domesticPhoneVal.match(domesticPhoneRegex) === null) {
                    domesticPhoneValidationResult = new signup.validation.ValidationResult("domesticPhone", false, "Please enter a valid phone number.");
                }
                else {
                    domesticPhoneValidationResult = new signup.validation.ValidationResult("domesticPhone", true, null);
                }

                if (!domesticPhoneValidationResult.status) {
                    signup.validation.setInvalidField(domesticPhoneValidationResult);
                }
                else {
                    signup.validation.clearInvalidField(domesticPhoneValidationResult);
                }
            }
        } else if (event.target.id === "work-phone") {
            // international phone number validation
            if ($("#IntPhone").is(":visible")) {
                var internationalPhoneValidationResult = null;
                var internationalPhoneVal = $("#work-phone").val()
                var internationalPhoneRegex = /^(\(?\+?[0-9]*\)?)?[0-9_\- . \(\)]*$/;
                if (internationalPhoneVal.match(internationalPhoneRegex) === null) {
                    internationalPhoneValidationResult = new signup.validation.ValidationResult("internationalPhone", false, "Phone fields can contain only numbers and the following symbols: -.+( )");
                }
                else {
                    internationalPhoneValidationResult = new signup.validation.ValidationResult("internationalPhone", true, null);
                }

                if (!internationalPhoneValidationResult.status) {
                    signup.validation.setInvalidField(internationalPhoneValidationResult);
                }
                else {
                    signup.validation.clearInvalidField(internationalPhoneValidationResult);
                }
            }
        }
        else {
            // this handles all elements defined by the standard rules based system
            var result = signup.validation.validateField(event.target.id);
            if (result) {
                if (!result.status) {
                    signup.validation.setInvalidField(result);
                } else {
                    signup.validation.clearInvalidField(result);
                }
            }
        }
    },

    submitHandler: function (event) {
        var hasErrors = false;
        var firstInvalid = true;
        // loop through all the defined fields
        for (var field in signup.validation.rules) {
            // validate each field (will walk all defined rules for the field and call the appropriate validation method).
            var result = signup.validation.validateField(field);
            if (!result.status) {
                hasErrors = true;
                signup.validation.setInvalidField(result);
                if (firstInvalid && signup.validation.focusFirstInvalidFieldOnInvalidSubmit) {
                    firstInvalid = false;
                    $("#" + result.fieldName).focus();
                }
            }
            else {
                signup.validation.clearInvalidField(result);
            }
        }

        // HACK: do validation for any inputs that could not be defined by our rules system:

        // domestic phone number validation
        if ($("#phone").is(":visible")) {
            var domesticPhoneValidationResult = null;
            var domesticPhoneVal = $("#phonearea").val() + $("#phone-3").val() + $("#phone-4").val();
            var domesticPhoneRegex = /^\d{10}$/;
            if (domesticPhoneVal.match(domesticPhoneRegex) === null) {
                domesticPhoneValidationResult = new signup.validation.ValidationResult("domesticPhone", false, "Please enter a valid phone number");
            }
            else {
                domesticPhoneValidationResult = new signup.validation.ValidationResult("domesticPhone", true, null);
            }

            if (!domesticPhoneValidationResult.status) {
                hasErrors = true;
                signup.validation.setInvalidField(domesticPhoneValidationResult);
            }
            else {
                signup.validation.clearInvalidField(domesticPhoneValidationResult);
            }
        }
        // international phone number validation
        if ($("#IntPhone").is(":visible")) {
            var internationalPhoneValidationResult = null;
            var internationalPhoneVal = $("#work-phone").val()
            var internationalPhoneRegex = /^(\(?\+?[0-9]*\)?)?[0-9_\- . \(\)]*$/;
            if (internationalPhoneVal.match(internationalPhoneRegex) === null) {
                internationalPhoneValidationResult = new signup.validation.ValidationResult("internationalPhone", false, "Phone fields can contain only numbers and the following symbols: -.+( )");
            }
            else {
                internationalPhoneValidationResult = new signup.validation.ValidationResult("internationalPhone", true, null);
            }

            if (!internationalPhoneValidationResult.status) {
                hasErrors = true;
                signup.validation.setInvalidField(internationalPhoneValidationResult);
            }
            else {
                signup.validation.clearInvalidField(internationalPhoneValidationResult);
            }
        }

        if (hasErrors) {
            event.preventDefault();
        }
    },

    setInvalidField: function (validationResult) {
        var invalidClass = signup.validation.invalidClass;
        var validationErrorClass = signup.validation.validationErrorClass;
        var $elem;

        if (validationResult.fieldName === "domesticPhone") {
            var $domesticPhoneFields = $("#phonearea, #phone-3, #phone-4");
            signup.validation.clearInvalidField(validationResult);
            $domesticPhoneFields.addClass(invalidClass);
            $domesticPhoneFields.first().parent().append("<div class='" + validationErrorClass + " " + validationResult.fieldName + "'><label for='phonearea'>" + validationResult.message + "</label></div>");
        }
        else if (validationResult.fieldName === "internationalPhone") {
            $elem = $("#work-phone");
            signup.validation.clearInvalidField(validationResult);
            $elem.addClass(invalidClass);
            $elem.parent().append("<div class='" + validationErrorClass + " " + validationResult.fieldName + "'><label for='work-phone'>" + validationResult.message + "</label></div>");
        }
        else if (validationResult.fieldName === "password" || validationResult.fieldName === "passwordConfirm") {
            signup.validation.clearInvalidField(validationResult);
            $elem = $("#" + validationResult.fieldName);
            $elem.addClass(invalidClass);
            $elem.siblings("input").addClass(signup.validation.invalidClass);
            $elem.parent().append("<div class='" + validationErrorClass + " " + validationResult.fieldName + "'><label for='" + validationResult.fieldName + "'>" + validationResult.message + "</label></div>");
        }
        else {
            $elem = $("#" + validationResult.fieldName);
            // first remove any potentially set validation error so we can update with the latest
            signup.validation.clearInvalidField(validationResult);
            // now add the validation error	    
            $elem.addClass(invalidClass);
            $elem.parent().append("<div class='" + validationErrorClass + " " + validationResult.fieldName + "'><label for='" + validationResult.fieldName + "'>" + validationResult.message + "</label></div>");
        }
    },

    clearInvalidField: function (validationResult) {
        var $elem;
        if (validationResult.fieldName === "domesticPhone") {
            var $domesticPhoneFields = $("#phonearea, #phone-3, #phone-4");
            $domesticPhoneFields.removeClass(signup.validation.invalidClass);
            $domesticPhoneFields.first().siblings(".validationError." + validationResult.fieldName).remove();
        }
        else if (validationResult.fieldName === "internationalPhone") {
            $elem = $("#work-phone");
            $elem.removeClass(signup.validation.invalidClass);
            $elem.siblings(".validationError." + validationResult.fieldName).remove();
        }
        else if (validationResult.fieldName === "password" || validationResult.fieldName === "passwordConfirm") {
            $elem = $("#" + validationResult.fieldName);
            $elem.removeClass(signup.validation.invalidClass);
            $elem.siblings("input").removeClass(signup.validation.invalidClass);
            $elem.siblings(".validationError." + validationResult.fieldName).remove();
        }
        else {
            // validation of fields in our standard rules definition should be handled here (all the weird cases are above)
            $elem = $("#" + validationResult.fieldName);
            $elem.removeClass(signup.validation.invalidClass);
            $elem.siblings(".validationError." + validationResult.fieldName).remove();
        }
    },

    // Rules based system where id maps to a set of validation methods (rules are processed top to bottom)
    // most common validation types can be defined within this structure.
    // The id and validation rule will be used as keys  into the messages to describe the error.
    rules: {
        firstName: { required: true },
        lastName: { required: true },
        email1: { required: true, email: true },
        email2: { required: true, email: true, equalTo: "email1" },
        password: { required: true, password: true },
        passwordConfirm: { required: true, password: true, equalTo: "password" },
        cc_number: { required: true },
        "security-code": { required: true },
        company: { required: true },
        "billing-address": { required: true },
        city: { required: true },
        zip: { required: true },
        "phone-ext": { wholeNumber: true },
        internationalExt: { wholeNumber: true },
        State: { required: true }
    },

    // NOTE: the rules that do not fit into our standard rules system do not define their error messages here
    messages: {
        firstName: { required: "Please enter a valid first name" },
        lastName: { required: "Please enter a valid last name" },
        email1: { required: "Please enter a valid email address", email: "Please enter a valid email address" },
        email2: { required: "Please enter a valid email address", email: "Please enter a valid email address", equalTo: "The specified email addresses do not match" },
        password: { required: "Please enter a valid password", password: "Please enter a valid password" },
        passwordConfirm: { required: "Please enter a valid password", password: "Please enter a valid password", equalTo: "The specified passwords do not match" },
        cc_number: { required: "Please enter a valid credit card number" },
        "security-code": { required: "Please enter a valid CVV2 code" },
        company: { required: "Please enter a valid company name" },
        "billing-address": { required: "Please enter a valid billing address" },
        city: { required: "Please enter a valid billing city" },
        zip: { required: "Please enter a valid billing zip / postal code" },
        "phone-ext": { wholeNumber: "Only numbers can be entered in phone extension field" },
        internationalExt: { wholeNumber: "Only numbers can be entered in phone extension field" },
        State: { required: "Please enter a valid billing state / province" }
    },

    // validator methods must return true if the validation method is satisfied
    validators: {
        required: function (elemId) {
            return $.trim($("#" + elemId).val()).length != 0;
        },
        email: function (elemId) {
            var emailRegex = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,6}$/i;
            var emailVal = $("#" + elemId).val();
            return emailVal.match(emailRegex) !== null;
        },
        password: function (elemId) {
            var passwordVal = $("#" + elemId).val();
            // HACK: fix for browsers using a placeholder plugin script that swaps in a <input type="text"> element
            //       for type="password" fields. As a result the placeholder text needs to be ignored and treated like blank.
            if (passwordVal === "Create Password" || passwordVal === "Confirm Password") {
                passwordVal = "";
            }
            return (passwordVal.length >= 8 && passwordVal.match( /[A-z]/ ) && !passwordVal.match( /[!@#$%^&*(){}]/ ) && passwordVal.match( /[0-9]/ ));
        },
        wholeNumber: function (elemId) {
            var wholeNumberRegex = /^\d*$/;
            // or with empty string so we will treat empty or whole numbers as valid (addresses issue where 
            var numVal = $("#" + elemId).val() || "";
            return numVal.match(wholeNumberRegex) !== null;
        },
        equalTo: function (elemId1, elemId2) {
            var elemId1Val = $("#" + elemId1).val();
            var elemId2Val = $("#" + elemId2).val();

            // HACK: fix for browsers using a placeholder plugin script that swaps in a <input type="text"> element
            //       for type="password" fields. As a result treat the placeholder text as though the actual value is blank.
            if (elemId1Val === "Create Password") { elemId1Val = ""; }
            if (elemId2Val === "Confirm Password") { elemId2Val = ""; }

            // HACK: we treat empty string as equal (a required:true rule should be used if necessary before validating this rule).
            return elemId2Val == elemId1Val;
        }
    },

    validateField: function (fieldName) {
        var isValid = true;
        var message = null;

        // null will be obtained here if the fieldName does not have any defined rules.
        var fieldRules = signup.validation.rules[fieldName];
        for (var rule in fieldRules) {
            if ($("#" + fieldName).length == 0)
                break;
            var validator = signup.validation.getValidationMethod(rule);
            if (rule == "equalTo") {
                // this rule requires 2 fields:
                isValid = validator(fieldName, fieldRules[rule]);
            }
            else {
                isValid = validator(fieldName);
            }

            if (!isValid) {
                message = signup.validation.getValidationFailedMessage(fieldName, rule);
                break;
            }
        }
        // use null when we have a field that does not have any rules defined (uses the fact : null == undefined BUT null !== undefined)
        if (fieldRules == undefined) { return null; }
        else {
            return new signup.validation.ValidationResult(fieldName, isValid, message);
        }
    },

    getValidationMethod: function (ruleName) {
        return signup.validation.validators[ruleName];
    },

    getValidationFailedMessage: function (fieldName, rule) {
        var obj = signup.validation.messages[fieldName];
        return obj[rule];
    },

    ValidationResult: function (fieldName, status, message) {
        return {
            fieldName: fieldName,
            status: status,
            message: message
        };
    }

};