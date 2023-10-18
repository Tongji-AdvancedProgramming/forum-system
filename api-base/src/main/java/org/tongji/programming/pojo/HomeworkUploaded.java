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
    private String hwupTerm;

    /**
     * 课程序号(主键,对应course中的course_code 注意:不是外键关系,但要检查是否存在)
     */
    private String hwupCcode;

    /**
     * 文件编号(例: 22232-030101-W0102)
     */
    private String hwupId;

    /**
     * 布置周
     */
    private Integer hwupWeek;

    /**
     * 章节(0-20: 第0-20章 90:大作业 98:文档作业 99:其它作业)
     */
    private Integer hwupChapter;

    /**
     * 上传的文件名
     */
    private String hwupFilename;

    /**
     * 上传的文件的MD5
     */
    private String hwupFilemd5;

    /**
     * 作业描述
     */
    private String hwupDescription;

    /**
     * 文件导入本论坛的时间
     */
    private Date hwupDateAdd;

    /**
     * 文件是否已删除('0':可正常显示/下载 '1':不显示/不提供下载 注意:enum不要当int处理)
     */
    private Object hwupIsDel;

    /**
     * 备注
     */
    private String hwupComment;

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
        return (this.getHwupTerm() == null ? other.getHwupTerm() == null : this.getHwupTerm().equals(other.getHwupTerm()))
                && (this.getHwupCcode() == null ? other.getHwupCcode() == null : this.getHwupCcode().equals(other.getHwupCcode()))
                && (this.getHwupId() == null ? other.getHwupId() == null : this.getHwupId().equals(other.getHwupId()))
                && (this.getHwupWeek() == null ? other.getHwupWeek() == null : this.getHwupWeek().equals(other.getHwupWeek()))
                && (this.getHwupChapter() == null ? other.getHwupChapter() == null : this.getHwupChapter().equals(other.getHwupChapter()))
                && (this.getHwupFilename() == null ? other.getHwupFilename() == null : this.getHwupFilename().equals(other.getHwupFilename()))
                && (this.getHwupFilemd5() == null ? other.getHwupFilemd5() == null : this.getHwupFilemd5().equals(other.getHwupFilemd5()))
                && (this.getHwupDescription() == null ? other.getHwupDescription() == null : this.getHwupDescription().equals(other.getHwupDescription()))
                && (this.getHwupDateAdd() == null ? other.getHwupDateAdd() == null : this.getHwupDateAdd().equals(other.getHwupDateAdd()))
                && (this.getHwupIsDel() == null ? other.getHwupIsDel() == null : this.getHwupIsDel().equals(other.getHwupIsDel()))
                && (this.getHwupComment() == null ? other.getHwupComment() == null : this.getHwupComment().equals(other.getHwupComment()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getHwupTerm() == null) ? 0 : getHwupTerm().hashCode());
        result = prime * result + ((getHwupCcode() == null) ? 0 : getHwupCcode().hashCode());
        result = prime * result + ((getHwupId() == null) ? 0 : getHwupId().hashCode());
        result = prime * result + ((getHwupWeek() == null) ? 0 : getHwupWeek().hashCode());
        result = prime * result + ((getHwupChapter() == null) ? 0 : getHwupChapter().hashCode());
        result = prime * result + ((getHwupFilename() == null) ? 0 : getHwupFilename().hashCode());
        result = prime * result + ((getHwupFilemd5() == null) ? 0 : getHwupFilemd5().hashCode());
        result = prime * result + ((getHwupDescription() == null) ? 0 : getHwupDescription().hashCode());
        result = prime * result + ((getHwupDateAdd() == null) ? 0 : getHwupDateAdd().hashCode());
        result = prime * result + ((getHwupIsDel() == null) ? 0 : getHwupIsDel().hashCode());
        result = prime * result + ((getHwupComment() == null) ? 0 : getHwupComment().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", hwupTerm=").append(hwupTerm);
        sb.append(", hwupCcode=").append(hwupCcode);
        sb.append(", hwupId=").append(hwupId);
        sb.append(", hwupWeek=").append(hwupWeek);
        sb.append(", hwupChapter=").append(hwupChapter);
        sb.append(", hwupFilename=").append(hwupFilename);
        sb.append(", hwupFilemd5=").append(hwupFilemd5);
        sb.append(", hwupDescription=").append(hwupDescription);
        sb.append(", hwupDateAdd=").append(hwupDateAdd);
        sb.append(", hwupIsDel=").append(hwupIsDel);
        sb.append(", hwupComment=").append(hwupComment);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
