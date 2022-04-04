import request from '@/utils/request'
const api_name = '/eduservice/edu-chapter'
export default {
    getNestedTreeList(courseId) {
        return request({
            url: `${api_name}/ChapterAndVideo/${courseId}`,
            method: 'post'
        })
    },
    removeById(id) {
        return request({
            url: `${api_name}/deleteChapter/${id}`,
            method: 'delete'
        })
    },
    save(chapter) {
        return request({
            url:`${api_name}/addChapter`,
            method: 'post',
            data: chapter
        })
    },
    getById(id) {
        return request({
            url: `${api_name}/${id}`,
            method: 'get'
        })
    },
    updateById(chapter) {
        return request({
            url: `${api_name}/updateChapter/${chapter.id}`,
            method: 'post',
            data: chapter
        })
    }

}
