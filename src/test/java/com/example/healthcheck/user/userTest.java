package com.example.healthcheck.user;

import com.example.healthcheck.user.domain.User;
import com.example.healthcheck.user.repository.UserRepository;
import com.example.healthcheck.user.requestdto.UserCreateRequestDTO;
import com.example.healthcheck.user.requestdto.UserPutRequestDTO;
import com.example.healthcheck.user.responsedto.UserGetResponseDTO;
import com.example.healthcheck.user.service.UserService;
import com.example.healthcheck.util.http.HttpResponse;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@SpringBootTest
public class userTest {


    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    User userTest1;
    User userTest2;
    User userTest3;
    User userTest4;
    User userTest5;


    @BeforeEach
    public void setUp(){
        this.userTest1 = new User("Kang","1234");

        this.userTest2 = new User("Kim","6543");

        this.userTest3 = new User("Shin","7456");

        this.userTest4 = new User("Lee","1145");

        this.userTest5 = new User("Park","2345");

        userService.deleteUsers();

        System.out.println("userTest1.setUp");
    }

    @Test
    @Transactional
    @DisplayName("생성 테스트")
    public void createUser(){
        System.out.println("userTest1.createUser");

        UserCreateRequestDTO userCreateRequestDTO = new UserCreateRequestDTO(userTest1.getName(), userTest1.getPassword());

        HttpResponse httpResponse = userService.createUser(userCreateRequestDTO);

        assertThat(httpResponse.getMessage(),is("User Created"));
        assertThat(userCreateRequestDTO.getName(),is(userTest1.getName()));
        assertThat(userCreateRequestDTO.getPassword(),is(userTest1.getPassword()));
    }

    @Test
    @Transactional
    @DisplayName("단일 조회 테스트")
    public void getUser(){
        System.out.println("userTest1.getUser");

        UserCreateRequestDTO userCreateRequestDTO = new UserCreateRequestDTO(userTest5.getName(), userTest5.getPassword());

        HttpResponse httpResponse = userService.createUser(userCreateRequestDTO);
        String description = httpResponse.getDescription();

    // Extracting the UUID using a regular expression
        String uuidString = description.replaceAll(".*?(\\b[0-9a-f]{8}-([0-9a-f]{4}-){3}[0-9a-f]{12}\\b).*", "$1");

    // Parsing the extracted UUID string into a UUID object
        UUID userId = UUID.fromString(uuidString);

    // Now you can use this UUID for further processing
        UserGetResponseDTO userGetResponseDTO = userService.getUser(userId);

    // Asserting using the extracted UUID
        assertThat(userGetResponseDTO.getName(), is(userTest5.getName()));
        assertThat(userGetResponseDTO.getPassword(), is(userTest5.getPassword()));


    }

    @Test
    @Transactional
    @DisplayName("전체 조회 테스트")
    public void getUsers(){
        System.out.println("userTest1.getUsers");

        UserCreateRequestDTO userCreateRequestDTO1 = new UserCreateRequestDTO(userTest1.getName(), userTest1.getPassword());
        userService.createUser(userCreateRequestDTO1);

        UserCreateRequestDTO userCreateRequestDTO2 = new UserCreateRequestDTO(userTest2.getName(), userTest2.getPassword());
        userService.createUser(userCreateRequestDTO2);

        UserCreateRequestDTO userCreateRequestDTO3 = new UserCreateRequestDTO(userTest3.getName(), userTest3.getPassword());
        userService.createUser(userCreateRequestDTO3);

        UserCreateRequestDTO userCreateRequestDTO4 = new UserCreateRequestDTO(userTest4.getName(), userTest4.getPassword());
        userService.createUser(userCreateRequestDTO4);

        UserCreateRequestDTO userCreateRequestDTO5 = new UserCreateRequestDTO(userTest5.getName(), userTest5.getPassword());
        userService.createUser(userCreateRequestDTO5);

        List<UserGetResponseDTO> userGetResponseDTOList = userService.getUsers();

        assertThat(userGetResponseDTOList.size(),is(5));
    }

