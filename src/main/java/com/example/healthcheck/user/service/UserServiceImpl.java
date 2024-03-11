package com.example.healthcheck.user.service;

import com.example.healthcheck.user.domain.User;
import com.example.healthcheck.user.repository.UserRepository;

import com.example.healthcheck.user.requestdto.UserCreateRequestDTO;
import com.example.healthcheck.user.requestdto.UserLoginRequestDTO;
import com.example.healthcheck.user.requestdto.UserPutRequestDTO;
import com.example.healthcheck.user.responsedto.UserGetResponseDTO;
import com.example.healthcheck.user.responsedto.UserLoginResponseDTO;
import com.example.healthcheck.util.http.HttpResponse;
import com.example.healthcheck.util.email.EmailService;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final EmailService emailService;

    public UserServiceImpl(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @Override
    @Transactional
    public HttpResponse createUser(UserCreateRequestDTO userCreateRequestDTO) {
        User user = new User(
                userCreateRequestDTO.getName(),
                userCreateRequestDTO.getPassword()
        );

        User saveUser = userRepository.save(user);

        HttpResponse httpResponse = new HttpResponse("User Created", "User Id " + user.getId() + " created");


        return httpResponse;
    }

    @Override
    @Transactional
    public UserGetResponseDTO getUser(UUID userId) {

        User user = userRepository.findById(userId);

        return new UserGetResponseDTO(
                user.getId(),
                user.getName(),
                user.getPassword()
        );
    }

    @Override
    @Transactional
    public List<UserGetResponseDTO> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserGetResponseDTO> userGetResponseDTOS = new ArrayList<>();

        for (User user:users){
            userGetResponseDTOS.add(new UserGetResponseDTO(user.getId(),user.getName(),user.getPassword()));
        }
        return userGetResponseDTOS;
    }

    @Override
    @Transactional
    public HttpResponse putUser(UserPutRequestDTO userPutRequestDTO) {
        User user = userRepository.findById(userPutRequestDTO.getId());
        user.setName(userPutRequestDTO.getName());
        user.setPassword(userPutRequestDTO.getPassword());
        userRepository.save(user);
        HttpResponse httpResponse = new HttpResponse("User Updated", "User Id " + user.getId() + " updated");


        return httpResponse;
    }

    @Override
    @Transactional
    public HttpResponse deleteUser(UUID userId) {
        User user = userRepository.findById(userId);
        userRepository.deleteById(userId);
        HttpResponse httpResponse = new HttpResponse("User Deleted", "User Id " + user.getId() + " deleted");

        return httpResponse;
    }

    @Override
    @Transactional
    public HttpResponse deleteUsers() {
        userRepository.deleteAll();
        HttpResponse httpResponse = new HttpResponse("User Deleted","");

        return httpResponse;

    }

    @Override
    public String loginUsers(UserLoginRequestDTO userLoginRequestDTO) {
        UserLoginResponseDTO userLoginResponseDTO = userRepository.findByName(userLoginRequestDTO.getName());
        System.out.println("userLoginResponseDTO = " + userLoginResponseDTO);
        if(Objects.equals(userLoginResponseDTO.getName(), userLoginRequestDTO.getName())&&Objects.equals(userLoginResponseDTO.getPassword(), userLoginRequestDTO.getPassword())){
            return "response.ok";
        }else{
            return "response.fail";
        }
    }

    @Override
    public String verifyEmail(UserCreateRequestDTO userCreateRequestDTO) {
        String title = "Travel with me 이메일 인증 번호";
        String authCode = this.createCode();
        emailService.sendEmail(userCreateRequestDTO,title,authCode);
        /*User user = User.builder()
                .email(userCreateRequestDTO.getEmail())
                .isVerified(false)
                .build();

        userRepository.save(user);*/
        return null;
    }


    private String createCode() {
        int lenth = 6;
        try {
            Random random = SecureRandom.getInstanceStrong();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < lenth; i++) {
                builder.append(random.nextInt(10));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("실패");
        }
    }

}