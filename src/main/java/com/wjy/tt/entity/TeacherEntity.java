package com.wjy.tt.entity;

import javax.persistence.*;

/**
 * Created by wjy on 15/05/2017.
 */
@Entity
@Table(name = "teacher", schema = "teach", catalog = "")
public class TeacherEntity {
    private long mId;
    private String mName;
    private String mSubject;
    private String mGraduate;
    private Integer mTeachStart;
    private String mIntroduction;
    private String mPhoto;

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
    @Column(name = "subject")
    public String getSubject() {
        return mSubject;
    }

    public void setSubject(String subject) {
        mSubject = subject;
    }

    @Basic
    @Column(name = "graduate")
    public String getGraduate() {
        return mGraduate;
    }

    public void setGraduate(String graduate) {
        mGraduate = graduate;
    }

    @Basic
    @Column(name = "teach_start")
    public Integer getTeachStart() {
        return mTeachStart;
    }

    public void setTeachStart(Integer teachStart) {
        mTeachStart = teachStart;
    }

    @Basic
    @Column(name = "introduction")
    public String getIntroduction() {
        return mIntroduction;
    }

    public void setIntroduction(String introduction) {
        mIntroduction = introduction;
    }

    @Basic
    @Column(name = "photo")
    public String getPhoto() {
        return mPhoto;
    }

    public void setPhoto(String photo) {
        mPhoto = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeacherEntity that = (TeacherEntity) o;

        if (mId != that.mId) return false;
        if (mName != null ? !mName.equals(that.mName) : that.mName != null) return false;
        if (mSubject != null ? !mSubject.equals(that.mSubject) : that.mSubject != null) return false;
        if (mGraduate != null ? !mGraduate.equals(that.mGraduate) : that.mGraduate != null) return false;
        if (mTeachStart != null ? !mTeachStart.equals(that.mTeachStart) : that.mTeachStart != null) return false;
        if (mIntroduction != null ? !mIntroduction.equals(that.mIntroduction) : that.mIntroduction != null)
            return false;
        return mPhoto != null ? mPhoto.equals(that.mPhoto) : that.mPhoto == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (mId ^ (mId >>> 32));
        result = 31 * result + (mName != null ? mName.hashCode() : 0);
        result = 31 * result + (mSubject != null ? mSubject.hashCode() : 0);
        result = 31 * result + (mGraduate != null ? mGraduate.hashCode() : 0);
        result = 31 * result + (mTeachStart != null ? mTeachStart.hashCode() : 0);
        result = 31 * result + (mIntroduction != null ? mIntroduction.hashCode() : 0);
        result = 31 * result + (mPhoto != null ? mPhoto.hashCode() : 0);
        return result;
    }
}
