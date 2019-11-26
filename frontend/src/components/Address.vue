<template>
  <div class="block">
    <el-row>
      <span class="demonstration">配送地址</span>
      <el-cascader v-model="value" placeholder="请选择地址" :options="options" style="width:300px" @change="handleChange" />
      <span class="demonstration">详细地址</span>
      <el-input v-model="input" placeholder="请输入详细地址" clearable style="width:300px" />
      <el-button type="warning" size="small" @click="inputAddress">确 定</el-button>
    </el-row>
  </div>
</template>

<script>

export default {
  name: 'Address',
  data() {
    return {
      selectedOptions: [], // 存放默认值
      options: this.GLOBAL.options, // 存放城市数据
      input: '',
      value: ''
    }
  },
  methods: {
    handleChange(value) {
      console.log(value)
    },
    inputAddress() {
      this.$message({
        type: 'success',
        message: '地址修改成功!'
      })
    }
  }

}

</script>

<style scoped>
  .block{
    float:left;
    margin-left:250px;
    margin-bottom:40px;
  }
  .demonstration{
    margin-left:40px;
    margin-right:10px;
  }

</style>
