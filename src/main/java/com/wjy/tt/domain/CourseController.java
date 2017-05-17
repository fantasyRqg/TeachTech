package com.wjy.tt.domain;

import com.wjy.tt.Response;
import com.wjy.tt.entity.CourseEntity;
import com.wjy.tt.entity.JoinCourseUserEntity;
import com.wjy.tt.entity.TeacherEntity;
import com.wjy.tt.entity.UserEntity;
import com.wjy.tt.repo.CourseRepository;
import com.wjy.tt.repo.JoinCourseUserRepo;
import com.wjy.tt.repo.TeacherRepo;
import com.wjy.tt.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.wjy.tt.Commons.FAILURE;
import static com.wjy.tt.Commons.SUCCESS;

/**
 * Created by wjy on 09/05/2017.
 */
@Controller
@RequestMapping(path = "course")
public class CourseController {
    @Autowired
    CourseRepository mCourseRepository;
    @Autowired
    TeacherRepo mTeacherRepo;
    @Autowired
    UserRepository mUserRepository;
    @Autowired
    JoinCourseUserRepo mJoinCourseUserRepo;

    @GetMapping(path = "all")
    @ResponseBody
    public Response<Iterable<CourseEntity>> getAllCourses() {
        Iterable<CourseEntity> all = mCourseRepository.findAll();

        ArrayList<CourseEntity> ces = new ArrayList<>();

        System.out.println(all);
        return Response.noNUllResponse(all, "can not load courses");
    }

    @GetMapping(path = "info")
    @ResponseBody
    public Response<CourseEntity> getCourseById(@RequestParam Long id) {
        CourseEntity one = mCourseRepository.findOne(id);

        return Response.noNUllResponse(one, "course not exist");
    }


    @GetMapping(path = "teacher")
    @ResponseBody
    public Response<TeacherEntity> getTeacherById(@RequestParam Long id) {
        TeacherEntity one = mTeacherRepo.findOne(id);

        return Response.noNUllResponse(one, "can not find teacher");
    }


    @GetMapping(path = "hasbuy")
    @ResponseBody
    public Response<Boolean> userHasByCourse(@RequestParam long userId, @RequestParam long courseId) {
        List<JoinCourseUserEntity> join = mJoinCourseUserRepo.findAllByCourseIdAndUserId(courseId, userId);
        if (join.size() == 0)
            return Response.noNUllResponse(false, "");
        return Response.noNUllResponse(join.get(0) != null, "");
    }


    @PostMapping(path = "buy")
    @ResponseBody
    public Response<String> buyCourse(@RequestParam long courseId, @RequestParam long userId, @RequestParam String token) {
        CourseEntity course = mCourseRepository.findOne(courseId);
        Response<String> response = new Response<>();

        if (course == null) {
            response.setStatus(FAILURE);
            response.setMessage("course not exist");
            return response;
        }

        UserEntity user = mUserRepository.findByIdAndToken(userId, token);

        if (user == null || (user.getRemaining() - course.getPrice() < 0)) {
            response.setStatus(FAILURE);
            response.setMessage("user invalid");
            return response;
        }


        JoinCourseUserEntity cu = new JoinCourseUserEntity();

        cu.setCourseId((int) courseId);
        cu.setUserId((int) userId);
        mJoinCourseUserRepo.save(cu);

        user.setRemaining(user.getRemaining() - course.getPrice());
        mUserRepository.save(user);

        response.setStatus(SUCCESS);


        return response;
    }


    @PostMapping(path = "add")
    @ResponseBody
    public Response<String> addCourse(@RequestBody CourseEntity course) {

        Response<String> response = new Response<>();
        try {
            mCourseRepository.save(course);
        } catch (Exception e) {
            e.printStackTrace();

            response.setStatus(FAILURE);
            return response;
        }


        response.setStatus(SUCCESS);
        return response;

    }


    @GetMapping(path = "remove")
    @ResponseBody
    Response<String> removeCourse(@RequestParam long id) {
        Response<String> response = new Response<>();

        mCourseRepository.delete(id);

        response.setStatus(SUCCESS);
        return response;
    }


}
