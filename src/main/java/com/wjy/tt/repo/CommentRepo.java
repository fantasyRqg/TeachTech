package com.wjy.tt.repo;

import com.wjy.tt.entity.CommentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by wjy on 09/05/2017.
 */
public interface CommentRepo extends CrudRepository<CommentEntity, Long> {
    Iterable<CommentEntity> findAllByCourseId(long courseId);

    //    CommentEntity findByUserIdAndCourseIdAndTiemstamp(long userId, long courseId, Timestamp tiemstamp);
    List<CommentEntity> findAllByUserIdAndCourseId(long userId, long courseId);
}

