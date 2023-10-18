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
    private Integer postId;

    /**
     * 学期(外键)
     */
    private String postTerm;

    /**
     * 课程序号(对应course中的course_code 注意:不是外键关系,但要检查是否存在)
     */
    private String postCcode;

    /**
     * 对应的上传文件/具体作业的序号

                            如果是在第x周的整体问题处发帖,则本字段值为上传文件序号('22232-000001-W0101' - 必须在homework_uploaded中存在)

                            如果是在第x周的某具体作业处发帖,则本字段值为具体作业序号('0401' - 必须在homework中存在)

                            如果是在课程的整体问题处发帖(仅管理员及超级用户允许),则本字段值为"学期-G5位序号-W4位周次"('22232-G00001-W0101')
     */
    private String postHwupOrHwId;

    /**
     * 布置周(课程的整体问题则周次为-1)
     */
    private Integer postWeek;

    /**
     * 章节(课程的整体问题则章节为-1)
     */
    private Integer postChapter;

    /**
     * 对应帖子的id(与post_id是外键关系)

                            如果是发帖,则为NULL

                            如果是回帖,则为对应帖子的post_id(以此为依据构建发帖回帖的树形结构)
     */
    private Integer postAnswerId;

    /**
     * 帖子类型('Question':首发问题 'QuestionsAdditional':追问 'Answer':回帖 'Other':其它 '/':预留)

                            以 post_term + post_ccode + post_hwup_or_hw_id 为基准汇聚,具体排序规则?

                            本字段是否多余?
     */
    private Object postType;

    /**
     * 发帖人学号
     */
    private String postSno;

    /**
     * 优先级(从'0'~'9' 依次递增,帖子显示是按优先级顺序,相同优先级按发帖时间,可由管理员手工置位进行调整)
     */
    private String postPriority;

    /**
     * 约定的tag 1标记(0:此标记未置位 1:此标记已置位)
     */
    private Object postTag01;

    /**
     * 约定的tag 2标记(0:此标记未置位 1:此标记已置位)
     */
    private Object postTag02;

    /**
     * 约定的tag 3标记(0:此标记未置位 1:此标记已置位)
     */
    private Object postTag03;

    /**
     * 约定的tag 4标记(0:此标记未置位 1:此标记已置位)
     */
    private Object postTag04;

    /**
     * 约定的tag 5标记(0:此标记未置位 1:此标记已置位)
     */
    private Object postTag05;

    /**
     * 约定的tag 6标记(0:此标记未置位 1:此标记已置位)
     */
    private Object postTag06;

    /**
     * 约定的tag 7标记(0:此标记未置位 1:此标记已置位)
     */
    private Object postTag07;

    /**
     * 约定的tag 8标记(0:此标记未置位 1:此标记已置位)
     */
    private Object postTag08;

    /**
     * 约定的tag 9标记(0:此标记未置位 1:此标记已置位)
     */
    private Object postTag09;

    /**
     * 约定的tag 10标记(0:此标记未置位 1:此标记已置位)
     */
    private Object postTag10;

    /**
     * 发帖具体内容(允许贴图,Richtext?)
     */
    private String postContent;

    /**
     * 发帖时间
     */
    private Date postDate;

    /**
     * 帖子是否已删除('0':正常显示 '1':不显示,包括所有的回帖 注意:enum不要当int处理)
     */
    private Object postIsDel;

    /**
     * 备注(预留)
     */
    private String postComment;

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
        return (this.getPostId() == null ? other.getPostId() == null : this.getPostId().equals(other.getPostId()))
            && (this.getPostTerm() == null ? other.getPostTerm() == null : this.getPostTerm().equals(other.getPostTerm()))
            && (this.getPostCcode() == null ? other.getPostCcode() == null : this.getPostCcode().equals(other.getPostCcode()))
            && (this.getPostHwupOrHwId() == null ? other.getPostHwupOrHwId() == null : this.getPostHwupOrHwId().equals(other.getPostHwupOrHwId()))
            && (this.getPostWeek() == null ? other.getPostWeek() == null : this.getPostWeek().equals(other.getPostWeek()))
            && (this.getPostChapter() == null ? other.getPostChapter() == null : this.getPostChapter().equals(other.getPostChapter()))
            && (this.getPostAnswerId() == null ? other.getPostAnswerId() == null : this.getPostAnswerId().equals(other.getPostAnswerId()))
            && (this.getPostType() == null ? other.getPostType() == null : this.getPostType().equals(other.getPostType()))
            && (this.getPostSno() == null ? other.getPostSno() == null : this.getPostSno().equals(other.getPostSno()))
            && (this.getPostPriority() == null ? other.getPostPriority() == null : this.getPostPriority().equals(other.getPostPriority()))
            && (this.getPostTag01() == null ? other.getPostTag01() == null : this.getPostTag01().equals(other.getPostTag01()))
            && (this.getPostTag02() == null ? other.getPostTag02() == null : this.getPostTag02().equals(other.getPostTag02()))
            && (this.getPostTag03() == null ? other.getPostTag03() == null : this.getPostTag03().equals(other.getPostTag03()))
            && (this.getPostTag04() == null ? other.getPostTag04() == null : this.getPostTag04().equals(other.getPostTag04()))
            && (this.getPostTag05() == null ? other.getPostTag05() == null : this.getPostTag05().equals(other.getPostTag05()))
            && (this.getPostTag06() == null ? other.getPostTag06() == null : this.getPostTag06().equals(other.getPostTag06()))
            && (this.getPostTag07() == null ? other.getPostTag07() == null : this.getPostTag07().equals(other.getPostTag07()))
            && (this.getPostTag08() == null ? other.getPostTag08() == null : this.getPostTag08().equals(other.getPostTag08()))
            && (this.getPostTag09() == null ? other.getPostTag09() == null : this.getPostTag09().equals(other.getPostTag09()))
            && (this.getPostTag10() == null ? other.getPostTag10() == null : this.getPostTag10().equals(other.getPostTag10()))
            && (this.getPostContent() == null ? other.getPostContent() == null : this.getPostContent().equals(other.getPostContent()))
            && (this.getPostDate() == null ? other.getPostDate() == null : this.getPostDate().equals(other.getPostDate()))
            && (this.getPostIsDel() == null ? other.getPostIsDel() == null : this.getPostIsDel().equals(other.getPostIsDel()))
            && (this.getPostComment() == null ? other.getPostComment() == null : this.getPostComment().equals(other.getPostComment()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPostId() == null) ? 0 : getPostId().hashCode());
        result = prime * result + ((getPostTerm() == null) ? 0 : getPostTerm().hashCode());
        result = prime * result + ((getPostCcode() == null) ? 0 : getPostCcode().hashCode());
        result = prime * result + ((getPostHwupOrHwId() == null) ? 0 : getPostHwupOrHwId().hashCode());
        result = prime * result + ((getPostWeek() == null) ? 0 : getPostWeek().hashCode());
        result = prime * result + ((getPostChapter() == null) ? 0 : getPostChapter().hashCode());
        result = prime * result + ((getPostAnswerId() == null) ? 0 : getPostAnswerId().hashCode());
        result = prime * result + ((getPostType() == null) ? 0 : getPostType().hashCode());
        result = prime * result + ((getPostSno() == null) ? 0 : getPostSno().hashCode());
        result = prime * result + ((getPostPriority() == null) ? 0 : getPostPriority().hashCode());
        result = prime * result + ((getPostTag01() == null) ? 0 : getPostTag01().hashCode());
        result = prime * result + ((getPostTag02() == null) ? 0 : getPostTag02().hashCode());
        result = prime * result + ((getPostTag03() == null) ? 0 : getPostTag03().hashCode());
        result = prime * result + ((getPostTag04() == null) ? 0 : getPostTag04().hashCode());
        result = prime * result + ((getPostTag05() == null) ? 0 : getPostTag05().hashCode());
        result = prime * result + ((getPostTag06() == null) ? 0 : getPostTag06().hashCode());
        result = prime * result + ((getPostTag07() == null) ? 0 : getPostTag07().hashCode());
        result = prime * result + ((getPostTag08() == null) ? 0 : getPostTag08().hashCode());
        result = prime * result + ((getPostTag09() == null) ? 0 : getPostTag09().hashCode());
        result = prime * result + ((getPostTag10() == null) ? 0 : getPostTag10().hashCode());
        result = prime * result + ((getPostContent() == null) ? 0 : getPostContent().hashCode());
        result = prime * result + ((getPostDate() == null) ? 0 : getPostDate().hashCode());
        result = prime * result + ((getPostIsDel() == null) ? 0 : getPostIsDel().hashCode());
        result = prime * result + ((getPostComment() == null) ? 0 : getPostComment().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", postId=").append(postId);
        sb.append(", postTerm=").append(postTerm);
        sb.append(", postCcode=").append(postCcode);
        sb.append(", postHwupOrHwId=").append(postHwupOrHwId);
        sb.append(", postWeek=").append(postWeek);
        sb.append(", postChapter=").append(postChapter);
        sb.append(", postAnswerId=").append(postAnswerId);
        sb.append(", postType=").append(postType);
        sb.append(", postSno=").append(postSno);
        sb.append(", postPriority=").append(postPriority);
        sb.append(", postTag01=").append(postTag01);
        sb.append(", postTag02=").append(postTag02);
        sb.append(", postTag03=").append(postTag03);
        sb.append(", postTag04=").append(postTag04);
        sb.append(", postTag05=").append(postTag05);
        sb.append(", postTag06=").append(postTag06);
        sb.append(", postTag07=").append(postTag07);
        sb.append(", postTag08=").append(postTag08);
        sb.append(", postTag09=").append(postTag09);
        sb.append(", postTag10=").append(postTag10);
        sb.append(", postContent=").append(postContent);
        sb.append(", postDate=").append(postDate);
        sb.append(", postIsDel=").append(postIsDel);
        sb.append(", postComment=").append(postComment);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}