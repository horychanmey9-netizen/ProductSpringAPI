package com.example.ProductSpringAPI.service.serviceImplement;

import com.example.ProductSpringAPI.dto.request.UserRequest;
import com.example.ProductSpringAPI.dto.response.ProductResponse;
import com.example.ProductSpringAPI.dto.response.UserResponse;
import com.example.ProductSpringAPI.entity.Product;
import com.example.ProductSpringAPI.entity.User;
import com.example.ProductSpringAPI.exception.UserNotFound;
import com.example.ProductSpringAPI.repository.UserRepository;
import com.example.ProductSpringAPI.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public UserResponse create(UserRequest userRequest){
        User user= new User();
        user.setName(userRequest.getName());
        user.setGender(userRequest.getGender());
        user.setAge(userRequest.getAge());
        user.setEmail(userRequest.getEmail());
        user=userRepository.save(user);

        UserResponse userResponse=new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setGender(user.getGender());
        userResponse.setAge(user.getAge());
        userResponse.setEmail(user.getEmail());

        userResponse.setCreatedAt(user.getCreatedAt());

        return userResponse;
    }
    public List<UserResponse> getData() {
        List<User> users=userRepository.findAll();
        List<UserResponse> userResponses=new ArrayList<>();
        for (User user:users){
            UserResponse userResponse =new UserResponse();
            userResponse.setId(user.getId());
            userResponse.setName(user.getName());
            userResponse.setGender(user.getGender());
            userResponse.setAge(user.getAge());
            userResponse.setEmail(user.getEmail());

            userResponse.setCreatedAt(user.getCreatedAt());
            userResponse.setUpdatedAt(user.getUpdatedAt());

            userResponses.add(userResponse);
        }
        return userResponses;
    }
    @Override
    public void deleteById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFound("User not found"));
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse update(Long id, UserRequest userRequest) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFound("User not found"));

        user.setName(userRequest.getName());
        user.setGender(userRequest.getGender());
        user.setAge(userRequest.getAge());
        user.setEmail(userRequest.getEmail());

        user = userRepository.save(user);

        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setGender(user.getGender());
        response.setAge(user.getAge());
        response.setEmail(user.getEmail());

        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());

        return response;
    }

}
