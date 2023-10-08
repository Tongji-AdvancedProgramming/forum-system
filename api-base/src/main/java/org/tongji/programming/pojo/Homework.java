package org.tongji.programming.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 作业信息表
 * @TableName homework
 */
@TableName(value ="homework")
@Data
public class Homework implements Serializable {
    /**
     * 
     */
    @TableId
    private String hwTerm;

    /**
     * 
     */
    @TableId
    private String hwCcode;

    /**
     * 
     */
    @TableId
    private String hwId;

    /**
     * 
     */
    private Integer hwWeek;

    /**
     * 
     */
    private Integer hwChapter;

    /**
     * 
     */
    private String hwFilename;

    /**
     * 
     */
    private String hwDescription;

    /**
     * 
     */
    private Date hwBdate;

    /**
     * 
     */
    private Date hwEdate;

    /**
     * 
     */
    private BigDecimal hwScore;

    /**
     * 
     */
    private Date hwDateAdd;

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
        return (this.getHwTerm() == null ? other.getHwTerm() == null : this.getHwTerm().equals(other.getHwTerm()))
            && (this.getHwCcode() == null ? other.getHwCcode() == null : this.getHwCcode().equals(other.getHwCcode()))
            && (this.getHwId() == null ? other.getHwId() == null : this.getHwId().equals(other.getHwId()))
            && (this.getHwWeek() == null ? other.getHwWeek() == null : this.getHwWeek().equals(other.getHwWeek()))
            && (this.getHwChapter() == null ? other.getHwChapter() == null : this.getHwChapter().equals(other.getHwChapter()))
            && (this.getHwFilename() == null ? other.getHwFilename() == null : this.getHwFilename().equals(other.getHwFilename()))
            && (this.getHwDescription() == null ? other.getHwDescription() == null : this.getHwDescription().equals(other.getHwDescription()))
            && (this.getHwBdate() == null ? other.getHwBdate() == null : this.getHwBdate().equals(other.getHwBdate()))
            && (this.getHwEdate() == null ? other.getHwEdate() == null : this.getHwEdate().equals(other.getHwEdate()))
            && (this.getHwScore() == null ? other.getHwScore() == null : this.getHwScore().equals(other.getHwScore()))
            && (this.getHwDateAdd() == null ? other.getHwDateAdd() == null : this.getHwDateAdd().equals(other.getHwDateAdd()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getHwTerm() == null) ? 0 : getHwTerm().hashCode());
        result = prime * result + ((getHwCcode() == null) ? 0 : getHwCcode().hashCode());
        result = prime * result + ((getHwId() == null) ? 0 : getHwId().hashCode());
        result = prime * result + ((getHwWeek() == null) ? 0 : getHwWeek().hashCode());
        result = prime * result + ((getHwChapter() == null) ? 0 : getHwChapter().hashCode());
        result = prime * result + ((getHwFilename() == null) ? 0 : getHwFilename().hashCode());
        result = prime * result + ((getHwDescription() == null) ? 0 : getHwDescription().hashCode());
        result = prime * result + ((getHwBdate() == null) ? 0 : getHwBdate().hashCode());
        result = prime * result + ((getHwEdate() == null) ? 0 : getHwEdate().hashCode());
        result = prime * result + ((getHwScore() == null) ? 0 : getHwScore().hashCode());
        result = prime * result + ((getHwDateAdd() == null) ? 0 : getHwDateAdd().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", hwTerm=").append(hwTerm);
        sb.append(", hwCcode=").append(hwCcode);
        sb.append(", hwId=").append(hwId);
        sb.append(", hwWeek=").append(hwWeek);
        sb.append(", hwChapter=").append(hwChapter);
        sb.append(", hwFilename=").append(hwFilename);
        sb.append(", hwDescription=").append(hwDescription);
        sb.append(", hwBdate=").append(hwBdate);
        sb.append(", hwEdate=").append(hwEdate);
        sb.append(", hwScore=").append(hwScore);
        sb.append(", hwDateAdd=").append(hwDateAdd);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}