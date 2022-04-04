import request from '@/utils/request'
const api_name = '/eduservice/edu-vod'
export default {
    getPlayAuth(vid) {
        return request({
            baseUrl:"http://localhost:8222",
            url: `${api_name}/get-play-auth/${vid}`,
            method: 'get'
        })
    }
}
