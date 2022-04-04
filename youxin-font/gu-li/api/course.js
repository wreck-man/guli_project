import request from '@/utils/request'
export default {
    getPageList(page, limit, searchObj) {
        return request({
            url: `/eduservice/frontCourse/pageList/${page}/${limit}`,
            method: 'post',
            data: searchObj
        })
    },
    getById(courseId) {
        return request({
            url: `/eduservice/frontCourse/webCourseInfo/${courseId}`,
            method: 'post',
        })
    },
    
    // 获取课程二级分类
    getNestedTreeList2() {
        return request({
            url: `/eduservice/edu-subject/list`,
            method: 'post'
        })
    }
}
