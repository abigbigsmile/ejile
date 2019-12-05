<template>
    <div>
      <el-col :span="4">
        <el-input v-model="searchState" placeHolder="按操作搜索"/>
      </el-col>
      <el-col :span="4">
        <div class="block">
          <el-date-picker
            v-model="dateValue"
            type="daterange"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期">
          </el-date-picker>
        </div>
      </el-col>
      <el-col :span="4" :offset="12">
        <export-excel v-if="this.logList != null" :list="this.logList"
                      :t-header="['#ID', '创建时间', 'IP地址', '服务器端方法', '参数', '操作描述', '响应时间']"
                      :t-value="['id', 'createTime', 'ip', 'method', 'params', 'operation', 'time']"></export-excel>
      </el-col>
<!--      <el-table :data="this.logList.slice((currentPage-1)*cntPerPage,currentPage*cntPerPage)" style="width: 100%">-->
      <el-table :data="this.logList" style="width: 100%">
        <el-table-column
          sortable
          fixed="left"
          prop="id"
          label="#ID"
          width="180">
        </el-table-column>
        <el-table-column
          sortable
          prop="createTime"
          label="创建时间"
          width="230">
        </el-table-column>
        <el-table-column
          prop="ip"
          label="IP地址"
          width="180">
        </el-table-column>
        <el-table-column
          prop="method"
          label="服务器端方法"
          width="230">
        </el-table-column>
        <el-table-column
          prop="params"
          label="参数"
          width="180">
        </el-table-column>
        <el-table-column
          prop="operation"
          label="操作描述"
          width="180">
        </el-table-column>
        <el-table-column
          sortable
          prop="time"
          label="响应时间"
          width="180">
        </el-table-column>
      </el-table>

      <div class="block">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5, 10, 20, 40]"
          :page-size="5"
          layout="total, sizes, prev, pager, next, jumper"
          :total="this.totalElements">
        </el-pagination>
      </div>
    </div>
</template>

<script>
  import ExportExcel from "../../components/ExportExcel"
  export default {
    name: "Monitor",
    components: {ExportExcel},
    data() {
      return {
        logList: [
          {
            CREATE_TIME: "2019-11-15 02:36:35",
            ID: 1,
            IP: "127.0.0.1",
            METHOD: "com.qtztlink.ejile.common.controller.Test.methodOne()",
            OPERATION: "执行方法一",
            PARAMS: "  name: Zhang",
            USERNAME: "Zhang San",
            _TIME: 0
          }
        ],
        currentPage: 1,
        cntPerPage: 5,
        searchState: '',
        totalElements: 1,
        dateValue: ''
      }
    },
    created() {
      this.fetchData()
    },
    methods: {
      handleSizeChange(val) {
        // console.log(`每页 ${val} 条`);
        this.cntPerPage = val
        this.currentPage=1
        this.fetchData()
      },
      handleCurrentChange(val) {
        // console.log(`当前页: ${val}`);
        this.currentPage = val
        this.fetchData()
      },
      fetchData(){
        let url="/log?page="+this.currentPage+"&size="+this.cntPerPage
        if(this.searchState!==''){
          url=url+"&keyword="+this.searchState
        }
        console.log(this.dateValue)
        if(this.dateValue!==''){
          let newDate=[]
          this.dateValue.forEach(item=>{
            var d = new Date(item)
            let youWant=d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate() + ' ' + d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds();
            newDate.push(youWant)
          })
          url=url+"&choseTime="+newDate
        }
        this.$get(url).then(res => {
          this.logList = res.data.data.content
          this.totalElements=res.data.data.totalElements
        }).catch(err => console.log(err))
      }
    },
    watch: {
      dateValue(val){
        this.fetchData()
      },
      searchState(val){
        this.fetchData()
      }
    }
  }
</script>

<style scoped>

</style>
