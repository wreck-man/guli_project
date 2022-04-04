import request from '@/utils/request'
const api_name = '/servicestatistics/statistics-daily'
export default {
    createStatistics(day) {
        return request({
            url: `${api_name}/${day}`,
            method: 'get'
        })
    },
    getChartIndfo(searchObj) {
        return request({
            url: `${api_name}/${searchObj.begin}/${searchObj.end}/${searchObj.type}`,
            method: 'get'
        })
    }
}
