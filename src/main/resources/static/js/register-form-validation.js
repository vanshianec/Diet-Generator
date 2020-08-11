$(function () {
    $("#registerForm").submit(function (event) {
        /* If one of the fields is not valid prevent the form submition */
        if (!validateUsername() || !validatePassword() || !validateConfirmPassword()) {
            event.preventDefault();
        }
    });

    let types = 'propertychange change click keyup input paste';
    let $usernameId = $("#username");
    let $passwordId = $("#password");
    let $confirmPasswordId = $("#confirmPassword");

    $usernameId.bind(types, function () {
        validateUsername();
    });
    $passwordId.bind(types, function () {
        validatePassword();
        validateConfirmPassword();
    });
    $confirmPasswordId.bind(types, function () {
        validateConfirmPassword();
    });

    function validateUsername() {
        let fieldValue = $usernameId.val();
        let $errorBlock = $("#usernameHelpBlock");
        $usernameId.removeClass("is-invalid");
        $usernameId.removeClass("is-valid");
        $errorBlock.text("");

        if (fieldValue.length < 3) {
            $usernameId.addClass("is-invalid");
            $errorBlock.text("Should be at least 3 characters");
            return false;
        }

        $usernameId.addClass("is-valid");
        return true;
    }

    function validatePassword() {
        let fieldValue = $passwordId.val();
        let $errorBlock = $("#passwordHelpBlock");
        $passwordId.removeClass("is-invalid");
        $passwordId.removeClass("is-valid");
        $errorBlock.text("");

        if (fieldValue.length < 5) {
            $passwordId.addClass("is-invalid");
            $errorBlock.text("Should be at least 5 characters");
            return false;
        }

        $passwordId.addClass("is-valid");
        return true;
    }

    function validateConfirmPassword() {
        let passwordValue = $passwordId.val();
        let confirmPasswordValue = $confirmPasswordId.val();
        let $errorBlock = $("#confirmPasswordHelpBlock");
        $confirmPasswordId.removeClass("is-invalid");
        $confirmPasswordId.removeClass("is-valid");
        $errorBlock.text("");

        if (confirmPasswordValue.length < 5) {
            $confirmPasswordId.addClass("is-invalid");
            $errorBlock.text("Should be at least 5 characters");
            return false;
        }
        else if(passwordValue !== confirmPasswordValue && passwordValue.length >= 5){
            $confirmPasswordId.addClass("is-invalid");
            $errorBlock.text("Passwords don't match");
            return false;
        }

        $confirmPasswordId.addClass("is-valid");
        return true;
    }
});