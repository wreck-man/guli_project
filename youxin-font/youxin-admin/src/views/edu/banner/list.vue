<template>
  <div class="app-container">
    <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="searchObj.title" placeholder="幻燈片名稱" />
      </el-form-item>
      <el-form-item>
        <el-select v-model="searchObj.linkUrl" clearable placeholder="幻燈片分類">
          <el-option :value="'/course'" label="課程類型" />
          <el-option :value="'/banner'" label="講師類型" />
        </el-select>
      </el-form-item>
      <el-form-item label="添加时间">
        <el-date-picker
          v-model="searchObj.gmtCreate"
          type="datetime"
          placeholder="选择开始时间"
          value-format="yyyy-MM-dd"
          default-time="00:00:00"
        />
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="searchObj.gmtModified"
          type="datetime"
          placeholder="选择截止时间"
          value-format="yyyy-MM-dd"
          default-time="00:00:00"
        />
      </el-form-item>
      <el-button type="primary" icon="el-icon-search" @click="fetchData()"
        >查询</el-button
      >
      <el-button type="default" @click="resetData()">清空</el-button>
    </el-form>
    <!-- 表格 -->
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="数据加载中"
      border
      fit
      highlight-current-row
    >
      <el-table-column label="序号" width="100" align="center">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column prop="title" label="名称" width="100" />
      <el-table-column label="头衔" width="100">
        <template slot-scope="scope">
          {{ scope.row.linkUrl === "'/course'" ? "課程類型" : "讲师類型" }}
        </template>
      </el-table-column>
      <el-table-column prop="gmtCreate" label="添加时间" width="250" />
      <el-table-column prop="gmtModified" label="修改时间" width="250" />
      <el-table-column prop="sort" label="排序" width="80" />
      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <router-link :to="'/edu/banner/form/' + scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit"
              >修改</el-button
            >
          </router-link>
          <el-button
            type="danger"
            size="mini"
            icon="el-icon-delete"
            @click="removeDataById(scope.row.id)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <el-pagination
      :current-page="page"
      :page-size="limit"
      :total="total"
      style="padding: 30px 0; text-align: center"
      layout="total, prev, pager, next, jumper"
      @current-change="fetchData"
    />
  </div>
</template>

<script>
import banner from "@/api/edu/banner";
export default {
  created() {
    this.fetchData();
  },
  data() {
    return {
      page: 1,
      limit: 10,
      total: 0,
      searchObj: {
          title:"",
          linkUrl:"",
          
      },
      listLoading: true,
      list: null,
    };
  },
  methods: {
    fetchData(page = 1) {
      // 调用api层获取数据库中的数据
      console.log("加载列表");
      this.page = page;
      this.listLoading = true;
      console.log(this.searchObj)
      banner
        .getPageList(this.page, this.limit, this.searchObj)
        .then((response) => {
          // debugger 设置断点调试

          if (response.success === true) {
            this.list = response.data.list;
            this.total = response.data.total;
          }
          this.listLoading = false;
        });
    },
    resetData() {
      this.searchObj = {};
      this.fetchData();
    },
    removeDataById(id) {
      this.$confirm("此操作将永久删除该文件, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          return banner.removeById(id);
        })
        .then(() => {
          this.fetchData();
          this.$message({
            type: "success",
            message: "删除成功!",
          });
        });
    },
  },
};
</script>