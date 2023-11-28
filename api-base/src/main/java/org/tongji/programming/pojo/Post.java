package org.tongji.programming.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

import lombok.Data;

/**
 * 发帖信息表
 *
 * @TableName post
 */
@TableName(value = "post")
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
     * 对应的具体作业的序号
     * <p>
     * 如果本项为空但week和/或chapter不为-1，则表示帖子为周总体问题或章节总体问题
     * <p>
     * 如果本项与week/chapter皆为-1，则表示帖子为学期总体问题
     */
    private Integer postHwId;

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
     * <p>
     * 如果是发帖,则为NULL
     * <p>
     * 如果是回帖,则为对应帖子的post_id(以此为依据构建发帖回帖的树形结构)
     */
    private Integer postAnswerId;

    /**
     * 帖子类型('Question':首发问题 'QuestionsAdditional':追问 'Answer':回帖 'Other':其它 '/':预留)
     * <p>
     * 以 post_term + post_ccode + post_hwup_or_hw_id 为基准汇聚,具体排序规则?
     * <p>
     * 本字段是否多余?
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
    @TableField("post_tag_01")
    private Object postTag01;

    /**
     * 约定的tag 2标记(0:此标记未置位 1:此标记已置位)
     */
    @TableField("post_tag_02")
    private Object postTag02;

    /**
     * 约定的tag 3标记(0:此标记未置位 1:此标记已置位)
     */
    @TableField("post_tag_03")
    private Object postTag03;

    /**
     * 约定的tag 4标记(0:此标记未置位 1:此标记已置位)
     */
    @TableField("post_tag_04")
    private Object postTag04;

    /**
     * 约定的tag 5标记(0:此标记未置位 1:此标记已置位)
     */
    @TableField("post_tag_05")
    private Object postTag05;

    /**
     * 约定的tag 6标记(0:此标记未置位 1:此标记已置位)
     */
    @TableField("post_tag_06")
    private Object postTag06;

    /**
     * 约定的tag 7标记(0:此标记未置位 1:此标记已置位)
     */
    @TableField("post_tag_07")
    private Object postTag07;

    /**
     * 约定的tag 8标记(0:此标记未置位 1:此标记已置位)
     */
    @TableField("post_tag_08")
    private Object postTag08;

    /**
     * 约定的tag 9标记(0:此标记未置位 1:此标记已置位)
     */
    @TableField("post_tag_09")
    private Object postTag09;

    /**
     * 约定的tag 10标记(0:此标记未置位 1:此标记已置位)
     */
    @TableField("post_tag_10")
    private Object postTag10;

    /**
     * 帖子标题
     */
    private String postTitle;

    /**
     * 发帖具体内容(允许贴图,Richtext?)
     */
    private String postContent;

    /**
     * 发帖时间
     */
    private LocalDateTime postDate;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return getPostHwId() == post.getPostHwId() && Objects.equals(getPostId(), post.getPostId()) && Objects.equals(getPostTerm(), post.getPostTerm()) && Objects.equals(getPostCcode(), post.getPostCcode()) && Objects.equals(getPostWeek(), post.getPostWeek()) && Objects.equals(getPostChapter(), post.getPostChapter()) && Objects.equals(getPostAnswerId(), post.getPostAnswerId()) && Objects.equals(getPostType(), post.getPostType()) && Objects.equals(getPostSno(), post.getPostSno()) && Objects.equals(getPostPriority(), post.getPostPriority()) && Objects.equals(getPostTag01(), post.getPostTag01()) && Objects.equals(getPostTag02(), post.getPostTag02()) && Objects.equals(getPostTag03(), post.getPostTag03()) && Objects.equals(getPostTag04(), post.getPostTag04()) && Objects.equals(getPostTag05(), post.getPostTag05()) && Objects.equals(getPostTag06(), post.getPostTag06()) && Objects.equals(getPostTag07(), post.getPostTag07()) && Objects.equals(getPostTag08(), post.getPostTag08()) && Objects.equals(getPostTag09(), post.getPostTag09()) && Objects.equals(getPostTag10(), post.getPostTag10()) && Objects.equals(getPostTitle(), post.getPostTitle()) && Objects.equals(getPostContent(), post.getPostContent()) && Objects.equals(getPostDate(), post.getPostDate()) && Objects.equals(getPostIsDel(), post.getPostIsDel()) && Objects.equals(getPostComment(), post.getPostComment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPostId(), getPostTerm(), getPostCcode(), getPostHwId(), getPostWeek(), getPostChapter(), getPostAnswerId(), getPostType(), getPostSno(), getPostPriority(), getPostTag01(), getPostTag02(), getPostTag03(), getPostTag04(), getPostTag05(), getPostTag06(), getPostTag07(), getPostTag08(), getPostTag09(), getPostTag10(), getPostTitle(), getPostContent(), getPostDate(), getPostIsDel(), getPostComment());
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
        sb.append(", postHwupOrHwId=").append(postHwId);
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
