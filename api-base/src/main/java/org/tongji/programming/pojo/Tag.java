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
    private String tag_fieldname;

    /**
     * tag的中文解释
     */
    private String tag_name;

    /**
     * 对应tar的前景色(FF0000 - RGB方式表示的颜色,每两位表示一个16进制的颜色)
     */
    private String tag_fgcolor;

    /**
     * 对应tar的背景色(00FF00 - RGB方式表示的颜色,每两位表示一个16进制的颜色)
     */
    private String tag_bgcolor;

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
        return (this.getTag_fieldname() == null ? other.getTag_fieldname() == null : this.getTag_fieldname().equals(other.getTag_fieldname()))
            && (this.getTag_name() == null ? other.getTag_name() == null : this.getTag_name().equals(other.getTag_name()))
            && (this.getTag_fgcolor() == null ? other.getTag_fgcolor() == null : this.getTag_fgcolor().equals(other.getTag_fgcolor()))
            && (this.getTag_bgcolor() == null ? other.getTag_bgcolor() == null : this.getTag_bgcolor().equals(other.getTag_bgcolor()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTag_fieldname() == null) ? 0 : getTag_fieldname().hashCode());
        result = prime * result + ((getTag_name() == null) ? 0 : getTag_name().hashCode());
        result = prime * result + ((getTag_fgcolor() == null) ? 0 : getTag_fgcolor().hashCode());
        result = prime * result + ((getTag_bgcolor() == null) ? 0 : getTag_bgcolor().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tag_fieldname=").append(tag_fieldname);
        sb.append(", tag_name=").append(tag_name);
        sb.append(", tag_fgcolor=").append(tag_fgcolor);
        sb.append(", tag_bgcolor=").append(tag_bgcolor);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}