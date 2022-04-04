import request from '@/utils/request'
const api_name='/eduservice/edu-course'
export default{
    getPulishCourse(id){
        return request({
            url: `${api_name}/getCourse/${id}`,
            method: 'post',
             })
    },
    PulishCourse(id){
        return request({
            url: `${api_name}/publish/${id}`,
            method: 'post',
             })
    },
    getPageList(page, limit, searchObj) {
        return request({
        url: `${api_name}/page/list/${page}/${limit}`,
        method: 'get',
        params: searchObj
         })
        },

    saveInfo(searchObj){
        return request(
            {
                url:`${api_name}/add`,
                method:'post',
                data:searchObj
            }
        )
    },
    updateInfo(searchObj){
        return request(
            {
                url:`${api_name}/update`,
                method:'post',
                data:searchObj
            }
        )
    },
    getInfo(id){
        return request(
            {
                url:`${api_name}/getInfo/${id}`,
                method:'get',
                
            }
        )
    },
    removeCourse(id){
        return request(
            {
                url:`${api_name}/deleteCourse/${id}`,
                method:'post',
                
            }
        )
    }


}