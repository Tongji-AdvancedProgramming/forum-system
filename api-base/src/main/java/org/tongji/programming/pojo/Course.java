package org.tongji.programming.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 课程信息表
 * @TableName course
 */
@TableName(value ="course")
@Data
public class Course implements Serializable {
    /**
     * 
     */
    @TableId
    private String courseTerm;

    /**
     * 
     */
    @TableId
    private String courseNo;

    /**
     * 
     */
    private String courseCode;

    /**
     * 
     */
    private String courseFname;

    /**
     * 
     */
    private String courseSname;

    /**
     * 
     */
    private String courseType;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Course other = (Course) that;
        return (this.getCourseTerm() == null ? other.getCourseTerm() == null : this.getCourseTerm().equals(other.getCourseTerm()))
            && (this.getCourseNo() == null ? other.getCourseNo() == null : this.getCourseNo().equals(other.getCourseNo()))
            && (this.getCourseCode() == null ? other.getCourseCode() == null : this.getCourseCode().equals(other.getCourseCode()))
            && (this.getCourseFname() == null ? other.getCourseFname() == null : this.getCourseFname().equals(other.getCourseFname()))
            && (this.getCourseSname() == null ? other.getCourseSname() == null : this.getCourseSname().equals(other.getCourseSname()))
            && (this.getCourseType() == null ? other.getCourseType() == null : this.getCourseType().equals(other.getCourseType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCourseTerm() == null) ? 0 : getCourseTerm().hashCode());
        result = prime * result + ((getCourseNo() == null) ? 0 : getCourseNo().hashCode());
        result = prime * result + ((getCourseCode() == null) ? 0 : getCourseCode().hashCode());
        result = prime * result + ((getCourseFname() == null) ? 0 : getCourseFname().hashCode());
        result = prime * result + ((getCourseSname() == null) ? 0 : getCourseSname().hashCode());
        result = prime * result + ((getCourseType() == null) ? 0 : getCourseType().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", courseTerm=").append(courseTerm);
        sb.append(", courseNo=").append(courseNo);
        sb.append(", courseCode=").append(courseCode);
        sb.append(", courseFname=").append(courseFname);
        sb.append(", courseSname=").append(courseSname);
        sb.append(", courseType=").append(courseType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}