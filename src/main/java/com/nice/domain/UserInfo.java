
package com.nice.domain;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.*;

import java.io.Serializable;


@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_info")
public class UserInfo extends Model<UserInfo> {

    private static final long serialVersionUID = 1L;
	@TableField("USER_ID")
	private Long id;




	@TableField("USER_NAME")
	private String userName;


     // 手机号码


	@TableField("MOBILE_PHONE")
	private String mobilePhone;



	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}

