package com.wjy.tt.domain;

import com.wjy.tt.Response;
import com.wjy.tt.entity.CourseEntity;
import com.wjy.tt.entity.JoinCourseUserEntity;
import com.wjy.tt.entity.UserEntity;
import com.wjy.tt.entity.VerificationEntity;
import com.wjy.tt.repo.CourseRepository;
import com.wjy.tt.repo.JoinCourseUserRepo;
import com.wjy.tt.repo.UserRepository;
import com.wjy.tt.repo.VerificationRepo;
import com.wjy.tt.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static com.wjy.tt.Commons.FAILURE;
import static com.wjy.tt.Commons.SUCCESS;

/**
 * Created by wjy on 07/05/2017.
 */


/**
 * 用户相关接口
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
    @Autowired
    private VerificationRepo mVerificationRepo;


    private static SecureRandom sRandom = new SecureRandom();


    private String generateToken() {
        byte bytes[] = new byte[20];
        sRandom.nextBytes(bytes);
        return new String(Base64.getEncoder().encode(bytes)).substring(0, 20);

    }

    /**
     * 获取所有用户
     *
     * @return
     */
    @GetMapping(path = "all")
    @ResponseBody
    public Response<Iterable<UserEntity>> getAllUsers() {
        return Response.noNUllResponse(mUserRepository.findAll(), "no user");
    }


    /**
     * 注册接口
     *
     * @param user user info
     * @return
     */
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

    /**
     * 登陆接口
     *
     * @param name 用户名
     * @param pwd  密码
     * @return
     */
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


    /**
     * 检查手机是否正确，如果正确则修改密码，用于忘记密码
     *
     * @param phone 手机号码
     * @param pwd   新密码
     * @return
     */
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

    /**
     * 更改密码
     *
     * @param id    用户ID
     * @param token 用户token
     * @param pwd   新密码
     * @return
     */
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


    /**
     * 获取用户信息
     *
     * @param id    用户ID
     * @param token 用户token
     * @return
     */
    @GetMapping(path = "info")
    @ResponseBody
    public Response<UserEntity> getUserById(@RequestParam int id, @RequestParam String token) {
        UserEntity user = mUserRepository.findByIdAndToken(id, token);


        return Response.noNUllResponse(user, "该用户不存在");
    }

    /**
     * 修改用户昵称
     *
     * @param id       用户ID
     * @param token    用户token
     * @param nickName 新昵称
     * @return
     */
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

    /**
     * 修改用户头像
     *
     * @param id    用户ID
     * @param token 用户token
     * @param photo 用户头像文件名
     * @return
     */
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

    /**
     * 获取一个用户购买的所有课程
     *
     * @param id    用户ID
     * @param token 用户token
     * @return
     */
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

    /**
     * 获取验证码， 获得验证码图片以及验证码值
     *
     * @return
     */
    @GetMapping(path = "verify")
    @ResponseBody
    public Response<VerificationEntity> getVerification() {
        Iterable<VerificationEntity> all = mVerificationRepo.findAll();

        List<VerificationEntity> vList = new ArrayList<>();
        for (VerificationEntity entity : all) {
            vList.add(entity);
        }

        VerificationEntity ve = null;
        if (vList.size() >= 0) {
            ve = vList.get((int) (System.currentTimeMillis() % vList.size()));
        }


        return Response.noNUllResponse(ve, "no re");

    }

    /**
     * 充值接口
     *
     * @param id     用户ID
     * @param token  用户token
     * @param charge 充值数据
     * @return
     */
    @PostMapping(path = "recharge")
    @ResponseBody
    public Response<Integer> recharge(@RequestParam int id, @RequestParam String token, @RequestParam int charge) {
        UserEntity user = mUserRepository.findByIdAndToken(id, token);
        Response<Integer> response = new Response<>();

        if (user == null) {
            response.setStatus(FAILURE);
            response.setMessage("user invalid");

            return response;
        }

        user.setRemaining(charge);

        mUserRepository.save(user);

        response.setStatus(SUCCESS);
        response.setData(charge);


        return response;
    }

    /**
     * 修改用户信息
     *
     * @param id     用户ID
     * @param token  用户token
     * @param avator 用户头像文件名
     * @param nick   昵称
     * @param phone  手机号码
     * @return
     */
    @PostMapping(path = "modify")
    @ResponseBody
    public Response<String> modifyInfo(@RequestParam int id, @RequestParam String token, @RequestParam String avator, @RequestParam String nick, @RequestParam String phone) {
        UserEntity user = mUserRepository.findByIdAndToken(id, token);
        Response<String> response = new Response<>();

        if (user == null) {
            response.setStatus(FAILURE);
            response.setMessage("user invalid");

            return response;
        }

        user.setProtrait(avator);
        user.setNickName(nick);
        user.setPhone(phone);

        mUserRepository.save(user);

        response.setStatus(SUCCESS);

        return response;

    }


    /**
     * 删除用户
     *
     * @param id 用户 id
     * @return
     */
    @GetMapping(path = "del")
    @ResponseBody
    public Response<String> delUser(@RequestParam long id) {
        Response<String> response = new Response<>();

        UserEntity user = mUserRepository.findOne(id);
        if (user == null) {
            response.setStatus(FAILURE);
            response.setMessage("user id invalid");

            return response;
        }

        mUserRepository.delete(id);


        response.setStatus(SUCCESS);

        return response;
    }
}
