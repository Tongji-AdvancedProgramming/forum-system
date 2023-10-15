package org.tongji.programming.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 作业上传信息表
 *
 * @TableName homework_uploaded
 */
@TableName(value = "homework_uploaded")
@Data
public class HomeworkUploaded implements Serializable {
    /**
     * 学期(主键+外键)
     */
    private String hwup_term;

    /**
     * 课程序号(主键,对应course中的course_code 注意:不是外键关系,但要检查是否存在)
     */
    private String hwup_ccode;

    /**
     * 文件编号(例: 22232-030101-W0102)
     */
    private String hwup_id;

    /**
     * 布置周
     */
    private Integer hwup_week;

    /**
     * 章节(0-20: 第0-20章 90:大作业 98:文档作业 99:其它作业)
     */
    private Integer hwup_chapter;

    /**
     * 上传的文件名
     */
    private String hwup_filename;

    /**
     * 上传的文件的MD5
     */
    private String hwup_filemd5;

    /**
     * 作业描述
     */
    private String hwup_description;

    /**
     * 文件导入本论坛的时间
     */
    private Date hwup_date_add;

    /**
     * 文件是否已删除('0':可正常显示/下载 '1':不显示/不提供下载 注意:enum不要当int处理)
     */
    private Object hwup_is_del;

    /**
     * 备注
     */
    private String hwup_comment;

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
        HomeworkUploaded other = (HomeworkUploaded) that;
        return (this.getHwup_term() == null ? other.getHwup_term() == null : this.getHwup_term().equals(other.getHwup_term()))
                && (this.getHwup_ccode() == null ? other.getHwup_ccode() == null : this.getHwup_ccode().equals(other.getHwup_ccode()))
                && (this.getHwup_id() == null ? other.getHwup_id() == null : this.getHwup_id().equals(other.getHwup_id()))
                && (this.getHwup_week() == null ? other.getHwup_week() == null : this.getHwup_week().equals(other.getHwup_week()))
                && (this.getHwup_chapter() == null ? other.getHwup_chapter() == null : this.getHwup_chapter().equals(other.getHwup_chapter()))
                && (this.getHwup_filename() == null ? other.getHwup_filename() == null : this.getHwup_filename().equals(other.getHwup_filename()))
                && (this.getHwup_filemd5() == null ? other.getHwup_filemd5() == null : this.getHwup_filemd5().equals(other.getHwup_filemd5()))
                && (this.getHwup_description() == null ? other.getHwup_description() == null : this.getHwup_description().equals(other.getHwup_description()))
                && (this.getHwup_date_add() == null ? other.getHwup_date_add() == null : this.getHwup_date_add().equals(other.getHwup_date_add()))
                && (this.getHwup_is_del() == null ? other.getHwup_is_del() == null : this.getHwup_is_del().equals(other.getHwup_is_del()))
                && (this.getHwup_comment() == null ? other.getHwup_comment() == null : this.getHwup_comment().equals(other.getHwup_comment()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getHwup_term() == null) ? 0 : getHwup_term().hashCode());
        result = prime * result + ((getHwup_ccode() == null) ? 0 : getHwup_ccode().hashCode());
        result = prime * result + ((getHwup_id() == null) ? 0 : getHwup_id().hashCode());
        result = prime * result + ((getHwup_week() == null) ? 0 : getHwup_week().hashCode());
        result = prime * result + ((getHwup_chapter() == null) ? 0 : getHwup_chapter().hashCode());
        result = prime * result + ((getHwup_filename() == null) ? 0 : getHwup_filename().hashCode());
        result = prime * result + ((getHwup_filemd5() == null) ? 0 : getHwup_filemd5().hashCode());
        result = prime * result + ((getHwup_description() == null) ? 0 : getHwup_description().hashCode());
        result = prime * result + ((getHwup_date_add() == null) ? 0 : getHwup_date_add().hashCode());
        result = prime * result + ((getHwup_is_del() == null) ? 0 : getHwup_is_del().hashCode());
        result = prime * result + ((getHwup_comment() == null) ? 0 : getHwup_comment().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", hwup_term=").append(hwup_term);
        sb.append(", hwup_ccode=").append(hwup_ccode);
        sb.append(", hwup_id=").append(hwup_id);
        sb.append(", hwup_week=").append(hwup_week);
        sb.append(", hwup_chapter=").append(hwup_chapter);
        sb.append(", hwup_filename=").append(hwup_filename);
        sb.append(", hwup_filemd5=").append(hwup_filemd5);
        sb.append(", hwup_description=").append(hwup_description);
        sb.append(", hwup_date_add=").append(hwup_date_add);
        sb.append(", hwup_is_del=").append(hwup_is_del);
        sb.append(", hwup_comment=").append(hwup_comment);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
