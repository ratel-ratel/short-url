
package com.nice.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.nice.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by yxc on 2018/12/12.
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    Long queryMax();

    List<UserInfo> queryAll();
}

