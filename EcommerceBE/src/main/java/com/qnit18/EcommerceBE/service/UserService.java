package com.qnit18.EcommerceBE.service;

import com.qnit18.EcommerceBE.exception.UserException;
import com.qnit18.EcommerceBE.model.User;

public interface UserService {
    User findUserById(Long userId) throws UserException;

    User findUserProfileByJwt(String jwt) throws UserException;
}