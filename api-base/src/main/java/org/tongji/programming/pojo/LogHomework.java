package org.tongji.programming.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 作业导入日志表
 * @TableName log_homework
 */
@TableName(value ="log_homework")
@Data
public class LogHomework implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer logHwId;

    /**
     * 
     */
    private String logHwOpno;

    /**
     * 
     */
    private String logHwIpaddr;

    /**
     * 
     */
    private Date logHwDate;

    /**
     * 
     */
    private String logHwComment;

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
        LogHomework other = (LogHomework) that;
        return (this.getLogHwId() == null ? other.getLogHwId() == null : this.getLogHwId().equals(other.getLogHwId()))
            && (this.getLogHwOpno() == null ? other.getLogHwOpno() == null : this.getLogHwOpno().equals(other.getLogHwOpno()))
            && (this.getLogHwIpaddr() == null ? other.getLogHwIpaddr() == null : this.getLogHwIpaddr().equals(other.getLogHwIpaddr()))
            && (this.getLogHwDate() == null ? other.getLogHwDate() == null : this.getLogHwDate().equals(other.getLogHwDate()))
            && (this.getLogHwComment() == null ? other.getLogHwComment() == null : this.getLogHwComment().equals(other.getLogHwComment()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLogHwId() == null) ? 0 : getLogHwId().hashCode());
        result = prime * result + ((getLogHwOpno() == null) ? 0 : getLogHwOpno().hashCode());
        result = prime * result + ((getLogHwIpaddr() == null) ? 0 : getLogHwIpaddr().hashCode());
        result = prime * result + ((getLogHwDate() == null) ? 0 : getLogHwDate().hashCode());
        result = prime * result + ((getLogHwComment() == null) ? 0 : getLogHwComment().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", logHwId=").append(logHwId);
        sb.append(", logHwOpno=").append(logHwOpno);
        sb.append(", logHwIpaddr=").append(logHwIpaddr);
        sb.append(", logHwDate=").append(logHwDate);
        sb.append(", logHwComment=").append(logHwComment);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}