package com.nice.domain;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangliang
 * @since 2017-08-28
 */
@TableName("short_link")
@ToString
public class ShortLink extends Model<ShortLink> {

    private static final long serialVersionUID = 1L;

	private Long id;
    /**
     * 平台编号
     */
	@TableField("app_id")
	private Long appId;
    /**
     * 原链接
     */
	private String link;
    /**
     * 短链接
     */
	@TableField("short_link")
	private String shortLink;
    /**
     * 创建人
     */
	@TableField("created_by")
	private Long createdBy;
    /**
     * 创建时间
     */
	@TableField("created_time")
	private Long createdTime;
    /**
     * 更新人
     */
	@TableField("updated_by")
	private Long updatedBy;
    /**
     * 更新时间
     */
	@TableField("updated_time")
	private Long updatedTime;
    /**
     * 删除标识(1:正常  2:删除)
     */
	private Integer deleted;
	//指定此字段不映射
	@TableField(exist = false)
	private String host;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getShortLink() {
		return shortLink;
	}

	public void setShortLink(String shortLink) {
		this.shortLink = shortLink;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Long createdTime) {
		this.createdTime = createdTime;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Long getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Long updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
