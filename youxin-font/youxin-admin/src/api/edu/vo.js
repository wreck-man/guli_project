import request from '@/utils/request'
const api_name = '/eduservice/edu-vod'
export default {
    removeById(id) {
        return request({
        url: `${api_name}/remove/${id}`,
        method: 'post'
         })
         }
}