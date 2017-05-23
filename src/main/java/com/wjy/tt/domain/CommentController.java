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
import java.util.ArrayList;
import java.util.List;

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


    /**
     * 添加评论
     *
     * @param userId   用户id
     * @param token    用户 token
     * @param courseId 课程 id
     * @param comment  评论内容
     * @return
     */
    @PostMapping(path = "add")
    @ResponseBody
    public Response<Long> addComment(@RequestParam long userId, @RequestParam String token, @RequestParam long courseId, @RequestParam String comment) {
        Response<Long> response = new Response<>();

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
        ce.setCourseId(courseId);
        ce.setUserId(userId);
        ce.setTimestamp(new Timestamp(System.currentTimeMillis()));

        mCommentRepo.save(ce);

        List<CommentEntity> cList = mCommentRepo.findAllByUserIdAndCourseId(userId, courseId);

        long id = -1;
        for (CommentEntity c : cList) {
            if (c.getTimestamp().equals(ce.getTimestamp())) {
                id = c.getId();
                break;
            }
        }

        if (id == -1) {
            response.setStatus(FAILURE);
        } else {
            response.setStatus(SUCCESS);
            response.setData(id);
        }


        return response;
    }

    /**
     * 获取课程所有评论
     *
     * @param courseId 课程ID
     * @return
     */
    @GetMapping(path = "course")
    @ResponseBody
    public Response<Iterable<CommentUserEntity>> getCommentsByCourse(@RequestParam long courseId) {
        Iterable<CommentEntity> all = mCommentRepo.findAllByCourseId(courseId);

        List<CommentUserEntity> cueList = new ArrayList<>();
        for (CommentEntity ce : all) {
            UserEntity user = mUserRepository.findOne(ce.getUserId());
            CommentUserEntity commentUserEntity = new CommentUserEntity();
            commentUserEntity.setUserName(user.getNickName());
            commentUserEntity.setContent(ce.getContent());
            commentUserEntity.setCourseId(ce.getCourseId());
            commentUserEntity.setId(ce.getId());
            commentUserEntity.setTimestamp(ce.getTimestamp());
            commentUserEntity.setUserId(ce.getUserId());
            commentUserEntity.setUserImage(user.getProtrait());
            cueList.add(commentUserEntity);
        }

        return Response.noNUllResponse(cueList, "not comment");
    }


    /**
     * 删除评论
     *
     * @param commentId 评论ID
     * @return
     */
    @GetMapping(path = "del")
    @ResponseBody
    public Response<String> delComment(@RequestParam long commentId) {
        mCommentRepo.delete(commentId);

        return Response.noNUllResponse("success", "success");
    }
}
