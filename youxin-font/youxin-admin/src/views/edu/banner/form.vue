<template>
  <div class="app-container">
    <el-form label-width="120px">
      <el-form-item label="幻燈片名称">
        <el-input v-model="banner.title" />
      </el-form-item>
      <el-form-item label="幻燈片排序">
        <el-input-number
          v-model="banner.sort"
          controls-position="right"
          :min="0"
        />
      </el-form-item>
      <el-form-item label="幻燈片分類">
        <el-select v-model="banner.linkUrl" clearable placeholder="请选择">
          <!--
    数据类型一定要和取出的json中的一致，否则没法回填
    因此，这里value使用动态绑定的值，保证其数据类型是number
    -->
          <el-option :value="'/course'" label="課程類型" />
          <el-option :value="'/teacher'" label="教師類型" />
        </el-select>
      </el-form-item>
      <!-- 讲师头像：TODO -->
      <!-- 讲师头像 -->
      <el-form-item label="幻燈片圖片">
        <!-- 头衔缩略图 -->
        <pan-thumb :image="banner.imageUrl"/>
        <!-- 文件上传按钮 -->
        <el-button
          type="primary"
          icon="el-icon-upload"
          @click="imagecropperShow = true"
          >選擇圖片
        </el-button>
        <!--
v-show：是否显示上传组件
:key：类似于id，如果一个页面多个图片上传控件，可以做区分
:url：后台上传的url地址
@close：关闭上传组件
@crop-upload-success：上传成功后的回调 -->
        <image-cropper
          v-show="imagecropperShow"
          :width="300"
          :height="300"
          :key="imagecropperKey"
          :url="BASE_API + '/eduservice/edu-oss/file/upload'"
          field="file"
          @close="close"
          @crop-upload-success="cropSuccess"
        />
      </el-form-item>

      <el-form-item>
        <el-button
          :disabled="saveBtnDisabled"
          type="primary"
          @click="saveOrUpdate"
          >保存
        </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import ImageCropper from "@/components/ImageCropper";
import PanThumb from "@/components/PanThumb";
import UpdateOrDelete from "@/api/edu/banner";
export default {
  components: { ImageCropper, PanThumb },
  data() {
    return {
      imagecropperShow: false,
      saveBtnDisabled: false,
      BASE_API: "http://localhost:8222",
      imagecropperKey: 0,
      banner: {
        id:"",
        title: "",
        linkUrl: "/course",
        sort: 1,
        imageUrl: "",
      },
    };
  },
  watch: {
    $route(to, from) {
      console.log("watch $route");
      this.select();
    },
  },
  created() {
    debugger
    this.select();
  },
  methods: {
    close(){
      this.imagecropperShow=false
      this.imagecropperKey=this.imagecropperKey+1
    },
    cropSuccess(response){ 
      console.log(response.url)
      this.banner.imageUrl=response.url
    },
    saveOrUpdate() {
      this.saveBtnDisabled = true;
      if (this.select()) {
        
        this.banner.id = this.$route.params.id;
        console.log("hello");
        return UpdateOrDelete.update(this.banner).then((response) => {
          if (response.success) {
            this.$message({
              type: "success",
              message: "修改成功!",
            });
            this.saveBtnDisabled = false;
            this.$router.push({ path: "/edu/banner/list" });
          } else {
            this.$message({
              type: "info",
              message: "修改失败!",
            });
          }
        });
      } else {
        return UpdateOrDelete.save(this.banner).then((response) => {
          if (response.success) {
            this.$message({
              type: "success",
              message: "添加成功!",
            });
            this.saveBtnDisabled = false;
            this.$router.push({ path: "/edu/banner" });
          } else {
            this.$message({
              type: "info",
              message: "添加失败!",
            });
          }
        });
      }
      
    },
    select() {
      let flag = false;
      const cur = this.banner;
      if (this.$route.params && this.$route.params.id) {
        const id = this.$route.params.id;
        flag = UpdateOrDelete.selectOne(id).then((respnse) => {
          this.banner = respnse.data.item;
          console.log(respnse.data.item.name);
          flag = true;
        });
      }

      return flag;
    },
  },
};
</script>