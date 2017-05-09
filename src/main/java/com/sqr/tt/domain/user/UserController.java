package com.sqr.tt.domain.user;

import com.sqr.tt.entity.Response;
import com.sqr.tt.entity.UserEntity;
import com.sqr.tt.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.Base64;

import static com.sqr.tt.Commons.FAILURE;
import static com.sqr.tt.Commons.SUCCESS;

/**
 * Created by rqg on 07/05/2017.
 */

@Controller
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserRepository mUserRepository;

    private static SecureRandom sRandom = new SecureRandom();


    private String generateToken() {
        byte bytes[] = new byte[20];
        sRandom.nextBytes(bytes);
        return new String(Base64.getEncoder().encode(bytes)).substring(0, 20);

    }

    @GetMapping(path = "all")
    @ResponseBody
    public Iterable<UserEntity> getAllUsers() {
        return mUserRepository.findAll();
    }


    @PostMapping(path = "register")
    @ResponseBody
    public Response<UserEntity> registerUser(@RequestBody UserEntity user) {
        Response<UserEntity> response = new Response<>();

        UserEntity save = null;


        try {
            mUserRepository.save(user);

            save = mUserRepository.findByLoginName(user.getLoginName());

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(FAILURE);
            response.setMessage(FAILURE);
            response.setData(null);
            return response;
        }


        if (save != null && checkToken(save)) {
            response.setStatus(SUCCESS);
            response.setData(save);
        } else {
            response.setStatus(FAILURE);
        }
        return response;
    }

    /**
     * 检查token ，如果不存在则生成一个，并写入数据库
     *
     * @param user 待验证用户
     * @return 成功 true， 否则 false
     */
    private boolean checkToken(UserEntity user) {
        if (user.getToken() != null && !user.getToken().trim().equals("")) {
            return true;
        }

        user.setToken(generateToken());
        try {
            mUserRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @PostMapping(path = "login")
    @ResponseBody
    public Response<UserEntity> userLogin(@RequestParam String name, @RequestParam String pwd) {
        Response<UserEntity> response = new Response<>();

        UserEntity user = null;
        try {
            user = mUserRepository.findByLoginNameAndPassword(name, pwd);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(FAILURE);
            return response;
        }


        if (user != null && checkToken(user)) {
            response.setStatus(SUCCESS);
            response.setData(user);
        } else {
            response.setStatus(FAILURE);
            response.setMessage("username or password not correct");
        }
        return response;
    }


    @GetMapping(path = "phone/right")
    @ResponseBody
    public Response<String> rightPhone(@RequestParam String phone, @RequestParam String pwd) {
        Response<String> response = new Response<>();

        UserEntity user = mUserRepository.findByPhone(phone);

        if (user == null) {
            response.setMessage("信息错误");
            response.setStatus(FAILURE);
            return response;
        }


        user.setPassword(pwd);
        mUserRepository.save(user);
        response.setStatus(SUCCESS);

        return response;
    }

    @PostMapping(path = "password")
    @ResponseBody
    public Response<String> changePassword(@RequestParam int id, @RequestParam String token, @RequestParam String pwd) {

        Response<String> response = new Response<>();

        UserEntity user = mUserRepository.findByIdAndToken(id, token);

        if (user == null) {
            response.setMessage("信息错误");
            response.setStatus(FAILURE);
            return response;
        }

        user.setPassword(pwd);
        mUserRepository.save(user);

        response.setStatus(SUCCESS);

        return response;
    }


    @GetMapping(path = "info")
    @ResponseBody
    public Response<UserEntity> getUserById(@RequestParam int id, @RequestParam String token) {
        UserEntity user = mUserRepository.findByIdAndToken(id, token);


        return Response.noNUllResponse(user, "该用户不存在");
    }



}
