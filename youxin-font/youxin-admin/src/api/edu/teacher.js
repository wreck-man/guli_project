import request from '@/utils/request'
const api_name='/eduservice/edu-teacher'
export default {
    getPageList(page,limit,searchObj){
        return request(
            {
                url:`${api_name}/${page}/${limit}`,
                method:'post',
                data:searchObj
            }
        )
    },
    getList(){
        return request(
            {
                url:`${api_name}/all`,
                method:'post',
            }
        )
    }
    ,
    removeById(id)
    {

        return request({
                url:`${api_name}/${id}`,
                method:'delete',
                data:id
        
        })


    },
    save(teacherQuery)
    {   
        return request({
        url:`${api_name}/save`,
        method:'post',
        data:teacherQuery})
    },
    update(id,teacher){
        return request({
            url:`${api_name}/${id}`,
            method:'post',
            data:teacher})
    },
    selectOne(id){
        return request({
            url:`${api_name}/${id}`,
            method:'get',
            })
    }



}