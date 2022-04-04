<template>
    
  <div class="app-container">
      <!--查询表单-->
      <!-- 表格 -->
<el-table 
v-loading="listLoading"
:data="list"
    element-loading-text="数据加载中"
    border
    fit
    highlight-current-row>
<el-table-column
    label="序号"
    width="70"
    align="center">
<template slot-scope="scope">
 {{ (page - 1) * limit + scope.$index + 1 }}
    </template>
    </el-table-column>
<el-table-column prop="orderNo" label="訂單號" width="160" />
<el-table-column prop="courseTitle" label="課程名称" width="120" />
<el-table-column prop="teacherName" label="教師名称" width="80" />
<el-table-column prop="nickname" label="用戶名稱"  width="80"/>
<el-table-column prop="gmtCreate" label="購買时间" width="160"/>
<el-table-column prop="totalFee" label="價錢" width="80"/>
</el-table>
<!-- 分页 -->
<el-pagination
:current-page="page"
:page-size="limit"
:total="total"
    style="padding: 30px 0; text-align: center;"
    layout="total, prev, pager, next, jumper"
@current-change="fetchData"
/>
    

  </div>
</template>

<script>
    import teacher from '@/api/order'
import cascader from '../../../../element-ui/packages/cascader/src/cascader.vue'
    export default{
  components: { cascader },
        created(){
            this.fetchData()
        },
        data(){

            return{
                page:1,
                limit:8,
                total:0,
                searchObj:{},
                listLoading:true,
                list:[]
            }
        },
        methods:{
            fetchData(page = 1) { // 调用api层获取数据库中的数据
            console.log('加载列表')
            this.page = page
            this.listLoading = true
            teacher.selectPages(this.page, this.limit, this.searchObj).then(response => {
// debugger 设置断点调试

            if (response.success === true) {
                console.log(response.data)
                this.list = response.data.list
                this.total = response.data.total
                console.log(response.data.total)
            }
                this.listLoading = false
                })
            },
            resetData(){
                this.searchObj={}
                this.fetchData()
            },
            removeDataById(id){
                this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(()=>{
            return teacher.removeById(id)
        }
        )
            .then(() => {
            this.fetchData()
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
        })

            }

            

        }
            




    }

</script>