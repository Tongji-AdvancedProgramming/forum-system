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
 * @TableName homework
 */
@TableName(value ="homework")
@Data
public class Homework implements Serializable {
    /**
     * 学期(主键+外键)
     */
    private String hw_term;

    /**
     * 
     */
    private String hw_ccode;

    /**
     * 作业序号(主键)
     */
    private Integer hw_id;

    /**
     * 布置周
     */
    private Integer hw_week;

    /**
     * 章节(0-20: 第0-20章 90:大作业 98:文档作业 99:其它作业)
     */
    private Integer hw_chapter;

    /**
     * 交作业网站的提交文件名
     */
    private String hw_filename;

    /**
     * 作业描述
     */
    private String hw_description;

    /**
     * 作业提交开始时间
     */
    private Date hw_bdate;

    /**
     * 作业提交结束时间
     */
    private Date hw_edate;

    /**
     * 本作业加入论坛的时间
     */
    private Date hw_add_date;

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
        Homework other = (Homework) that;
        return (this.getHw_term() == null ? other.getHw_term() == null : this.getHw_term().equals(other.getHw_term()))
            && (this.getHw_ccode() == null ? other.getHw_ccode() == null : this.getHw_ccode().equals(other.getHw_ccode()))
            && (this.getHw_id() == null ? other.getHw_id() == null : this.getHw_id().equals(other.getHw_id()))
            && (this.getHw_week() == null ? other.getHw_week() == null : this.getHw_week().equals(other.getHw_week()))
            && (this.getHw_chapter() == null ? other.getHw_chapter() == null : this.getHw_chapter().equals(other.getHw_chapter()))
            && (this.getHw_filename() == null ? other.getHw_filename() == null : this.getHw_filename().equals(other.getHw_filename()))
            && (this.getHw_description() == null ? other.getHw_description() == null : this.getHw_description().equals(other.getHw_description()))
            && (this.getHw_bdate() == null ? other.getHw_bdate() == null : this.getHw_bdate().equals(other.getHw_bdate()))
            && (this.getHw_edate() == null ? other.getHw_edate() == null : this.getHw_edate().equals(other.getHw_edate()))
            && (this.getHw_add_date() == null ? other.getHw_add_date() == null : this.getHw_add_date().equals(other.getHw_add_date()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getHw_term() == null) ? 0 : getHw_term().hashCode());
        result = prime * result + ((getHw_ccode() == null) ? 0 : getHw_ccode().hashCode());
        result = prime * result + ((getHw_id() == null) ? 0 : getHw_id().hashCode());
        result = prime * result + ((getHw_week() == null) ? 0 : getHw_week().hashCode());
        result = prime * result + ((getHw_chapter() == null) ? 0 : getHw_chapter().hashCode());
        result = prime * result + ((getHw_filename() == null) ? 0 : getHw_filename().hashCode());
        result = prime * result + ((getHw_description() == null) ? 0 : getHw_description().hashCode());
        result = prime * result + ((getHw_bdate() == null) ? 0 : getHw_bdate().hashCode());
        result = prime * result + ((getHw_edate() == null) ? 0 : getHw_edate().hashCode());
        result = prime * result + ((getHw_add_date() == null) ? 0 : getHw_add_date().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", hw_term=").append(hw_term);
        sb.append(", hw_ccode=").append(hw_ccode);
        sb.append(", hw_id=").append(hw_id);
        sb.append(", hw_week=").append(hw_week);
        sb.append(", hw_chapter=").append(hw_chapter);
        sb.append(", hw_filename=").append(hw_filename);
        sb.append(", hw_description=").append(hw_description);
        sb.append(", hw_bdate=").append(hw_bdate);
        sb.append(", hw_edate=").append(hw_edate);
        sb.append(", hw_add_date=").append(hw_add_date);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}