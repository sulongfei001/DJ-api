package com.sevenXnetworks.treasure.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Description
 * @Author sulongfei
 * @Date 19-1-28 下午4:19
 * @Version 1.0
 */
@Entity
@Table(name = "grade", schema = "DJ", catalog = "")
public class GradeEntity {
    private long id;
    private String identifyId;
    private String groupId;
    private byte gender;
    private String headshot;
    private String name;
    private double grade;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "identify_id")
    public String getIdentifyId() {
        return identifyId;
    }

    public void setIdentifyId(String identifyId) {
        this.identifyId = identifyId;
    }

    @Basic
    @Column(name = "group_id")
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "gender")
    public byte getGender() {
        return gender;
    }

    public void setGender(byte gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "headshot")
    public String getHeadshot() {
        return headshot;
    }

    public void setHeadshot(String headshot) {
        this.headshot = headshot;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "grade")
    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GradeEntity entity = (GradeEntity) o;
        return id == entity.id &&
                gender == entity.gender &&
                Double.compare(entity.grade, grade) == 0 &&
                Objects.equals(groupId, entity.groupId) &&
                Objects.equals(headshot, entity.headshot) &&
                Objects.equals(name, entity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, groupId, gender, headshot, name, grade);
    }
}
