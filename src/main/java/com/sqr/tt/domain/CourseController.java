package com.sqr.tt.domain;

import com.sqr.tt.entity.*;
import com.sqr.tt.repo.CourseRepository;
import com.sqr.tt.repo.JoinCourseUserRepo;
import com.sqr.tt.repo.TeacherRepo;
import com.sqr.tt.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.sqr.tt.Commons.FAILURE;
import static com.sqr.tt.Commons.SUCCESS;

/**
 * Created by rqg on 09/05/2017.
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


    @GetMapping(path = "buy")
    public Response<String> buyCourse(@RequestParam long courseId, @RequestParam long userId, @RequestParam String token) {
        CourseEntity course = mCourseRepository.findOne(courseId);
        Response<String> response = new Response<>();

        if (course == null) {
            response.setStatus(FAILURE);
            response.setMessage("course not exist");
            return response;
        }

        UserEntity user = mUserRepository.findByIdAndToken(userId, token);

        if (user == null) {
            response.setStatus(FAILURE);
            response.setMessage("user invalid");
            return response;
        }

        JoinCourseUserEntity cu = new JoinCourseUserEntity();

        cu.setCourseId((int) courseId);
        cu.setUserId((int) userId);

        mJoinCourseUserRepo.save(cu);

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
