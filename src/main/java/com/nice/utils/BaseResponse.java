package com.nice.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 * 返回信息
 * </p>
 *
 * @author wangliang
 * @since 2017/8/31
 */
@Getter
@Setter
@ToString
public class BaseResponse<T> implements Serializable {

    /**
     * 请求结果
     */
    protected Integer returnCode;
    /**
     * 错误信息
     */
    protected String message;

    protected T dataInfo;

}
