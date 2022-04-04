<template>
  <div class="app-container">
    <!--表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-select v-model="searchObj.type" clearable placeholder="请选择">
          <el-option label="学员登录数统计" value="login_num" />
          <el-option label="学员注册数统计" value="register_num" />
          <el-option label="课程播放数统计" value="video_view_num" />
          <el-option label="每日课程数统计" value="course_num" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="searchObj.begin"
          type="date"
          placeholder="选择开始日期"
          value-format="yyyy-MM-dd"
        />
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="searchObj.end"
          type="date"
          placeholder="选择截止日期"
          value-format="yyyy-MM-dd"
        />
      </el-form-item>
      <el-button
        :disabled="btnDisabled"
        type="primary"
        icon="el-icon-search"
        @click="showCharts()"
        >查询</el-button
      >
    </el-form>
    <div class="chart-container">
      <div id="chart" class="chart" style="height: 500px; width: 100%" />
    </div>
  </div>
</template>
<script>
import * as echarts from "echarts";
import sta from "@/api/sta";
export default {
  data() {
    return {
      searchObj: {
        type: "",
        begin: "",
        end: "",
      },
      btnDisabled: false,
      chart: null,
      title: "",
      xData: [],
      yData: [],
    };
  },

  mounted() {},
  methods: {
    playChar() {
      var chartDom = document.getElementById("chart");
      var myChart = echarts.init(chartDom);
      var option;

      option = {
        xAxis: {
          type: "category",
          data: this.xData,
        },
        yAxis: {
          type: "value",
        },
        series: [
          {
            data: this.yData,
            type: "line",
            smooth: true,
          },
        ],
      };

      option && myChart.setOption(option);
    },
    showCharts() {
      if(this.searchObj.type===""||!this.searchObj.begin===""||this.searchObj.end===""){
        this.$message({
          type:"success",
          message:"請先選擇要查詢的數據"
        })
        return;
      }
      sta.getChartIndfo(this.searchObj).then((response) => {
        this.xData = response.data.map.xList;
        this.yData = response.data.map.yList;
        console.log(response.data.yList);
        switch (this.searchObj.type) {
          case "register_num":
            this.title = "学员注册数统计";
            break;
          case "login_num":
            this.title = "学员登录数统计";
            break;
          case "video_view_num":
            this.title = "课程播放数统计";
            break;
          case "course_num":
            this.title = "每日课程数统计";
            break;
        }
        this.playChar();
      });
    },
  },
};
// 基于准备好的dom，初始化echarts实例
</script>

