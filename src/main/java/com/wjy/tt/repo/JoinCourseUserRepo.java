package com.wjy.tt.repo;

import com.wjy.tt.entity.JoinCourseUserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by wjy on 09/05/2017.
 */
public interface JoinCourseUserRepo extends CrudRepository<JoinCourseUserEntity, Long> {
    Iterable<JoinCourseUserEntity> findAllByUserId(int userId);

    List<JoinCourseUserEntity> findAllByCourseIdAndUserId(long courseId, long userId);
}
