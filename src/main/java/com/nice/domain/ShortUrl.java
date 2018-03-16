package com.nice.domain;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 * 短链和长链映射关系
 * </p>
 *
 * @author wangliang
 * @since 2017/8/31
 */
@Getter
@Setter
@ToString
@TableName(value = "short_url")
public class ShortUrl extends Model<ShortUrl> {
    private static final long serialVersionUID = 1L;
    @TableField("id")
    private Long id;

    /**
     * 长链
     */
    @TableField("long_url")
    private String longUrl;

    /**
     * 短链编码
     */
    @TableField("short_code")
    private String shortCode;

    /**
     * 短链
     */
    @TableField("short_url")
    private String shortUrl;

    /**
     * 点击数
     */
    private int  clicks;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
