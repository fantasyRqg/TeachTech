package com.wjy.tt.repo;

import com.wjy.tt.entity.TeacherEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by wjy on 09/05/2017.
 */
public interface TeacherRepo extends CrudRepository<TeacherEntity, Long> {
}
