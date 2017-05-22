package com.wjy.tt.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by rqg on 22/05/2017.
 */
@Entity
@Table(name = "comment", schema = "teach", catalog = "")
public class CommentEntity {
    private long mId;
    private long mUserId;
    private long mCourseId;
    private String mContent;
    private Timestamp mTimestamp;

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
    @Column(name = "timestamp")
    public Timestamp getTimestamp() {
        return mTimestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        mTimestamp = timestamp;
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
        return mTimestamp != null ? mTimestamp.equals(that.mTimestamp) : that.mTimestamp == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (mId ^ (mId >>> 32));
        result = 31 * result + (int) (mUserId ^ (mUserId >>> 32));
        result = 31 * result + (int) (mCourseId ^ (mCourseId >>> 32));
        result = 31 * result + (mContent != null ? mContent.hashCode() : 0);
        result = 31 * result + (mTimestamp != null ? mTimestamp.hashCode() : 0);
        return result;
    }
}
