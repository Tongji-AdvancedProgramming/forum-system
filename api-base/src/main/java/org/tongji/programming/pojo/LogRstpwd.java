package org.tongji.programming.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 重置密码日志表
 * @TableName log_rstpwd
 */
@TableName(value ="log_rstpwd")
@Data
public class LogRstpwd implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer logRstpwdId;

    /**
     * 
     */
    private String logRstpwdNo;

    /**
     * 
     */
    private String logRstpwdOpno;

    /**
     * 
     */
    private String logRstpwdIpaddr;

    /**
     * 
     */
    private Date logRstpwdDate;

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
        LogRstpwd other = (LogRstpwd) that;
        return (this.getLogRstpwdId() == null ? other.getLogRstpwdId() == null : this.getLogRstpwdId().equals(other.getLogRstpwdId()))
            && (this.getLogRstpwdNo() == null ? other.getLogRstpwdNo() == null : this.getLogRstpwdNo().equals(other.getLogRstpwdNo()))
            && (this.getLogRstpwdOpno() == null ? other.getLogRstpwdOpno() == null : this.getLogRstpwdOpno().equals(other.getLogRstpwdOpno()))
            && (this.getLogRstpwdIpaddr() == null ? other.getLogRstpwdIpaddr() == null : this.getLogRstpwdIpaddr().equals(other.getLogRstpwdIpaddr()))
            && (this.getLogRstpwdDate() == null ? other.getLogRstpwdDate() == null : this.getLogRstpwdDate().equals(other.getLogRstpwdDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLogRstpwdId() == null) ? 0 : getLogRstpwdId().hashCode());
        result = prime * result + ((getLogRstpwdNo() == null) ? 0 : getLogRstpwdNo().hashCode());
        result = prime * result + ((getLogRstpwdOpno() == null) ? 0 : getLogRstpwdOpno().hashCode());
        result = prime * result + ((getLogRstpwdIpaddr() == null) ? 0 : getLogRstpwdIpaddr().hashCode());
        result = prime * result + ((getLogRstpwdDate() == null) ? 0 : getLogRstpwdDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", logRstpwdId=").append(logRstpwdId);
        sb.append(", logRstpwdNo=").append(logRstpwdNo);
        sb.append(", logRstpwdOpno=").append(logRstpwdOpno);
        sb.append(", logRstpwdIpaddr=").append(logRstpwdIpaddr);
        sb.append(", logRstpwdDate=").append(logRstpwdDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}