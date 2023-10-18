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
    private Integer logPostId;

    /**
     * 帖子id
     */
    private Integer logPostPostid;

    /**
     * 操作人学号
     */
    private String logPostOpno;

    /**
     * 登录IP
     */
    private String logPostIpaddr;

    /**
     * 登录时间
     */
    private Date logPostDate;

    /**
     * 备注（没考虑好怎么更合理）
     */
    private String logPostComment;

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
        return (this.getLogPostId() == null ? other.getLogPostId() == null : this.getLogPostId().equals(other.getLogPostId()))
            && (this.getLogPostPostid() == null ? other.getLogPostPostid() == null : this.getLogPostPostid().equals(other.getLogPostPostid()))
            && (this.getLogPostOpno() == null ? other.getLogPostOpno() == null : this.getLogPostOpno().equals(other.getLogPostOpno()))
            && (this.getLogPostIpaddr() == null ? other.getLogPostIpaddr() == null : this.getLogPostIpaddr().equals(other.getLogPostIpaddr()))
            && (this.getLogPostDate() == null ? other.getLogPostDate() == null : this.getLogPostDate().equals(other.getLogPostDate()))
            && (this.getLogPostComment() == null ? other.getLogPostComment() == null : this.getLogPostComment().equals(other.getLogPostComment()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLogPostId() == null) ? 0 : getLogPostId().hashCode());
        result = prime * result + ((getLogPostPostid() == null) ? 0 : getLogPostPostid().hashCode());
        result = prime * result + ((getLogPostOpno() == null) ? 0 : getLogPostOpno().hashCode());
        result = prime * result + ((getLogPostIpaddr() == null) ? 0 : getLogPostIpaddr().hashCode());
        result = prime * result + ((getLogPostDate() == null) ? 0 : getLogPostDate().hashCode());
        result = prime * result + ((getLogPostComment() == null) ? 0 : getLogPostComment().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", logPostId=").append(logPostId);
        sb.append(", logPostPostid=").append(logPostPostid);
        sb.append(", logPostOpno=").append(logPostOpno);
        sb.append(", logPostIpaddr=").append(logPostIpaddr);
        sb.append(", logPostDate=").append(logPostDate);
        sb.append(", logPostComment=").append(logPostComment);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}