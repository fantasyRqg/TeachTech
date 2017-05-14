package com.sqr.tt.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by rqg on 14/05/2017.
 */
@Entity
@Table(name = "comment", schema = "teach", catalog = "")
public class CommentEntity {
    private long mId;
    private long mUserId;
    private long mCourseId;
    private String mContent;
    private Timestamp mTiemstamp;

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

    @Basic
    @Column(name = "content")
    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    @Basic
    @Column(name = "tiemstamp")
    public Timestamp getTiemstamp() {
        return mTiemstamp;
    }

    public void setTiemstamp(Timestamp tiemstamp) {
        mTiemstamp = tiemstamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentEntity that = (CommentEntity) o;

        if (mId != that.mId) return false;
        if (mUserId != that.mUserId) return false;
        if (mCourseId != that.mCourseId) return false;
        if (mContent != null ? !mContent.equals(that.mContent) : that.mContent != null) return false;
        return mTiemstamp != null ? mTiemstamp.equals(that.mTiemstamp) : that.mTiemstamp == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (mId ^ (mId >>> 32));
        result = 31 * result + (int) (mUserId ^ (mUserId >>> 32));
        result = 31 * result + (int) (mCourseId ^ (mCourseId >>> 32));
        result = 31 * result + (mContent != null ? mContent.hashCode() : 0);
        result = 31 * result + (mTiemstamp != null ? mTiemstamp.hashCode() : 0);
        return result;
    }
}
