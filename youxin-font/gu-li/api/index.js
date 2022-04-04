
import request from '@/utils/request'
export default{
  getIndexData(){
    return request({
      url: `http://localhost:8222/eduservice/index/hot`,
      method: 'get'
    })
  }
}