import request from '@/utils/request'
const api_name='/eduservicecms/crm-banner'
export default {
    getPageList(page,limit,searchObj){
        return request(
            {
                url:`${api_name}/pageList/${page}/${limit}`,
                method:'post',
                data:searchObj
            }
        )
    },
    // getList(){
    //     return request(
    //         {
    //             url:`${api_name}/selectAll`,
    //             method:'get',
    //         }
    //     )
    // }
    // ,
    removeById(id)
    {

        return request({
                url:`${api_name}/deleteCourse/${id}`,
                method:'post',
                data:id
        
        })


    },
    save(crmBanner)
    {   
        return request({
        url:`${api_name}/add`,
        method:'post',
        data:crmBanner})
    },
    update(crmBanner){
        return request({
            url:`${api_name}/update`,
            method:'post',
            data:crmBanner})
    },
    selectOne(id){
        return request({
            url:`${api_name}/selectOne/${id}`,
            method:'get',
            })
    }



}