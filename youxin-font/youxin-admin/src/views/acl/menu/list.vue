<template>
  <div class="app-container">
    <el-input v-model="filterText" placeholder="Filter keyword" style="margin-bottom:30px;"/>

    <el-tree
  :data="data"
  show-checkbox
  node-key="id"
  :default-expanded-keys="[2, 3]"
  :default-checked-keys="[5]"
  :props="defaultProps1"
  default-expand-all
      :expand-on-click-node="false"
      :render-content="renderContent">
  </el-tree>

    

    <el-dialog :visible.sync="dialogFormVisible" :title="dialogFormValue">
      <el-form ref="menu" :model="menu" :rules="menuValidateRules" label-width="120px">
        <el-form-item label="菜单名称" prop="name">
          <el-input v-model="menu.name"/>
        </el-form-item>
        <el-form-item label="访问路径" prop="path">
          <el-input v-model="menu.path"/>
        </el-form-item>
        <el-form-item label="组件路径" prop="component">
          <el-input v-model="menu.component"/>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="restData()">取 消</el-button>
        <el-button type="primary" @click="append()">确 定</el-button>
      </div>
    </el-dialog>
    <!-- 添加功能的窗口 -->
    <el-dialog :visible.sync="dialogPermissionVisible" title="添加功能">
      <el-form ref="permission" :model="permission" :rules="permissionValidateRules" label-width="120px">
        <el-form-item label="功能名称" prop="name">
          <el-input v-model="permission.name"/>
        </el-form-item>
        <el-form-item label="访问路径">
          <el-input v-model="permission.path"/>
        </el-form-item>
        <el-form-item label="组件路径">
          <el-input v-model="permission.component"/>
        </el-form-item>
        <el-form-item label="功能权限值" prop="permissionValue">
          <el-input v-model="permission.permissionValue"/>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="restData()">取 消</el-button>
        <el-button type="primary" @click="appendPermission()">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import menu from '@/api/acl/menu'

const menuForm = {
  name: '',
  pid: 0,
  path: '',
  component: '',
  type: '1'
}
const perForm = {
  permissionValue: '',
  type: '2',
  name: '',
  path: '',
  component: '',
  pid: 0
}

