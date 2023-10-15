package org.tongji.programming.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName student
 */
@TableName(value ="student")
@Data
public class Student implements Serializable {
    /**
     * 学期(与term表有外键关系)
     */
    private String stu_term;

    /**
     * 年级
     */
    private String stu_grade;

    /**
     * 学号(主键)
     */
    private String stu_no;

    /**
     * 姓名
     */
    private String stu_name;

    /**
     * 性别
     */
    private Object stu_sex;

    /**
     * 密码(md5)
     */
    private String stu_password;

    /**
     * 专业/班级全称
     */
    private String stu_class_fname;

    /**
     * 专业/班级简称
     */
    private String stu_class_sname;

    /**
     * 学生用户等级(0:普通用户 1:助教 5:管理员 9:超级用户)
     */
    private String stu_userlevel;

    /**
     * 账号是否启用('0':禁止登录 '1':允许登录 注意:enum不要当int处理)
     */
    private Object stu_enable;

    /**
     * 系统注册时间
     */
    private Date stu_add_date;

    /**
     * 学生选修的课程1的课号(与course表有外键关系)
     */
    private String stu_cno_1;

    /**
     * 学生选修的课程1是否退课('0':正常 '1':已退课 注意:enum不要当int处理,退课学生对应课程的作业信息不要显示出来)
     */
    private Object stu_cno_1_is_del;

    /**
     * 学生选修的课程2的课号(与course表有外键关系)
     */
    private String stu_cno_2;

    /**
     * 学生选修的课程2是否退课('0':正常 '1':已退课 注意:enum不要当int处理,退课学生对应课程的作业信息不要显示出来)
     */
    private Object stu_cno_2_is_del;

    /**
     * 学生选修的课程3的课号(与course表有外键关系)
     */
    private String stu_cno_3;

    /**
     * 学生选修的课程3是否退课('0':正常 '1':已退课 注意:enum不要当int处理,退课学生对应课程的作业信息不要显示出来)
     */
    private Object stu_cno_3_is_del;

    /**
     * 该学生是否被删除('0':正常 '1':已删除 注意:被删除则无论stu_enbale置何值均不允许登录)
     */
    private Object stu_is_del;