    @Test
    @Transactional
    @DisplayName("수정 테스트")
    public void putUser(){
        System.out.println("userTest1.putUser");
        //유저 생성
        UserCreateRequestDTO userCreateRequestDTO1 = new UserCreateRequestDTO(userTest1.getName(), userTest1.getPassword());
        HttpResponse httpResponse1 = userService.createUser(userCreateRequestDTO1);



        String description = httpResponse1.getDescription();
        // Extracting the UUID using a regular expression
        String uuidString = description.replaceAll(".*?(\\b[0-9a-f]{8}-([0-9a-f]{4}-){3}[0-9a-f]{12}\\b).*", "$1");

        // Parsing the extracted UUID string into a UUID object
        UUID userId = UUID.fromString(uuidString);

        //유저 수정
        UserPutRequestDTO userPutRequestDTO = new UserPutRequestDTO(userId,userTest2.getName(), userTest2.getPassword());
        HttpResponse httpResponse2 = userService.putUser(userPutRequestDTO);

        String description2 = httpResponse2.getDescription();
        // Extracting the UUID using a regular expression
        String uuidString2 = description2.replaceAll(".*?(\\b[0-9a-f]{8}-([0-9a-f]{4}-){3}[0-9a-f]{12}\\b).*", "$1");

        // Parsing the extracted UUID string into a UUID object
        UUID userId2 = UUID.fromString(uuidString2);


        //변경된 유저 조회 및 검증
        UserGetResponseDTO userGetResponseDTO = userService.getUser(userId2);

        assertThat(userGetResponseDTO.getName(),is(userTest2.getName()));
        assertThat(userGetResponseDTO.getPassword(),is(userTest2.getPassword()));

    }

    @Test
    @Transactional
    @DisplayName("삭제 테스트")
    public void deleteUser(){
        System.out.println("userTest1.deleteUser");
        //유저 생성
        UserCreateRequestDTO userCreateRequestDTO = new UserCreateRequestDTO(userTest1.getName(), userTest1.getPassword());
        HttpResponse httpResponse = userService.createUser(userCreateRequestDTO);

        String description = httpResponse.getDescription();
        // Extracting the UUID using a regular expression
        String uuidString = description.replaceAll(".*?(\\b[0-9a-f]{8}-([0-9a-f]{4}-){3}[0-9a-f]{12}\\b).*", "$1");

        // Parsing the extracted UUID string into a UUID object
        UUID userId = UUID.fromString(uuidString);

        //유저 삭제
        userService.deleteUser(userId);

        //삭제 확인
        List<UserGetResponseDTO> userGetResponseDTOList = userService.getUsers();
        assertThat(userGetResponseDTOList.size(),is(0));
    }

    @Test
    @Transactional
    @DisplayName("전체 삭제 테스트")
    public void deleteUsers(){
        System.out.println("userTest1.deleteUsers");
        //유저 생성
        UserCreateRequestDTO userCreateRequestDTO1 = new UserCreateRequestDTO(userTest1.getName(), userTest1.getPassword());
        HttpResponse httpResponse1 = userService.createUser(userCreateRequestDTO1);

        UserCreateRequestDTO userCreateRequestDTO2 = new UserCreateRequestDTO(userTest2.getName(), userTest2.getPassword());
        HttpResponse httpResponse2 = userService.createUser(userCreateRequestDTO2);

        UserCreateRequestDTO userCreateRequestDTO3 = new UserCreateRequestDTO(userTest3.getName(), userTest3.getPassword());
        HttpResponse httpResponse3 = userService.createUser(userCreateRequestDTO3);

        UserCreateRequestDTO userCreateRequestDTO4 = new UserCreateRequestDTO(userTest4.getName(), userTest4.getPassword());
        HttpResponse httpResponse4 = userService.createUser(userCreateRequestDTO4);

        UserCreateRequestDTO userCreateRequestDTO5 = new UserCreateRequestDTO(userTest5.getName(), userTest5.getPassword());
        HttpResponse httpResponse5 = userService.createUser(userCreateRequestDTO5);

        //유저 삭제
        userService.deleteUsers();

        //삭제 확인
        List<UserGetResponseDTO> userGetResponseDTOList = userService.getUsers();
        assertThat(userGetResponseDTOList.size(),is(0));
    }

    @AfterEach
    public void cleanUp(){
        System.out.println("userTest1.cleanUp");
        userService.deleteUsers();

        //삭제 확인
        List<UserGetResponseDTO> userGetResponseDTOList = userService.getUsers();
        assertThat(userGetResponseDTOList.size(),is(0));
    }
}
