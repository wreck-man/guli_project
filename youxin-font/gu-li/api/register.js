import request from '@/utils/request'
export default {
    //根据手机号码发送短信
    getMobile(email) {
        return request({
            url: `/registerservie/codmsm/getCodeMsm/${email}`,
            method: 'get'
        })
    },
    //用户注册
    submitRegister(formItem) {
        return request({
            url: `/serviceucenter/ucenter-member/register`,
            method: 'post',
            data: formItem
        })
    }
}
