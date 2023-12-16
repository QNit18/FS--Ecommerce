package com.qnit18.EcommerceBE.impletation;

import com.qnit18.EcommerceBE.config.JwtProvider;
import com.qnit18.EcommerceBE.exception.UserException;
import com.qnit18.EcommerceBE.model.User;
import com.qnit18.EcommerceBE.repository.UserRepository;
import com.qnit18.EcommerceBE.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private UserRepository userRepository;
    private JwtProvider jwtProvider;

    @Autowired
    public UserServiceImp(UserRepository userRepository, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }
    @Override
    public User findUserById(Long userId) throws UserException {

        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            return user.get();
        }
        throw new UserException("User not found with id : " + userId);
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {
        String email = jwtProvider.getEmailFromToken(jwt);

        User user = userRepository.findByEmail(email);
        if(user==null){
            throw new UserException("User not found with email : " + email);
        }
        return user;
    }
}
