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
    private String stuTerm;

    /**
     * 年级
     */
    private String stuGrade;

    /**
     * 学号(主键)
     */
    private String stuNo;

    /**
     * 姓名
     */
    private String stuName;

    /**
     * 性别
     */
    private Object stuSex;

    /**
     * 密码(md5)
     */
    private String stuPassword;

    /**
     * 专业/班级全称
     */
    private String stuClassFname;

    /**
     * 专业/班级简称
     */
    private String stuClassSname;

    /**
     * 学生用户等级(0:普通用户 1:助教 5:管理员 9:超级用户)
     */
    private String stuUserlevel;

    /**
     * 账号是否启用('0':禁止登录 '1':允许登录 注意:enum不要当int处理)
     */
    private Object stuEnable;

    /**
     * 系统注册时间
     */
    private Date stuAddDate;

    /**
     * 学生选修的课程1的课号(与course表有外键关系)
     */
    private String stuCno1;

    /**
     * 学生选修的课程1是否退课('0':正常 '1':已退课 注意:enum不要当int处理,退课学生对应课程的作业信息不要显示出来)
     */
    private Object stuCno1IsDel;

    /**
     * 学生选修的课程2的课号(与course表有外键关系)
     */
    private String stuCno2;

    /**
     * 学生选修的课程2是否退课('0':正常 '1':已退课 注意:enum不要当int处理,退课学生对应课程的作业信息不要显示出来)
     */
    private Object stuCno2IsDel;

    /**
     * 学生选修的课程3的课号(与course表有外键关系)
     */
    private String stuCno3;

    /**
     * 学生选修的课程3是否退课('0':正常 '1':已退课 注意:enum不要当int处理,退课学生对应课程的作业信息不要显示出来)
     */
    private Object stuCno3IsDel;

    /**
     * 该学生是否被删除('0':正常 '1':已删除 注意:被删除则无论stu_enbale置何值均不允许登录)
     */
    private Object stuIsDel;

    /**
     * 备注信息
     */
    private String stuComment;

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
        return (this.getStuTerm() == null ? other.getStuTerm() == null : this.getStuTerm().equals(other.getStuTerm()))
            && (this.getStuGrade() == null ? other.getStuGrade() == null : this.getStuGrade().equals(other.getStuGrade()))
            && (this.getStuNo() == null ? other.getStuNo() == null : this.getStuNo().equals(other.getStuNo()))
            && (this.getStuName() == null ? other.getStuName() == null : this.getStuName().equals(other.getStuName()))
            && (this.getStuSex() == null ? other.getStuSex() == null : this.getStuSex().equals(other.getStuSex()))
            && (this.getStuPassword() == null ? other.getStuPassword() == null : this.getStuPassword().equals(other.getStuPassword()))
            && (this.getStuClassFname() == null ? other.getStuClassFname() == null : this.getStuClassFname().equals(other.getStuClassFname()))
            && (this.getStuClassSname() == null ? other.getStuClassSname() == null : this.getStuClassSname().equals(other.getStuClassSname()))
            && (this.getStuUserlevel() == null ? other.getStuUserlevel() == null : this.getStuUserlevel().equals(other.getStuUserlevel()))
            && (this.getStuEnable() == null ? other.getStuEnable() == null : this.getStuEnable().equals(other.getStuEnable()))
            && (this.getStuAddDate() == null ? other.getStuAddDate() == null : this.getStuAddDate().equals(other.getStuAddDate()))
            && (this.getStuCno1() == null ? other.getStuCno1() == null : this.getStuCno1().equals(other.getStuCno1()))
            && (this.getStuCno1IsDel() == null ? other.getStuCno1IsDel() == null : this.getStuCno1IsDel().equals(other.getStuCno1IsDel()))
            && (this.getStuCno2() == null ? other.getStuCno2() == null : this.getStuCno2().equals(other.getStuCno2()))
            && (this.getStuCno2IsDel() == null ? other.getStuCno2IsDel() == null : this.getStuCno2IsDel().equals(other.getStuCno2IsDel()))
            && (this.getStuCno3() == null ? other.getStuCno3() == null : this.getStuCno3().equals(other.getStuCno3()))
            && (this.getStuCno3IsDel() == null ? other.getStuCno3IsDel() == null : this.getStuCno3IsDel().equals(other.getStuCno3IsDel()))
            && (this.getStuIsDel() == null ? other.getStuIsDel() == null : this.getStuIsDel().equals(other.getStuIsDel()))
            && (this.getStuComment() == null ? other.getStuComment() == null : this.getStuComment().equals(other.getStuComment()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getStuTerm() == null) ? 0 : getStuTerm().hashCode());
        result = prime * result + ((getStuGrade() == null) ? 0 : getStuGrade().hashCode());
        result = prime * result + ((getStuNo() == null) ? 0 : getStuNo().hashCode());
        result = prime * result + ((getStuName() == null) ? 0 : getStuName().hashCode());
        result = prime * result + ((getStuSex() == null) ? 0 : getStuSex().hashCode());
        result = prime * result + ((getStuPassword() == null) ? 0 : getStuPassword().hashCode());
        result = prime * result + ((getStuClassFname() == null) ? 0 : getStuClassFname().hashCode());
        result = prime * result + ((getStuClassSname() == null) ? 0 : getStuClassSname().hashCode());
        result = prime * result + ((getStuUserlevel() == null) ? 0 : getStuUserlevel().hashCode());
        result = prime * result + ((getStuEnable() == null) ? 0 : getStuEnable().hashCode());
        result = prime * result + ((getStuAddDate() == null) ? 0 : getStuAddDate().hashCode());
        result = prime * result + ((getStuCno1() == null) ? 0 : getStuCno1().hashCode());
        result = prime * result + ((getStuCno1IsDel() == null) ? 0 : getStuCno1IsDel().hashCode());
        result = prime * result + ((getStuCno2() == null) ? 0 : getStuCno2().hashCode());
        result = prime * result + ((getStuCno2IsDel() == null) ? 0 : getStuCno2IsDel().hashCode());
        result = prime * result + ((getStuCno3() == null) ? 0 : getStuCno3().hashCode());
        result = prime * result + ((getStuCno3IsDel() == null) ? 0 : getStuCno3IsDel().hashCode());
        result = prime * result + ((getStuIsDel() == null) ? 0 : getStuIsDel().hashCode());
        result = prime * result + ((getStuComment() == null) ? 0 : getStuComment().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", stuTerm=").append(stuTerm);
        sb.append(", stuGrade=").append(stuGrade);
        sb.append(", stuNo=").append(stuNo);
        sb.append(", stuName=").append(stuName);
        sb.append(", stuSex=").append(stuSex);
        sb.append(", stuPassword=").append(stuPassword);
        sb.append(", stuClassFname=").append(stuClassFname);
        sb.append(", stuClassSname=").append(stuClassSname);
        sb.append(", stuUserlevel=").append(stuUserlevel);
        sb.append(", stuEnable=").append(stuEnable);
        sb.append(", stuAddDate=").append(stuAddDate);
        sb.append(", stuCno1=").append(stuCno1);
        sb.append(", stuCno1IsDel=").append(stuCno1IsDel);
        sb.append(", stuCno2=").append(stuCno2);
        sb.append(", stuCno2IsDel=").append(stuCno2IsDel);
        sb.append(", stuCno3=").append(stuCno3);
        sb.append(", stuCno3IsDel=").append(stuCno3IsDel);
        sb.append(", stuIsDel=").append(stuIsDel);
        sb.append(", stuComment=").append(stuComment);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}