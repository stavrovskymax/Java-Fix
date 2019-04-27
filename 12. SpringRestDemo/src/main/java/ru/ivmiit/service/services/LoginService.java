package ru.ivmiit.service.services;

import ru.ivmiit.service.forms.LoginForm;
import ru.ivmiit.service.transfer.TokenDto;

public interface LoginService {
    TokenDto login(LoginForm loginForm);
}
