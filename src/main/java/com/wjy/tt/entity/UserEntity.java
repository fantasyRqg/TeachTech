package com.wjy.tt.entity;

import javax.persistence.*;

/**
 * Created by rqg on 22/05/2017.
 */
@Entity
@Table(name = "user", schema = "teach", catalog = "")
public class UserEntity {
    private long mId;
    private String mLoginName;
    private String mPassword;
    private String mNickName;
    private String mProtrait;
    private String mPhone;
    private String mToken;
    private Integer mRemaining;

    @Id
    @Column(name = "id")
    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    @Basic
    @Column(name = "login_name")
    public String getLoginName() {
        return mLoginName;
    }

    public void setLoginName(String loginName) {
        mLoginName = loginName;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    @Basic
    @Column(name = "nick_name")
    public String getNickName() {
        return mNickName;
    }

    public void setNickName(String nickName) {
        mNickName = nickName;
    }

    @Basic
    @Column(name = "protrait")
    public String getProtrait() {
        return mProtrait;
    }

    public void setProtrait(String protrait) {
        mProtrait = protrait;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    @Basic
    @Column(name = "token")
    public String getToken() {
        return mToken;
    }

    public void setToken(String token) {
        mToken = token;
    }

    @Basic
    @Column(name = "remaining")
    public Integer getRemaining() {
        return mRemaining;
    }

    public void setRemaining(Integer remaining) {
        mRemaining = remaining;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (mId != that.mId) return false;
        if (mLoginName != null ? !mLoginName.equals(that.mLoginName) : that.mLoginName != null) return false;
        if (mPassword != null ? !mPassword.equals(that.mPassword) : that.mPassword != null) return false;
        if (mNickName != null ? !mNickName.equals(that.mNickName) : that.mNickName != null) return false;
        if (mProtrait != null ? !mProtrait.equals(that.mProtrait) : that.mProtrait != null) return false;
        if (mPhone != null ? !mPhone.equals(that.mPhone) : that.mPhone != null) return false;
        if (mToken != null ? !mToken.equals(that.mToken) : that.mToken != null) return false;
        return mRemaining != null ? mRemaining.equals(that.mRemaining) : that.mRemaining == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (mId ^ (mId >>> 32));
        result = 31 * result + (mLoginName != null ? mLoginName.hashCode() : 0);
        result = 31 * result + (mPassword != null ? mPassword.hashCode() : 0);
        result = 31 * result + (mNickName != null ? mNickName.hashCode() : 0);
        result = 31 * result + (mProtrait != null ? mProtrait.hashCode() : 0);
        result = 31 * result + (mPhone != null ? mPhone.hashCode() : 0);
        result = 31 * result + (mToken != null ? mToken.hashCode() : 0);
        result = 31 * result + (mRemaining != null ? mRemaining.hashCode() : 0);
        return result;
    }
}