export default {

  data() {
    return {
      defaultProps1: {
          children: 'children',
          label: 'name'
        },
        data:[],
      //  data: [{
      //     id: 1,
      //     label: '一级 1',
      //     children: [{
      //       id: 4,
      //       label: '二级 1-1',
      //       children: [{
      //         id: 9,
      //         label: '三级 1-1-1'
      //       }, {
      //         id: 10,
      //         label: '三级 1-1-2'
      //       }]
      //     }]
      //   }, {
      //     id: 2,
      //     label: '一级 2',
      //     children: [{
      //       id: 5,
      //       label: '二级 2-1'
      //     }, {
      //       id: 6,
      //       label: '二级 2-2'
      //     }]
      //   }, {
      //     id: 3,
      //     label: '一级 3',
      //     children: [{
      //       id: 7,
      //       label: '二级 3-1'
      //     }, {
      //       id: 8,
      //       label: '二级 3-2'
      //     }]
      //   }],
      filterText: '',
      menuList: [],
      defaultProps: {
        children: 'children',
        label: 'name'
      },
      k1:"sa",
      dialogFormValue: '添加菜单',
      dialogFormVisible: false,
      dialogPermissionVisible: false,
      menu: menuForm,
      permission: perForm,
      menuValidateRules: {
        name: [{required: true, trigger: 'blur', message: '菜单名必须输入'}],
        path: [{required: true, trigger: 'blur', message: '菜单路径必须输入'}],
        component: [{required: true, trigger: 'blur', message: '组件名称必须输入'}]
      },
      permissionValidateRules: {
        name: [{required: true, trigger: 'blur', message: '功能名称必须输入'}],
        permissionValue: [{required: true, trigger: 'blur', message: '功能权限值必须输入'}]
      }
    }
  },
  // 监听上面文本框搜索
  watch: {
    filterText(val) {
      this.$refs.menuTree.filter(val)
    }
  },

  created() {
    SVGPoint
    this.fetchNodeList()
  },

  methods: {
    renderContent(h, { node, data, store }) {
        return (
          <span class="custom-tree-node">
            <span>{node.label}</span>
            <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <el-button size="mini" type="text" on-click={ () => this.newappend(data) }>添加</el-button>
              <el-button size="mini" type="text" on-click={ () => this.hasPerm('permission.update')?(data.level==4?this.updateFunction(data):this.getById(data)):this.info() }>修改</el-button>
              <el-button size="mini" type="text" on-click={ () => this.hasPerm('permission.remove')?this.remove( data):this.info() }>刪除</el-button>
            </span>
          </span>);
      },
      newappend(data){
        if(data.level===2||data.level===1&& this.hasPerm('permission.add')){
          this.dialogFormVisible = true
          this.menu.pid=data.id
        }else if( this.hasPerm('permission.add')){
          this.permission.pid = data.id
        this.dialogPermissionVisible = true
        }
        

      },
      info(){
        this.$message({
          type: 'errot',
          message: '您沒有該權限!'
        })
      },
    fetchNodeList() {
      menu.getNestedTreeList().then(response => {
        if (response.success === true) {
          console.log(response.data.children.gmtCreate)
          this.data=response.data.children
          this.menuList = response.data.children
          console.log(this.menuList)
        }
      })
    },
    filterNode(value, data) {
      if (!value) return true
      return data.title.toLowerCase().indexOf(value.toLowerCase()) !== -1
    },
    remove(data) {
      console.log(data)

      this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        return menu.removeById(data.id)
      }).then(() => {
        this.fetchNodeList()// 刷新列表
        this.$message({
          type: 'success',
          message: '删除成功!'
        })
      }).catch((response) => { // 失败
        if (response === 'cancel') {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        } else {
          this.$message({
            type: 'error',
            message: '删除失败'
          })
        }
      })
    },
    appendPermission() {
      this.$refs.permission.validate(valid => {
        if (valid) {
          if (this.permission.id) {
            this.update(this.permission)
          } else {
            menu.saveLevelOne(this.permission).then(response => {
              this.dialogPermissionVisible = false
              this.$message({
                type: 'success',
                message: '添加功能成功'
              })
              // 刷新页面
              this.fetchNodeList()
              this.menu = {...menuForm}
              this.permission = {...perForm}
            })
          }
        }
      })
    },
    appendLevelOne() {
      menu.saveLevelOne(this.menu)
        .then(response => {
          this.dialogFormVisible = false
          this.$message({
            type: 'success',
            message: '添加一级菜单成功'
          })
          // 刷新页面
          this.fetchNodeList()
          this.menu = {...menuForm}
          this.permission = {...perForm}
        })
        .catch(response => {
          // 你们写：判断点击取消清空一下
          this.dialogFormVisible = false
          this.$message({
            type: 'error',
            message: '添加一级菜单失败'
          })
          this.menu = {...menuForm}
          this.permission = {...perForm}
        })
    },

    append() {
      this.$refs.menu.validate(valid => {
        if (valid) {
          if (!this.menu.id) { // 添加
            if (this.menu.pid == 0) {
              this.appendLevelOne() // 一级分类的添加
            } else {
              this.appendLevelTwo() // 二级分类的添加
            }
          } else { // 修改
            this.update(this.menu)
          }
        }
      })
    },

    update(obj) {
      debugger
      menu.update(obj).then(response => {
        this.dialogFormVisible = false
        this.$message({
          type: 'success',
          message: '修改成功'
        })
        // 刷新页面
        this.fetchNodeList()
        this.restData()
      })
    },
    appendLevelTwo() {
      menu.saveLevelOne(this.menu)
        .then(response => {
          // 1、把文本框关
          this.dialogFormVisible = false
          // 2、提示成功
          this.$message({
            type: 'success',
            message: "添加二级分类成功"
          })
          // 3、刷新页面
          this.fetchNodeList()
          // 4、把menu清空
          this.menu = {...menuForm}
          this.permission = {...perForm}
        })
        .catch(response => {
          // 1、把文本框关
          this.dialogFormVisible = false
          // 2、提示成功
          this.$message({
            type: 'error',
            message: "添加二级分类失败"
          })
          // 3、把menu清空
          this.menu = {...menuForm}
          this.permission = {...perForm}

        })
    },
    getById(data) {
      this.dialogFormVisible = true
      this.menu = data
    },
    updateFunction(data) {
      this.dialogPermissionVisible = true
      this.permission = data
    },
    restData() {
      this.dialogPermissionVisible = false
      this.dialogFormVisible = false
      this.menu = {...menuForm}
      this.permission = {...perForm}
    }
  }
}
</script>
