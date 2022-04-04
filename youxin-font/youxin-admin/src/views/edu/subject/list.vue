
<template>
  <div>
    <el-input placeholder="输入关键字进行过滤" v-model="filterText"> </el-input>

    <el-tree
      class="filter-tree"
      :data="data"
      :props="defaultProps"
      default-expand-all
      :filter-node-method="filterNode"
      ref="tree"
    >
    </el-tree>
  </div>
</template>
<script>
import subject from "@/api/edu/subject";
export default {
  watch: {
    filterText(val) {
      this.$refs.tree.filter(val);
    },
  },
  created() {
     this.getList();
  },
  methods: {
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },
    getList() {
        subject.getList().then(response=>{
            console.log(response.data.list)
            this.data=response.data.list


        })


    },
  },

  data() {
    return {
      filterText: "",
      data: [],
      defaultProps: {
        children: "children",
        label: "title",
      },
    };
  },
};
</script>