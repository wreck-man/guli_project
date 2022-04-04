import request from '@/utils/request'
export default {
  getList() {
    return request({
      url: `http://localhost:8222/eduservicecms/crm-front-banner/selectAll`,
      method: 'get'
    })
  }
}