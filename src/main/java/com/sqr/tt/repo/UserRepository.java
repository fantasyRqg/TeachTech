package com.sqr.tt.repo;

import com.sqr.tt.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by rqg on 07/05/2017.
 */
@Transactional
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByLoginName(String loginName);

    UserEntity findByLoginNameAndPassword(String loginName, String password);
}
