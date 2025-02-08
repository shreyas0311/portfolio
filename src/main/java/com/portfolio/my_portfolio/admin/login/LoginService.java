package com.portfolio.my_portfolio.admin.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.portfolio.my_portfolio.admin.login.LoginController.LoginForm;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    public boolean validateCredentials(LoginForm loginForm) {
        return loginRepository.validateCredentials(
                loginForm.username(),
                loginForm.password()).isPresent();
    }
}
