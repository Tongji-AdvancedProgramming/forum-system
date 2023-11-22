package org.tongji.programming.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * 论坛运行所需要的其他数据
 *
 * @author cinea
 * @TableName student_info
 */
@TableName(value = "student_info")
@Data
public class StudentInfo implements Serializable {
    /**
     * 学生学号
     */
    @TableId
    private String stuNo;

    /**
     * 签名档
     */
    private String description;

    /**
     * 昵称（如有，和实名同时显示）
     */
    private String nickname;

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
        StudentInfo other = (StudentInfo) that;
        return (this.getStuNo() == null ? other.getStuNo() == null : this.getStuNo().equals(other.getStuNo()))
                && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
                && (this.getNickname() == null ? other.getNickname() == null : this.getNickname().equals(other.getNickname()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getStuNo() == null) ? 0 : getStuNo().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getNickname() == null) ? 0 : getNickname().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", stuNo=").append(stuNo);
        sb.append(", description=").append(description);
        sb.append(", nickname=").append(nickname);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
