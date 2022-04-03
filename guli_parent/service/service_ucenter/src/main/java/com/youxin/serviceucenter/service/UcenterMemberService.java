package com.youxin.serviceucenter.service;

import com.youxin.serviceucenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youxin.serviceucenter.entity.vo.LoginVo;
import com.youxin.serviceucenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-03-24
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(LoginVo loginVo);

    boolean register(RegisterVo registerVo);

    UcenterMember getLoginInfo(String token);

    UcenterMember getOpenIdMember(String openid);

    Integer selectRegiter(String data);
}