    /**
     * 备注信息
     */
    private String stu_comment;

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
        Student other = (Student) that;
        return (this.getStu_term() == null ? other.getStu_term() == null : this.getStu_term().equals(other.getStu_term()))
            && (this.getStu_grade() == null ? other.getStu_grade() == null : this.getStu_grade().equals(other.getStu_grade()))
            && (this.getStu_no() == null ? other.getStu_no() == null : this.getStu_no().equals(other.getStu_no()))
            && (this.getStu_name() == null ? other.getStu_name() == null : this.getStu_name().equals(other.getStu_name()))
            && (this.getStu_sex() == null ? other.getStu_sex() == null : this.getStu_sex().equals(other.getStu_sex()))
            && (this.getStu_password() == null ? other.getStu_password() == null : this.getStu_password().equals(other.getStu_password()))
            && (this.getStu_class_fname() == null ? other.getStu_class_fname() == null : this.getStu_class_fname().equals(other.getStu_class_fname()))
            && (this.getStu_class_sname() == null ? other.getStu_class_sname() == null : this.getStu_class_sname().equals(other.getStu_class_sname()))
            && (this.getStu_userlevel() == null ? other.getStu_userlevel() == null : this.getStu_userlevel().equals(other.getStu_userlevel()))
            && (this.getStu_enable() == null ? other.getStu_enable() == null : this.getStu_enable().equals(other.getStu_enable()))
            && (this.getStu_add_date() == null ? other.getStu_add_date() == null : this.getStu_add_date().equals(other.getStu_add_date()))
            && (this.getStu_cno_1() == null ? other.getStu_cno_1() == null : this.getStu_cno_1().equals(other.getStu_cno_1()))
            && (this.getStu_cno_1_is_del() == null ? other.getStu_cno_1_is_del() == null : this.getStu_cno_1_is_del().equals(other.getStu_cno_1_is_del()))
            && (this.getStu_cno_2() == null ? other.getStu_cno_2() == null : this.getStu_cno_2().equals(other.getStu_cno_2()))
            && (this.getStu_cno_2_is_del() == null ? other.getStu_cno_2_is_del() == null : this.getStu_cno_2_is_del().equals(other.getStu_cno_2_is_del()))
            && (this.getStu_cno_3() == null ? other.getStu_cno_3() == null : this.getStu_cno_3().equals(other.getStu_cno_3()))
            && (this.getStu_cno_3_is_del() == null ? other.getStu_cno_3_is_del() == null : this.getStu_cno_3_is_del().equals(other.getStu_cno_3_is_del()))
            && (this.getStu_is_del() == null ? other.getStu_is_del() == null : this.getStu_is_del().equals(other.getStu_is_del()))
            && (this.getStu_comment() == null ? other.getStu_comment() == null : this.getStu_comment().equals(other.getStu_comment()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getStu_term() == null) ? 0 : getStu_term().hashCode());
        result = prime * result + ((getStu_grade() == null) ? 0 : getStu_grade().hashCode());
        result = prime * result + ((getStu_no() == null) ? 0 : getStu_no().hashCode());
        result = prime * result + ((getStu_name() == null) ? 0 : getStu_name().hashCode());
        result = prime * result + ((getStu_sex() == null) ? 0 : getStu_sex().hashCode());
        result = prime * result + ((getStu_password() == null) ? 0 : getStu_password().hashCode());
        result = prime * result + ((getStu_class_fname() == null) ? 0 : getStu_class_fname().hashCode());
        result = prime * result + ((getStu_class_sname() == null) ? 0 : getStu_class_sname().hashCode());
        result = prime * result + ((getStu_userlevel() == null) ? 0 : getStu_userlevel().hashCode());
        result = prime * result + ((getStu_enable() == null) ? 0 : getStu_enable().hashCode());
        result = prime * result + ((getStu_add_date() == null) ? 0 : getStu_add_date().hashCode());
        result = prime * result + ((getStu_cno_1() == null) ? 0 : getStu_cno_1().hashCode());
        result = prime * result + ((getStu_cno_1_is_del() == null) ? 0 : getStu_cno_1_is_del().hashCode());
        result = prime * result + ((getStu_cno_2() == null) ? 0 : getStu_cno_2().hashCode());
        result = prime * result + ((getStu_cno_2_is_del() == null) ? 0 : getStu_cno_2_is_del().hashCode());
        result = prime * result + ((getStu_cno_3() == null) ? 0 : getStu_cno_3().hashCode());
        result = prime * result + ((getStu_cno_3_is_del() == null) ? 0 : getStu_cno_3_is_del().hashCode());
        result = prime * result + ((getStu_is_del() == null) ? 0 : getStu_is_del().hashCode());
        result = prime * result + ((getStu_comment() == null) ? 0 : getStu_comment().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", stu_term=").append(stu_term);
        sb.append(", stu_grade=").append(stu_grade);
        sb.append(", stu_no=").append(stu_no);
        sb.append(", stu_name=").append(stu_name);
        sb.append(", stu_sex=").append(stu_sex);
        sb.append(", stu_password=").append(stu_password);
        sb.append(", stu_class_fname=").append(stu_class_fname);
        sb.append(", stu_class_sname=").append(stu_class_sname);
        sb.append(", stu_userlevel=").append(stu_userlevel);
        sb.append(", stu_enable=").append(stu_enable);
        sb.append(", stu_add_date=").append(stu_add_date);
        sb.append(", stu_cno_1=").append(stu_cno_1);
        sb.append(", stu_cno_1_is_del=").append(stu_cno_1_is_del);
        sb.append(", stu_cno_2=").append(stu_cno_2);
        sb.append(", stu_cno_2_is_del=").append(stu_cno_2_is_del);
        sb.append(", stu_cno_3=").append(stu_cno_3);
        sb.append(", stu_cno_3_is_del=").append(stu_cno_3_is_del);
        sb.append(", stu_is_del=").append(stu_is_del);
        sb.append(", stu_comment=").append(stu_comment);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}