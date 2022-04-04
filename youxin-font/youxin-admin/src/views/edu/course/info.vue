<template>
  <div class="app-container">
    <h2 style="text-align: center">发布新课程</h2>
    <el-steps
      :active="1"
      process-status="wait"
      align-center
      style="margin-bottom: 40px"
    >
      <el-step title="填写课程基本信息" />
      <el-step title="创建课程大纲" />
      <el-step title="提交审核" />
    </el-steps>
    <el-form label-width="120px">
      <el-form-item label="课程标题">
        <el-input
          v-model="courseInfo.title"
          placeholder=" 示例：机器学习项目课：从基础到搭建项目视频课程。专业名称注意大小写"
        />
      </el-form-item>

      <!-- 所属分类 TODO -->
      <!-- 所属分类：级联下拉列表 -->
      <!-- 一级分类 -->
      <el-form-item label="课程类别">
        <el-select
          @change="subjectLevelOneChanged"
          v-model="courseInfo.subjectParentId"
          placeholder="请选择"
        >
          <el-option
            v-for="subject in subjectNestedList"
            :key="subject.id"
            :label="subject.title"
            :value="subject.id"
          />
        </el-select>
        <el-select v-model="courseInfo.subjectId" placeholder="请选择">
          <el-option
            v-for="subject in subSubjectList"
            :key="subject.value"
            :label="subject.title"
            :value="subject.id"
          />
        </el-select>
      </el-form-item>
      <!-- 二级分类 -->

      <!-- 课程讲师 TODO -->
      <el-form-item label="课程讲师">
        <el-select v-model="courseInfo.teacherId" placeholder="请选择">
          <el-option
            v-for="teacher in teacherList"
            :key="teacher.id"
            :label="teacher.name"
            :value="teacher.id"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="总课时">
        <el-input-number
          :min="0"
          v-model="courseInfo.lessonNum"
          controls-position="right"
          placeholder="请填写课程的总课时数"
        />
      </el-form-item>

      <!-- 课程简介 TODO -->
      <!-- 课程简介-->
      <el-form-item label="课程简介">
        <tinymce :height="300" v-model="courseInfo.description" />
      </el-form-item>

      <!-- 课程封面 TODO -->
      <!-- 课程封面-->

      <el-form-item label="课程封面">
        <el-upload
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
          :action="BASE_API + '/eduservice/edu-oss/file/upload?host=cover'"
          class="avatar-uploader"
        >
          <img :src="courseInfo.cover" />
        </el-upload>
      </el-form-item>

      <el-form-item label="课程价格">
        <el-input-number
          :min="0"
          v-model="courseInfo.price"
          controls-position="right"
          placeholder="免费课程请设置为0元"
        />
        元
      </el-form-item>

      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="next"
          >保存并下一步</el-button
        >
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import course from "@/api/edu/course";
import teacher from "@/api/edu/teacher";
import subject from "@/api/edu/subject";
import Tinymce from "@/components/Tinymce";
const defaultForm = {
  id:"",
 title: '',
 subjectParentId:'',
 subjectId: '',
 teacherId: '',
 lessonNum: 0,
 description: '',
 cover: 'https://youxin-edu.oss-cn-beijing.aliyuncs.com/kk1.png',
 price: 0
}
export default {
  components: { Tinymce },
  data() {
    return {
      subjectNestedList: [], //一级分类列表
      subSubjectList: [], //二级分类列表
      teacherList: [],
      saveBtnDisabled: false,
      BASE_API: process.env.BASE_API,
      courseInfo: defaultForm,
      cover:'https://youxin-edu.oss-cn-beijing.aliyuncs.com/kk1.png'
    };
  },
  created() {
    
    this.getTeacher();
    this.subjectList();
    this.getReturnCourse();
  },
  methods: {
    handleAvatarSuccess(res, file) {
      console.log(res); // 上传响应
      console.log(URL.createObjectURL(file.raw)); // base64编码
      this.courseInfo.cover = res.data.url;
    },
    
    fetchList(){
      for (var i = 0; i < this.subjectNestedList.length; i++) {
        if (this.subjectNestedList[i].id === this.courseInfo.s) {
          this.subSubjectList = this.subjectNestedList[i].children;
          console.log(this.subjectList);
          this.courseInfo.subjectId = "";
          break;
        }
      }


    }
    ,
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
    },
    getTeacher() {
      teacher.getList().then((response) => {
        this.teacherList = response.data.list;
      });
    },
    subjectList() {
      subject.getList().then((response) => {
        this.subjectNestedList = response.data.list;
      });
    },
    subjectLevelOneChanged(value) {
      for (var i = 0; i < this.subjectNestedList.length; i++) {
        if (this.subjectNestedList[i].id === value) {
          this.subSubjectList = this.subjectNestedList[i].children;
          console.log(this.subjectList);
          this.courseInfo.subjectId = "";
          break;
        }
      }
    },
    saveInfo() {
      let id = null;
      course
        .saveInfo(this.courseInfo)
        .then((response) => {
          this.$message({
            type: "success",
            message: "保存成功!",
          });
          return response;
        })
        .then((response) => {
          id = response.data.id;
          this.$router.push({ path: "/edu/course/chapter/" + id });
        })
        .catch((response) => {
          this.$message({
            type: "error",
            message: "保存失敗!",
          });
        });
    },
    updateInfo() {
      course.updateInfo(this.courseInfo).then((response) => {
          this.$message({
            type: "success",
            message: "修改成功!",
          });
          return response;
        })
        .then((response) => {
          let id = response.data.id;
          this.$router.push({ path: "/edu/course/chapter/" + id });
        })
        .catch((response) => {
          this.$message({
            type: "error",
            message: "修改失敗!",
          });
        });
    },
    getReturnCourse(){
      if(this.$route.params && this.$route.params.id){
        let id=this.$route.params.id
        course.getInfo(id).then(response=>{
          this.courseInfo=response.data.courseInfo
          for(var i=0;i<this.subjectNestedList.length;i++){
            if(this.subjectNestedList[i].id===this.courseInfo.subjectParentId){
              this.subSubjectList=this.subjectNestedList[i].children
              break;
            }



          }
        })
      }



    }
    
    ,
    next() {
      this.saveBtnDisabled = true;
      if (this.$route.params && this.$route.params.id) {
        const info = { id: this.$route.params.id };
        this.courseInfo.id=info.id
        this.saveBtnDisabled=false
        this.updateInfo();
      } else {
        this.saveBtnDisabled=false
        this.saveInfo();
      }
    },
  },
};
</script>

<style scoped>
.tinymce-container {
  line-height: 29px;
}
</style>