package com.sqr.tt.repo;

import com.sqr.tt.entity.TeacherEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rqg on 09/05/2017.
 */
public interface TeacherRepo extends CrudRepository<TeacherEntity, Long> {
}
