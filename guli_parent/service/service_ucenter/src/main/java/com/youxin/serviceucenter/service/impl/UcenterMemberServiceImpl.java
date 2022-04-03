package com.youxin.serviceucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.youxin.commonutils.utils.JwtUtils;
import com.youxin.commonutils.utils.MD5;
import com.youxin.servicebase.exception.YouXinException;
import com.youxin.serviceucenter.entity.UcenterMember;
import com.youxin.serviceucenter.entity.vo.LoginVo;
import com.youxin.serviceucenter.entity.vo.RegisterVo;
import com.youxin.serviceucenter.mapper.UcenterMemberMapper;
import com.youxin.serviceucenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-03-24
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String login(LoginVo loginVo) {
        System.out.println(loginVo.getMobile());
        System.out.println(loginVo.getPassword());
        if (loginVo == null) {
            throw new YouXinException(20001, "用戶登錄異常，請稍後再試。");
        }
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        password = MD5.encrypt(password);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("mobile", mobile);
        UcenterMember ucenterMember = baseMapper.selectOne(queryWrapper);
        if (ucenterMember == null) {
            throw new YouXinException(20001, "用戶不存在，請先注冊。");
        }
        if (!ucenterMember.getPassword().equals(password)) {
            throw new YouXinException(20001, "密碼錯誤，請重新輸入。");
        }
        if (ucenterMember.getIsDisabled()) {
            throw new YouXinException(20001, "賬號已被鎖定，請聯係管理員解封。");

        }
        String token = JwtUtils.getJwtToken(ucenterMember.getId(), ucenterMember.getNickname());
        return token;
    }

    @Override
    public boolean register(RegisterVo registerVo) {
        if (registerVo == null) {
            throw new YouXinException(20001, "輸入的信息有誤，請重新注冊。");
        }
        String password = registerVo.getPassword();
        String nickname = registerVo.getNickname();
        String code = "\""+registerVo.getCode()+"\"";
        System.out.println(code+"saaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        String mobile = registerVo.getMobile();
        String email=registerVo.getEmail();
        System.out.println(email);
        String registerCode = redisTemplate.opsForValue().get(email);
        System.out.println(registerCode);
        if (StringUtils.isEmpty(mobile) ||
                StringUtils.isEmpty(mobile) ||
                StringUtils.isEmpty(password) ||
                StringUtils.isEmpty(code)) {
            throw new YouXinException(20001, "輸入信息不完整，請檢查輸入信息是否有誤。");

        }
        if (!code.equals(registerCode)) {
            throw new YouXinException(20001, "驗證碼輸入錯誤，請重新輸入。");
        }
        password = MD5.encrypt(password);
        UcenterMember ucenterMember = new UcenterMember();
        ucenterMember.setAvatar("https://youxin-edu.oss-cn-beijing.aliyuncs.com/kk1.png");
        ucenterMember.setMobile(mobile);
        ucenterMember.setNickname(nickname);
        ucenterMember.setPassword(password);
        ucenterMember.setIsDisabled(false);
        int flag = baseMapper.insert(ucenterMember);
        return flag > 0;
    }

    @Override
    public UcenterMember getLoginInfo(String id) {
        UcenterMember ucenterMember=baseMapper.selectById(id);
//        LoginVo loginVo=new LoginVo();
//        BeanUtils.copyProperties(ucenterMember,loginVo);
        return ucenterMember;
    }

    @Override
    public UcenterMember getOpenIdMember(String openid) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("openid",openid);
        UcenterMember member=baseMapper.selectOne(wrapper);
        return member;
    }

    @Override
    public Integer selectRegiter(String data) {
        Integer integer=baseMapper.selectRegiter(data);
        return integer;
    }
}
