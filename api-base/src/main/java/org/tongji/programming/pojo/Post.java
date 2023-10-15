package org.tongji.programming.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 发帖信息表
 * @TableName post
 */
@TableName(value ="post")
@Data
public class Post implements Serializable {
    /**
     * ID(主键,自动增长)
     */
    @TableId(type = IdType.AUTO)
    private Integer post_id;

    /**
     * 学期(外键)
     */
    private String post_term;

    /**
     * 课程序号(对应course中的course_code 注意:不是外键关系,但要检查是否存在)
     */
    private String post_ccode;

    /**
     * 对应的上传文件/具体作业的序号

                            如果是在第x周的整体问题处发帖,则本字段值为上传文件序号('22232-000001-W0101' - 必须在homework_uploaded中存在)

                            如果是在第x周的某具体作业处发帖,则本字段值为具体作业序号('0401' - 必须在homework中存在)

                            如果是在课程的整体问题处发帖(仅管理员及超级用户允许),则本字段值为"学期-G5位序号-W4位周次"('22232-G00001-W0101')
     */
    private String post_hwup_or_hw_id;

    /**
     * 布置周(课程的整体问题则周次为-1)
     */
    private Integer post_week;

    /**
     * 章节(课程的整体问题则章节为-1)
     */
    private Integer post_chapter;

    /**
     * 对应帖子的id(与post_id是外键关系)

                            如果是发帖,则为NULL

                            如果是回帖,则为对应帖子的post_id(以此为依据构建发帖回帖的树形结构)
     */
    private Integer post_answer_id;

    /**
     * 帖子类型('Question':首发问题 'QuestionsAdditional':追问 'Answer':回帖 'Other':其它 '/':预留)

                            以 post_term + post_ccode + post_hwup_or_hw_id 为基准汇聚,具体排序规则?

                            本字段是否多余?
     */
    private Object post_type;

    /**
     * 发帖人学号
     */
    private String post_sno;

    /**
     * 优先级(从'0'~'9' 依次递增,帖子显示是按优先级顺序,相同优先级按发帖时间,可由管理员手工置位进行调整)
     */
    private String post_priority;

    /**
     * 约定的tag 1标记(0:此标记未置位 1:此标记已置位)
     */
    private Object post_tag_01;

    /**
     * 约定的tag 2标记(0:此标记未置位 1:此标记已置位)
     */
    private Object post_tag_02;

    /**
     * 约定的tag 3标记(0:此标记未置位 1:此标记已置位)
     */
    private Object post_tag_03;

    /**
     * 约定的tag 4标记(0:此标记未置位 1:此标记已置位)
     */
    private Object post_tag_04;

    /**
     * 约定的tag 5标记(0:此标记未置位 1:此标记已置位)
     */
    private Object post_tag_05;

    /**
     * 约定的tag 6标记(0:此标记未置位 1:此标记已置位)
     */
    private Object post_tag_06;

    /**
     * 约定的tag 7标记(0:此标记未置位 1:此标记已置位)
     */
    private Object post_tag_07;

    /**
     * 约定的tag 8标记(0:此标记未置位 1:此标记已置位)
     */
    private Object post_tag_08;

    /**
     * 约定的tag 9标记(0:此标记未置位 1:此标记已置位)
     */
    private Object post_tag_09;

    /**
     * 约定的tag 10标记(0:此标记未置位 1:此标记已置位)
     */
    private Object post_tag_10;

    /**
     * 发帖具体内容(允许贴图,Richtext?)
     */
    private String post_content;

    /**
     * 发帖时间
     */
    private Date post_date;

    /**
     * 帖子是否已删除('0':正常显示 '1':不显示,包括所有的回帖 注意:enum不要当int处理)
     */
    private Object post_is_del;

