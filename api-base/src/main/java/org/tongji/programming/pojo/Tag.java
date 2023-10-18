package org.tongji.programming.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 标签名称索引表
 * @TableName tag
 */
@TableName(value ="tag")
@Data
public class Tag implements Serializable {
    /**
     * post表中tag的字段名
     */
    @TableId
    private String tagFieldname;

    /**
     * tag的中文解释
     */
    private String tagName;

    /**
     * 对应tar的前景色(FF0000 - RGB方式表示的颜色,每两位表示一个16进制的颜色)
     */
    private String tagFgcolor;

    /**
     * 对应tar的背景色(00FF00 - RGB方式表示的颜色,每两位表示一个16进制的颜色)
     */
    private String tagBgcolor;

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
        Tag other = (Tag) that;
        return (this.getTagFieldname() == null ? other.getTagFieldname() == null : this.getTagFieldname().equals(other.getTagFieldname()))
            && (this.getTagName() == null ? other.getTagName() == null : this.getTagName().equals(other.getTagName()))
            && (this.getTagFgcolor() == null ? other.getTagFgcolor() == null : this.getTagFgcolor().equals(other.getTagFgcolor()))
            && (this.getTagBgcolor() == null ? other.getTagBgcolor() == null : this.getTagBgcolor().equals(other.getTagBgcolor()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTagFieldname() == null) ? 0 : getTagFieldname().hashCode());
        result = prime * result + ((getTagName() == null) ? 0 : getTagName().hashCode());
        result = prime * result + ((getTagFgcolor() == null) ? 0 : getTagFgcolor().hashCode());
        result = prime * result + ((getTagBgcolor() == null) ? 0 : getTagBgcolor().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tagFieldname=").append(tagFieldname);
        sb.append(", tagName=").append(tagName);
        sb.append(", tagFgcolor=").append(tagFgcolor);
        sb.append(", tagBgcolor=").append(tagBgcolor);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}