package com.wjy.tt.domain;

import com.wjy.tt.entity.CommentEntity;

/**
 * Created by rqg on 22/05/2017.
 */
public class CommentUserEntity extends CommentEntity {
    private String mUserName;

    private String mUserImage;

    public String getUserImage() {
        return mUserImage;
    }

    public void setUserImage(String userImage) {
        mUserImage = userImage;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }
}
