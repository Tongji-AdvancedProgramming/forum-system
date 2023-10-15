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
     * 序号(主键,自动增长)
     */
    @TableId(type = IdType.AUTO)
    private Integer log_hwup_id;

    /**
     * 操作人学号
     */
    private String log_hwup_opno;

    /**
     * 登录IP
     */
    private String log_hwup_ipaddr;

    /**
     * 登录时间
     */
    private Date log_hwup_date;

    /**
     * 备注
     */
    private String log_hwup_comment;

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
        return (this.getLog_hwup_id() == null ? other.getLog_hwup_id() == null : this.getLog_hwup_id().equals(other.getLog_hwup_id()))
            && (this.getLog_hwup_opno() == null ? other.getLog_hwup_opno() == null : this.getLog_hwup_opno().equals(other.getLog_hwup_opno()))
            && (this.getLog_hwup_ipaddr() == null ? other.getLog_hwup_ipaddr() == null : this.getLog_hwup_ipaddr().equals(other.getLog_hwup_ipaddr()))
            && (this.getLog_hwup_date() == null ? other.getLog_hwup_date() == null : this.getLog_hwup_date().equals(other.getLog_hwup_date()))
            && (this.getLog_hwup_comment() == null ? other.getLog_hwup_comment() == null : this.getLog_hwup_comment().equals(other.getLog_hwup_comment()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLog_hwup_id() == null) ? 0 : getLog_hwup_id().hashCode());
        result = prime * result + ((getLog_hwup_opno() == null) ? 0 : getLog_hwup_opno().hashCode());
        result = prime * result + ((getLog_hwup_ipaddr() == null) ? 0 : getLog_hwup_ipaddr().hashCode());
        result = prime * result + ((getLog_hwup_date() == null) ? 0 : getLog_hwup_date().hashCode());
        result = prime * result + ((getLog_hwup_comment() == null) ? 0 : getLog_hwup_comment().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", log_hwup_id=").append(log_hwup_id);
        sb.append(", log_hwup_opno=").append(log_hwup_opno);
        sb.append(", log_hwup_ipaddr=").append(log_hwup_ipaddr);
        sb.append(", log_hwup_date=").append(log_hwup_date);
        sb.append(", log_hwup_comment=").append(log_hwup_comment);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}