    /**
     * 备注(预留)
     */
    private String post_comment;

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
        Post other = (Post) that;
        return (this.getPost_id() == null ? other.getPost_id() == null : this.getPost_id().equals(other.getPost_id()))
            && (this.getPost_term() == null ? other.getPost_term() == null : this.getPost_term().equals(other.getPost_term()))
            && (this.getPost_ccode() == null ? other.getPost_ccode() == null : this.getPost_ccode().equals(other.getPost_ccode()))
            && (this.getPost_hwup_or_hw_id() == null ? other.getPost_hwup_or_hw_id() == null : this.getPost_hwup_or_hw_id().equals(other.getPost_hwup_or_hw_id()))
            && (this.getPost_week() == null ? other.getPost_week() == null : this.getPost_week().equals(other.getPost_week()))
            && (this.getPost_chapter() == null ? other.getPost_chapter() == null : this.getPost_chapter().equals(other.getPost_chapter()))
            && (this.getPost_answer_id() == null ? other.getPost_answer_id() == null : this.getPost_answer_id().equals(other.getPost_answer_id()))
            && (this.getPost_type() == null ? other.getPost_type() == null : this.getPost_type().equals(other.getPost_type()))
            && (this.getPost_sno() == null ? other.getPost_sno() == null : this.getPost_sno().equals(other.getPost_sno()))
            && (this.getPost_priority() == null ? other.getPost_priority() == null : this.getPost_priority().equals(other.getPost_priority()))
            && (this.getPost_tag_01() == null ? other.getPost_tag_01() == null : this.getPost_tag_01().equals(other.getPost_tag_01()))
            && (this.getPost_tag_02() == null ? other.getPost_tag_02() == null : this.getPost_tag_02().equals(other.getPost_tag_02()))
            && (this.getPost_tag_03() == null ? other.getPost_tag_03() == null : this.getPost_tag_03().equals(other.getPost_tag_03()))
            && (this.getPost_tag_04() == null ? other.getPost_tag_04() == null : this.getPost_tag_04().equals(other.getPost_tag_04()))
            && (this.getPost_tag_05() == null ? other.getPost_tag_05() == null : this.getPost_tag_05().equals(other.getPost_tag_05()))
            && (this.getPost_tag_06() == null ? other.getPost_tag_06() == null : this.getPost_tag_06().equals(other.getPost_tag_06()))
            && (this.getPost_tag_07() == null ? other.getPost_tag_07() == null : this.getPost_tag_07().equals(other.getPost_tag_07()))
            && (this.getPost_tag_08() == null ? other.getPost_tag_08() == null : this.getPost_tag_08().equals(other.getPost_tag_08()))
            && (this.getPost_tag_09() == null ? other.getPost_tag_09() == null : this.getPost_tag_09().equals(other.getPost_tag_09()))
            && (this.getPost_tag_10() == null ? other.getPost_tag_10() == null : this.getPost_tag_10().equals(other.getPost_tag_10()))
            && (this.getPost_content() == null ? other.getPost_content() == null : this.getPost_content().equals(other.getPost_content()))
            && (this.getPost_date() == null ? other.getPost_date() == null : this.getPost_date().equals(other.getPost_date()))
            && (this.getPost_is_del() == null ? other.getPost_is_del() == null : this.getPost_is_del().equals(other.getPost_is_del()))
            && (this.getPost_comment() == null ? other.getPost_comment() == null : this.getPost_comment().equals(other.getPost_comment()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPost_id() == null) ? 0 : getPost_id().hashCode());
        result = prime * result + ((getPost_term() == null) ? 0 : getPost_term().hashCode());
        result = prime * result + ((getPost_ccode() == null) ? 0 : getPost_ccode().hashCode());
        result = prime * result + ((getPost_hwup_or_hw_id() == null) ? 0 : getPost_hwup_or_hw_id().hashCode());
        result = prime * result + ((getPost_week() == null) ? 0 : getPost_week().hashCode());
        result = prime * result + ((getPost_chapter() == null) ? 0 : getPost_chapter().hashCode());
        result = prime * result + ((getPost_answer_id() == null) ? 0 : getPost_answer_id().hashCode());
        result = prime * result + ((getPost_type() == null) ? 0 : getPost_type().hashCode());
        result = prime * result + ((getPost_sno() == null) ? 0 : getPost_sno().hashCode());
        result = prime * result + ((getPost_priority() == null) ? 0 : getPost_priority().hashCode());
        result = prime * result + ((getPost_tag_01() == null) ? 0 : getPost_tag_01().hashCode());
        result = prime * result + ((getPost_tag_02() == null) ? 0 : getPost_tag_02().hashCode());
        result = prime * result + ((getPost_tag_03() == null) ? 0 : getPost_tag_03().hashCode());
        result = prime * result + ((getPost_tag_04() == null) ? 0 : getPost_tag_04().hashCode());
        result = prime * result + ((getPost_tag_05() == null) ? 0 : getPost_tag_05().hashCode());
        result = prime * result + ((getPost_tag_06() == null) ? 0 : getPost_tag_06().hashCode());
        result = prime * result + ((getPost_tag_07() == null) ? 0 : getPost_tag_07().hashCode());
        result = prime * result + ((getPost_tag_08() == null) ? 0 : getPost_tag_08().hashCode());
        result = prime * result + ((getPost_tag_09() == null) ? 0 : getPost_tag_09().hashCode());
        result = prime * result + ((getPost_tag_10() == null) ? 0 : getPost_tag_10().hashCode());
        result = prime * result + ((getPost_content() == null) ? 0 : getPost_content().hashCode());
        result = prime * result + ((getPost_date() == null) ? 0 : getPost_date().hashCode());
        result = prime * result + ((getPost_is_del() == null) ? 0 : getPost_is_del().hashCode());
        result = prime * result + ((getPost_comment() == null) ? 0 : getPost_comment().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", post_id=").append(post_id);
        sb.append(", post_term=").append(post_term);
        sb.append(", post_ccode=").append(post_ccode);
        sb.append(", post_hwup_or_hw_id=").append(post_hwup_or_hw_id);
        sb.append(", post_week=").append(post_week);
        sb.append(", post_chapter=").append(post_chapter);
        sb.append(", post_answer_id=").append(post_answer_id);
        sb.append(", post_type=").append(post_type);
        sb.append(", post_sno=").append(post_sno);
        sb.append(", post_priority=").append(post_priority);
        sb.append(", post_tag_01=").append(post_tag_01);
        sb.append(", post_tag_02=").append(post_tag_02);
        sb.append(", post_tag_03=").append(post_tag_03);
        sb.append(", post_tag_04=").append(post_tag_04);
        sb.append(", post_tag_05=").append(post_tag_05);
        sb.append(", post_tag_06=").append(post_tag_06);
        sb.append(", post_tag_07=").append(post_tag_07);
        sb.append(", post_tag_08=").append(post_tag_08);
        sb.append(", post_tag_09=").append(post_tag_09);
        sb.append(", post_tag_10=").append(post_tag_10);
        sb.append(", post_content=").append(post_content);
        sb.append(", post_date=").append(post_date);
        sb.append(", post_is_del=").append(post_is_del);
        sb.append(", post_comment=").append(post_comment);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}