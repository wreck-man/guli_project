import request from '@/utils/request'
const api_name='/eduservice/edu-subject'
export default{

    getList(searchObj){
        return request(
            {
                url:`${api_name}/list`,
                method:'post',
            }
        )
    },


}