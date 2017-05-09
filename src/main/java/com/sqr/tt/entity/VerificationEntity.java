package com.sqr.tt.entity;

import javax.persistence.*;

/**
 * Created by rqg on 09/05/2017.
 */
@Entity
@Table(name = "verification", schema = "teach", catalog = "")
public class VerificationEntity {
    private int mId;
    private String mCode;
    private String mPicture;

    @Id
    @Column(name = "id")
    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return mCode;
    }

    public void setCode(String code) {
        mCode = code;
    }

    @Basic
    @Column(name = "picture")
    public String getPicture() {
        return mPicture;
    }

    public void setPicture(String picture) {
        mPicture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VerificationEntity that = (VerificationEntity) o;

        if (mId != that.mId) return false;
        if (mCode != null ? !mCode.equals(that.mCode) : that.mCode != null) return false;
        return mPicture != null ? mPicture.equals(that.mPicture) : that.mPicture == null;
    }

    @Override
    public int hashCode() {
        int result = mId;
        result = 31 * result + (mCode != null ? mCode.hashCode() : 0);
        result = 31 * result + (mPicture != null ? mPicture.hashCode() : 0);
        return result;
    }
}
