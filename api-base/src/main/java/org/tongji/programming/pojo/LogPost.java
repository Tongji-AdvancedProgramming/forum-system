package org.tongji.programming.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 发帖日志表
 * @TableName log_post
 */
@TableName(value ="log_post")
@Data
public class LogPost implements Serializable {
    /**
     * 序号(主键,自动增长)
     */
    @TableId(type = IdType.AUTO)
    private Integer log_post_id;

    /**
     * 帖子id
     */
    private Integer log_post_postid;

    /**
     * 操作人学号
     */
    private String log_post_opno;

    /**
     * 登录IP
     */
    private String log_post_ipaddr;

    /**
     * 登录时间
     */
    private Date log_post_date;

    /**
     * 备注（没考虑好怎么更合理）
     */
    private String log_post_comment;

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
        LogPost other = (LogPost) that;
        return (this.getLog_post_id() == null ? other.getLog_post_id() == null : this.getLog_post_id().equals(other.getLog_post_id()))
            && (this.getLog_post_postid() == null ? other.getLog_post_postid() == null : this.getLog_post_postid().equals(other.getLog_post_postid()))
            && (this.getLog_post_opno() == null ? other.getLog_post_opno() == null : this.getLog_post_opno().equals(other.getLog_post_opno()))
            && (this.getLog_post_ipaddr() == null ? other.getLog_post_ipaddr() == null : this.getLog_post_ipaddr().equals(other.getLog_post_ipaddr()))
            && (this.getLog_post_date() == null ? other.getLog_post_date() == null : this.getLog_post_date().equals(other.getLog_post_date()))
            && (this.getLog_post_comment() == null ? other.getLog_post_comment() == null : this.getLog_post_comment().equals(other.getLog_post_comment()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLog_post_id() == null) ? 0 : getLog_post_id().hashCode());
        result = prime * result + ((getLog_post_postid() == null) ? 0 : getLog_post_postid().hashCode());
        result = prime * result + ((getLog_post_opno() == null) ? 0 : getLog_post_opno().hashCode());
        result = prime * result + ((getLog_post_ipaddr() == null) ? 0 : getLog_post_ipaddr().hashCode());
        result = prime * result + ((getLog_post_date() == null) ? 0 : getLog_post_date().hashCode());
        result = prime * result + ((getLog_post_comment() == null) ? 0 : getLog_post_comment().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", log_post_id=").append(log_post_id);
        sb.append(", log_post_postid=").append(log_post_postid);
        sb.append(", log_post_opno=").append(log_post_opno);
        sb.append(", log_post_ipaddr=").append(log_post_ipaddr);
        sb.append(", log_post_date=").append(log_post_date);
        sb.append(", log_post_comment=").append(log_post_comment);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}