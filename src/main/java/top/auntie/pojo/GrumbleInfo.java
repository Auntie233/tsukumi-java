package top.auntie.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "grumble_info")
public class GrumbleInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    private String title;

    private String description;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "del_flag")
    private Boolean delFlag;

    private String type;

    /**
     * 心情：dic
     */
    private String mode;

    /**
     * 赞
     */
    private Integer islike;

    /**
     * 转发数
     */
    private Integer repost;

    /**
     * 被评论id-该条消息为评论时存在
     */
    @Column(name = "reply_post")
    private Integer replyPost;

    /**
     * 被转发id
     */
    @Column(name = "repost_id")
    private Integer repostId;

    private String content;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return del_flag
     */
    public Boolean getDelFlag() {
        return delFlag;
    }

    /**
     * @param delFlag
     */
    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取心情：dic
     *
     * @return mode - 心情：dic
     */
    public String getMode() {
        return mode;
    }

    /**
     * 设置心情：dic
     *
     * @param mode 心情：dic
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    public Integer getIslike() {
        return islike;
    }

    public void setIslike(Integer islike) {
        this.islike = islike;
    }

    /**
     * 获取转发数
     *
     * @return repost - 转发数
     */
    public Integer getRepost() {
        return repost;
    }

    /**
     * 设置转发数
     *
     * @param repost 转发数
     */
    public void setRepost(Integer repost) {
        this.repost = repost;
    }

    /**
     * 获取被评论id-该条消息为评论时存在
     *
     * @return reply_post - 被评论id-该条消息为评论时存在
     */
    public Integer getReplyPost() {
        return replyPost;
    }

    /**
     * 设置被评论id-该条消息为评论时存在
     *
     * @param replyPost 被评论id-该条消息为评论时存在
     */
    public void setReplyPost(Integer replyPost) {
        this.replyPost = replyPost;
    }

    /**
     * 获取被转发id
     *
     * @return repost_id - 被转发id
     */
    public Integer getRepostId() {
        return repostId;
    }

    /**
     * 设置被转发id
     *
     * @param repostId 被转发id
     */
    public void setRepostId(Integer repostId) {
        this.repostId = repostId;
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }
}