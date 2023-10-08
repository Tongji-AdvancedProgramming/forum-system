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
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer postId;

    /**
     * 
     */
    private String postTerm;

    /**
     * 
     */
    private String postCcode;

    /**
     * 
     */
    private String postHwupOrHwId;

    /**
     * 
     */
    private Integer postWeek;

    /**
     * 
     */
    private Integer postChapter;

    /**
     * 
     */
    private Integer postAnswerId;

    /**
     * 
     */
    private Object postType;

    /**
     * 
     */
    private String postSno;

    /**
     * 
     */
    private String postPriority;

    /**
     * 
     */
    private Object postTag01;

    /**
     * 
     */
    private Object postTag02;

    /**
     * 
     */
    private Object postTag03;

    /**
     * 
     */
    private Object postTag04;

    /**
     * 
     */
    private Object postTag05;

    /**
     * 
     */
    private Object postTag06;

    /**
     * 
     */
    private Object postTag07;

    /**
     * 
     */
    private Object postTag08;

    /**
     * 
     */
    private Object postTag09;

    /**
     * 
     */
    private Object postTag10;

    /**
     * 
     */
    private String postContent;

    /**
     * 
     */
    private Date postDate;

    /**
     * 
     */
    private Object postIsDel;

    /**
     * 
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