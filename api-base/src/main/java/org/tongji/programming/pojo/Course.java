package org.tongji.programming.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * 课程信息表
 *
 * @TableName course
 */
@TableName(value = "course")
@Data
public class Course implements Serializable {
    /**
     * 学期(主键+外键)
     */
    private String course_term;

    /**
     * 课程序号(主键,目前同济的规则是代码+两位序号)
     */
    private String course_no;

    /**
     * 课程代码
     */
    private String course_code;

    /**
     * 教务系统中的全名
     */
    private String course_fname;

    /**
     * 课程简称
     */
    private String course_sname;

    /**
     * 课程类别(1-基础 2-专业，暂时无用，未来和学校的课程编码匹配)
     */
    private String course_type;

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
        return (this.getCourse_term() == null ? other.getCourse_term() == null : this.getCourse_term().equals(other.getCourse_term()))
                && (this.getCourse_no() == null ? other.getCourse_no() == null : this.getCourse_no().equals(other.getCourse_no()))
                && (this.getCourse_code() == null ? other.getCourse_code() == null : this.getCourse_code().equals(other.getCourse_code()))
                && (this.getCourse_fname() == null ? other.getCourse_fname() == null : this.getCourse_fname().equals(other.getCourse_fname()))
                && (this.getCourse_sname() == null ? other.getCourse_sname() == null : this.getCourse_sname().equals(other.getCourse_sname()))
                && (this.getCourse_type() == null ? other.getCourse_type() == null : this.getCourse_type().equals(other.getCourse_type()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCourse_term() == null) ? 0 : getCourse_term().hashCode());
        result = prime * result + ((getCourse_no() == null) ? 0 : getCourse_no().hashCode());
        result = prime * result + ((getCourse_code() == null) ? 0 : getCourse_code().hashCode());
        result = prime * result + ((getCourse_fname() == null) ? 0 : getCourse_fname().hashCode());
        result = prime * result + ((getCourse_sname() == null) ? 0 : getCourse_sname().hashCode());
        result = prime * result + ((getCourse_type() == null) ? 0 : getCourse_type().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", course_term=").append(course_term);
        sb.append(", course_no=").append(course_no);
        sb.append(", course_code=").append(course_code);
        sb.append(", course_fname=").append(course_fname);
        sb.append(", course_sname=").append(course_sname);
        sb.append(", course_type=").append(course_type);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
