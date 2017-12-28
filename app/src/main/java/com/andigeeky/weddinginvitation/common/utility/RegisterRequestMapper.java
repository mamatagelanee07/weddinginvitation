package com.andigeeky.weddinginvitation.common.utility;

import com.andigeeky.weddinginvitation.domain.service.RegisterUserRequest;
import com.andigeeky.weddinginvitation.model.AccountType;
import com.andigeeky.weddinginvitation.model.User;
import com.google.firebase.auth.AuthCredential;

/**
 * Created by E066733 on 12/28/2017.
 */

public class RegisterRequestMapper {
    public static RegisterUserRequest registerWithEmailAndPassword(String email, String password) {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        registerUserRequest.setAccountType(AccountType.PASSWORD);
        registerUserRequest.setUser(user);
        return registerUserRequest;
    }

    public static RegisterUserRequest registerWithGoogle(AuthCredential authCredential) {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setAccountType(AccountType.GOOGLE);
        registerUserRequest.setAuthCredential(authCredential);
        return registerUserRequest;
    }

    public static RegisterUserRequest registerWithFacebook(AuthCredential authCredential) {
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setAccountType(AccountType.FACEBOOK);
        registerUserRequest.setAuthCredential(authCredential);
        return registerUserRequest;
    }
}
