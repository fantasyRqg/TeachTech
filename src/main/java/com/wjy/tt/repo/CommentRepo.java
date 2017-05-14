package com.wjy.tt.repo;

import com.wjy.tt.entity.CommentEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by wjy on 09/05/2017.
 */
public interface CommentRepo extends CrudRepository<CommentEntity, Long> {
    Iterable<CommentEntity> findAllByCourseId(long courseId);
}
