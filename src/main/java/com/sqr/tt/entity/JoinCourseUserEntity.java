package com.sqr.tt.entity;

import javax.persistence.*;

/**
 * Created by rqg on 14/05/2017.
 */
@Entity
@Table(name = "join_course_user", schema = "teach", catalog = "")
public class JoinCourseUserEntity {
    private long mId;
    private long mUserId;
    private long mCourseId;

    @Id
    @Column(name = "id")
    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    @Basic
    @Column(name = "user_id")
    public long getUserId() {
        return mUserId;
    }

    public void setUserId(long userId) {
        mUserId = userId;
    }

    @Basic
    @Column(name = "course_id")
    public long getCourseId() {
        return mCourseId;
    }

    public void setCourseId(long courseId) {
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
        int result = (int) (mId ^ (mId >>> 32));
        result = 31 * result + (int) (mUserId ^ (mUserId >>> 32));
        result = 31 * result + (int) (mCourseId ^ (mCourseId >>> 32));
        return result;
    }
}
