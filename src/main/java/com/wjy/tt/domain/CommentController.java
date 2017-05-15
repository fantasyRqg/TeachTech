package com.wjy.tt.domain;

import com.wjy.tt.Response;
import com.wjy.tt.entity.CommentEntity;
import com.wjy.tt.entity.CourseEntity;
import com.wjy.tt.entity.UserEntity;
import com.wjy.tt.repo.CommentRepo;
import com.wjy.tt.repo.CourseRepository;
import com.wjy.tt.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

import static com.wjy.tt.Commons.FAILURE;
import static com.wjy.tt.Commons.SUCCESS;

/**
 * Created by wjy on 09/05/2017.
 */
@Controller
@RequestMapping(path = "comment")
public class CommentController {
    @Autowired
    private UserRepository mUserRepository;
    @Autowired
    private CourseRepository mCourseRepository;
    @Autowired
    private CommentRepo mCommentRepo;

    @PostMapping(path = "add")
    @ResponseBody
    public Response<String> addComment(@RequestParam long userId, @RequestParam String token, @RequestParam long courseId, @RequestParam String comment) {
        Response<String> response = new Response<>();

        UserEntity user = mUserRepository.findByIdAndToken(userId, token);

        if (user == null) {
            response.setStatus(FAILURE);
            response.setMessage("user invalid");

            return response;
        }

        CourseEntity course = mCourseRepository.findOne(courseId);

        if (course == null) {
            response.setStatus(FAILURE);
            response.setMessage("course not exist");
            return response;
        }


        CommentEntity ce = new CommentEntity();
        ce.setContent(comment);
        ce.setCourseId((int) courseId);
        ce.setUserId((int) userId);
        ce.setTiemstamp(new Timestamp(System.currentTimeMillis()));

        mCommentRepo.save(ce);

        response.setStatus(SUCCESS);

        return response;
    }

    @GetMapping(path = "course")
    @ResponseBody
    public Response<Iterable<CommentEntity>> getCommentsByCourse(@RequestParam long courseId) {
        Iterable<CommentEntity> all = mCommentRepo.findAllByCourseId(courseId);


        return Response.noNUllResponse(all, "not comment");
    }
}
