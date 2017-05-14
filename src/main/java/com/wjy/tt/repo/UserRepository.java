package com.wjy.tt.repo;

import com.wjy.tt.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by wjy on 07/05/2017.
 */
@Transactional
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByLoginName(String loginName);

    UserEntity findByLoginNameAndPassword(String loginName, String password);

    UserEntity findByIdAndTokenAndPhone(long id, String token, String phone);

    UserEntity findByPhone(String phone);

    UserEntity findByIdAndToken(long id, String token);
}
