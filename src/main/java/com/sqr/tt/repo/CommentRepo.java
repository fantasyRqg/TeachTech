package com.sqr.tt.repo;

import com.sqr.tt.entity.CommentEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rqg on 09/05/2017.
 */
public interface CommentRepo extends CrudRepository<CommentEntity, Long> {
    Iterable<CommentEntity> findAllByCourseId(long courseId);
}
