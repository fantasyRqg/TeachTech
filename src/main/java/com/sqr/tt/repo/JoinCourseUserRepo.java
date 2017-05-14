package com.sqr.tt.repo;

import com.sqr.tt.entity.JoinCourseUserEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by wyj on 09/05/2017.
 */
public interface JoinCourseUserRepo extends CrudRepository<JoinCourseUserEntity, Long> {
    Iterable<JoinCourseUserEntity> findAllByUserId(int userId);

    JoinCourseUserEntity findByCourseIdAndUserId(long courseId, long userId);
}
