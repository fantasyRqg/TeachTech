package com.sqr.tt.entity;

import javax.persistence.*;

/**
 * Created by rqg on 09/05/2017.
 */
@Entity
@Table(name = "join_course_user", schema = "teach", catalog = "")
public class JoinCourseUserEntity {
    private int mId;
    private int mUserId;
    private int mCourseId;

    @Id
    @Column(name = "id")
    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    @Basic
    @Column(name = "user_id")
    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int userId) {
        mUserId = userId;
    }

    @Basic
    @Column(name = "course_id")
    public int getCourseId() {
        return mCourseId;
    }

    public void setCourseId(int courseId) {
        mCourseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JoinCourseUserEntity that = (JoinCourseUserEntity) o;

        if (mId != that.mId) return false;
        if (mUserId != that.mUserId) return false;
        return mCourseId == that.mCourseId;
    }

    @Override
    public int hashCode() {
        int result = mId;
        result = 31 * result + mUserId;
        result = 31 * result + mCourseId;
        return result;
    }
}
