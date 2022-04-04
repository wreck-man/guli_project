import request from '@/utils/request';
const api_name = '/eduservice/frontTeacher';
export default {
    getPageList(page, limit) {
        return request({
            url: `${api_name}/pageList/${page}/${limit}`,
            method: 'post'
        })
    },
    getById(id){
        return request({
            url: `/eduservice/frontTeacher/selectOne/${id}`,
            method: 'post'
        })
    }

}