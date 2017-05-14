package com.sqr.tt.entity;

import javax.persistence.*;

/**
 * Created by wyj on 14/05/2017.
 */
@Entity
@Table(name = "course", schema = "teach", catalog = "")
public class CourseEntity {
    private long mId;
    private String mName;
    private String mIntrodcution;
    private long mTeacherId;
    private String mVideo;
    private int mPrice;
    private String mImage;

    @Id
    @Column(name = "id")
    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    @Basic
    @Column(name = "introdcution")
    public String getIntrodcution() {
        return mIntrodcution;
    }

    public void setIntrodcution(String introdcution) {
        mIntrodcution = introdcution;
    }

    @Basic
    @Column(name = "teacher_id")
    public long getTeacherId() {
        return mTeacherId;
    }

    public void setTeacherId(long teacherId) {
        mTeacherId = teacherId;
    }

    @Basic
    @Column(name = "video")
    public String getVideo() {
        return mVideo;
    }

    public void setVideo(String video) {
        mVideo = video;
    }

    @Basic
    @Column(name = "price")
    public int getPrice() {
        return mPrice;
    }

    public void setPrice(int price) {
        mPrice = price;
    }

    @Basic
    @Column(name = "image")
    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseEntity that = (CourseEntity) o;

        if (mId != that.mId) return false;
        if (mTeacherId != that.mTeacherId) return false;
        if (mPrice != that.mPrice) return false;
        if (mName != null ? !mName.equals(that.mName) : that.mName != null) return false;
        if (mIntrodcution != null ? !mIntrodcution.equals(that.mIntrodcution) : that.mIntrodcution != null)
            return false;
        if (mVideo != null ? !mVideo.equals(that.mVideo) : that.mVideo != null) return false;
        return mImage != null ? mImage.equals(that.mImage) : that.mImage == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (mId ^ (mId >>> 32));
        result = 31 * result + (mName != null ? mName.hashCode() : 0);
        result = 31 * result + (mIntrodcution != null ? mIntrodcution.hashCode() : 0);
        result = 31 * result + (int) (mTeacherId ^ (mTeacherId >>> 32));
        result = 31 * result + (mVideo != null ? mVideo.hashCode() : 0);
        result = 31 * result + mPrice;
        result = 31 * result + (mImage != null ? mImage.hashCode() : 0);
        return result;
    }
}
