import request from '@/utils/request'
const api_name = '/eduservice/edu-video'
export default {
    saveVideoInfo(videoInfo) {
        return request({
            url: `${api_name}/addVideo`,
            method: 'post',
            data: videoInfo
        })
    },
    getVideoInfoById(id) {
        return request({
            url: `${api_name}/video-info/${id}`,
            method: 'get'
        })
    },
    updateVideoInfoById(videoInfo) {
        return request({
            url: `${api_name}/updateVideo/${videoInfo.id}`,
            method: 'post',
            data: videoInfo
        })
    },
    removeById(id) {
        return request({
            url: `${api_name}/deleteVideo/${id}`,
            method: 'delete'
        })
    }
}
