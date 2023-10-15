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
    private Integer log_login_id;

    /**
     * 学号
     */
    private String log_login_no;

    /**
     * 登录IP
     */
    private String log_login_ipaddr;

    /**
     * 登录时间
     */
    private Date log_login_date;

    /**
     * 登录环境(浏览器的agent)
     */
    private String log_login_useragent;

    /**
     * 备注
     */
    private String log_login_comment;

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
        return (this.getLog_login_id() == null ? other.getLog_login_id() == null : this.getLog_login_id().equals(other.getLog_login_id()))
            && (this.getLog_login_no() == null ? other.getLog_login_no() == null : this.getLog_login_no().equals(other.getLog_login_no()))
            && (this.getLog_login_ipaddr() == null ? other.getLog_login_ipaddr() == null : this.getLog_login_ipaddr().equals(other.getLog_login_ipaddr()))
            && (this.getLog_login_date() == null ? other.getLog_login_date() == null : this.getLog_login_date().equals(other.getLog_login_date()))
            && (this.getLog_login_useragent() == null ? other.getLog_login_useragent() == null : this.getLog_login_useragent().equals(other.getLog_login_useragent()))
            && (this.getLog_login_comment() == null ? other.getLog_login_comment() == null : this.getLog_login_comment().equals(other.getLog_login_comment()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLog_login_id() == null) ? 0 : getLog_login_id().hashCode());
        result = prime * result + ((getLog_login_no() == null) ? 0 : getLog_login_no().hashCode());
        result = prime * result + ((getLog_login_ipaddr() == null) ? 0 : getLog_login_ipaddr().hashCode());
        result = prime * result + ((getLog_login_date() == null) ? 0 : getLog_login_date().hashCode());
        result = prime * result + ((getLog_login_useragent() == null) ? 0 : getLog_login_useragent().hashCode());
        result = prime * result + ((getLog_login_comment() == null) ? 0 : getLog_login_comment().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", log_login_id=").append(log_login_id);
        sb.append(", log_login_no=").append(log_login_no);
        sb.append(", log_login_ipaddr=").append(log_login_ipaddr);
        sb.append(", log_login_date=").append(log_login_date);
        sb.append(", log_login_useragent=").append(log_login_useragent);
        sb.append(", log_login_comment=").append(log_login_comment);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}