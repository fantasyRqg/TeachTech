package com.sqr.tt.domain;

import com.sqr.tt.entity.CourseEntity;
import com.sqr.tt.entity.JoinCourseUserEntity;
import com.sqr.tt.entity.Response;
import com.sqr.tt.entity.UserEntity;
import com.sqr.tt.repo.CourseRepository;
import com.sqr.tt.repo.JoinCourseUserRepo;
import com.sqr.tt.repo.UserRepository;
import com.sqr.tt.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static com.sqr.tt.Commons.FAILURE;
import static com.sqr.tt.Commons.SUCCESS;

/**
 * Created by wyj on 07/05/2017.
 */

@Controller
@RequestMapping(path = "user")
public class UserController {
    @Autowired
    private UserRepository mUserRepository;
    @Autowired
    private CourseRepository mCourseRepository;
    @Autowired
    private JoinCourseUserRepo mJoinCourseUserRepo;


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


    @PostMapping(path = "modify/nickName")
    @ResponseBody
    public Response<String> modifyNickName(@RequestParam int id, @RequestParam String token, @RequestParam String nickName) {
        UserEntity user = mUserRepository.findByIdAndToken(id, token);
        Response<String> response = new Response<>();

        if (user == null) {
            response.setStatus(FAILURE);
            response.setMessage("user invalid");

            return response;
        }


        user.setNickName(nickName);

        mUserRepository.save(user);

        response.setStatus(SUCCESS);
        return response;
    }

    @PostMapping(path = "modify/photo")
    @ResponseBody
    public Response<String> modifyPhoto(@RequestParam int id, @RequestParam String token, @RequestParam String photo) {
        UserEntity user = mUserRepository.findByIdAndToken(id, token);
        Response<String> response = new Response<>();

        if (user == null) {
            response.setStatus(FAILURE);
            response.setMessage("user invalid");

            return response;
        }
        if (!FileUtil.fileExist(photo)) {
            response.setStatus(FAILURE);
            response.setMessage("photo not upload");

            return response;
        }

        user.setProtrait(photo);
        mUserRepository.save(user);

        response.setStatus(SUCCESS);
        return response;
    }

    @GetMapping(path = "courses")
    @ResponseBody
    public Response<Iterable<CourseEntity>> getMyCourses(@RequestParam int id, @RequestParam String token) {
        UserEntity user = mUserRepository.findByIdAndToken(id, token);
        Response<Iterable<CourseEntity>> response = new Response<>();

        if (user == null) {
            response.setStatus(FAILURE);
            response.setMessage("user invalid");

            return response;
        }

        Iterable<JoinCourseUserEntity> allCourses = mJoinCourseUserRepo.findAllByUserId(id);
        List<Long> courseIds = new ArrayList<>();
        for (JoinCourseUserEntity jcue : allCourses) {
            courseIds.add(jcue.getCourseId());
        }

        Iterable<CourseEntity> all = mCourseRepository.findAll(courseIds);


        response.setData(all);
        response.setStatus(SUCCESS);
        return response;

    }
}
