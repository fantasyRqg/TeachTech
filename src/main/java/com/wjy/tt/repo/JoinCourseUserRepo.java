package com.wjy.tt.repo;

import com.wjy.tt.entity.JoinCourseUserEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by wjy on 09/05/2017.
 */
public interface JoinCourseUserRepo extends CrudRepository<JoinCourseUserEntity, Long> {
    Iterable<JoinCourseUserEntity> findAllByUserId(int userId);

    JoinCourseUserEntity findByCourseIdAndUserId(long courseId, long userId);
}
