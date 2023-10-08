package org.tongji.programming.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 作业上传日志表
 * @TableName log_homework_uploaded
 */
@TableName(value ="log_homework_uploaded")
@Data
public class LogHomeworkUploaded implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer logHwupId;

    /**
     * 
     */
    private String logHwupOpno;

    /**
     * 
     */
    private String logHwupIpaddr;

    /**
     * 
     */
    private Date logHwupDate;

    /**
     * 
     */
    private String logHwupComment;

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
        LogHomeworkUploaded other = (LogHomeworkUploaded) that;
        return (this.getLogHwupId() == null ? other.getLogHwupId() == null : this.getLogHwupId().equals(other.getLogHwupId()))
            && (this.getLogHwupOpno() == null ? other.getLogHwupOpno() == null : this.getLogHwupOpno().equals(other.getLogHwupOpno()))
            && (this.getLogHwupIpaddr() == null ? other.getLogHwupIpaddr() == null : this.getLogHwupIpaddr().equals(other.getLogHwupIpaddr()))
            && (this.getLogHwupDate() == null ? other.getLogHwupDate() == null : this.getLogHwupDate().equals(other.getLogHwupDate()))
            && (this.getLogHwupComment() == null ? other.getLogHwupComment() == null : this.getLogHwupComment().equals(other.getLogHwupComment()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLogHwupId() == null) ? 0 : getLogHwupId().hashCode());
        result = prime * result + ((getLogHwupOpno() == null) ? 0 : getLogHwupOpno().hashCode());
        result = prime * result + ((getLogHwupIpaddr() == null) ? 0 : getLogHwupIpaddr().hashCode());
        result = prime * result + ((getLogHwupDate() == null) ? 0 : getLogHwupDate().hashCode());
        result = prime * result + ((getLogHwupComment() == null) ? 0 : getLogHwupComment().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", logHwupId=").append(logHwupId);
        sb.append(", logHwupOpno=").append(logHwupOpno);
        sb.append(", logHwupIpaddr=").append(logHwupIpaddr);
        sb.append(", logHwupDate=").append(logHwupDate);
        sb.append(", logHwupComment=").append(logHwupComment);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}