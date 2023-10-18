package org.tongji.programming.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户登录日志表
 * @TableName log_login
 */
@TableName(value ="log_login")
@Data
public class LogLogin implements Serializable {
    /**
     * 序号(主键,自动增长)
     */
    @TableId(type = IdType.AUTO)
    private Integer logLoginId;

    /**
     * 学号
     */
    private String logLoginNo;

    /**
     * 登录IP
     */
    private String logLoginIpaddr;

    /**
     * 登录时间
     */
    private Date logLoginDate;

    /**
     * 登录环境(浏览器的agent)
     */
    private String logLoginUseragent;

    /**
     * 备注
     */
    private String logLoginComment;

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
        LogLogin other = (LogLogin) that;
        return (this.getLogLoginId() == null ? other.getLogLoginId() == null : this.getLogLoginId().equals(other.getLogLoginId()))
            && (this.getLogLoginNo() == null ? other.getLogLoginNo() == null : this.getLogLoginNo().equals(other.getLogLoginNo()))
            && (this.getLogLoginIpaddr() == null ? other.getLogLoginIpaddr() == null : this.getLogLoginIpaddr().equals(other.getLogLoginIpaddr()))
            && (this.getLogLoginDate() == null ? other.getLogLoginDate() == null : this.getLogLoginDate().equals(other.getLogLoginDate()))
            && (this.getLogLoginUseragent() == null ? other.getLogLoginUseragent() == null : this.getLogLoginUseragent().equals(other.getLogLoginUseragent()))
            && (this.getLogLoginComment() == null ? other.getLogLoginComment() == null : this.getLogLoginComment().equals(other.getLogLoginComment()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLogLoginId() == null) ? 0 : getLogLoginId().hashCode());
        result = prime * result + ((getLogLoginNo() == null) ? 0 : getLogLoginNo().hashCode());
        result = prime * result + ((getLogLoginIpaddr() == null) ? 0 : getLogLoginIpaddr().hashCode());
        result = prime * result + ((getLogLoginDate() == null) ? 0 : getLogLoginDate().hashCode());
        result = prime * result + ((getLogLoginUseragent() == null) ? 0 : getLogLoginUseragent().hashCode());
        result = prime * result + ((getLogLoginComment() == null) ? 0 : getLogLoginComment().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", logLoginId=").append(logLoginId);
        sb.append(", logLoginNo=").append(logLoginNo);
        sb.append(", logLoginIpaddr=").append(logLoginIpaddr);
        sb.append(", logLoginDate=").append(logLoginDate);
        sb.append(", logLoginUseragent=").append(logLoginUseragent);
        sb.append(", logLoginComment=").append(logLoginComment);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}