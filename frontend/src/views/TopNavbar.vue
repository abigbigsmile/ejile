<template>
  <div class="top">
    <el-row id="nav-fixed" :class="{nav_fixed : isFixed}">
      <el-col :span="2" class="logo" />
      <el-col :span="2">
        <div class="grid-content" @click="$router.push('/index')">首页</div>
      </el-col>
      <!--      左侧-->
      <template v-if="userRole === 'consumer'">
        <el-col :span="2">
          <div class="grid-content" @click="$router.push('/service')">外卖服务</div>
        </el-col>
        <el-col :span="2">
          <div class="grid-content" @click="$router.push('/shoppingtrolley')">订单管理</div>
        </el-col>
      </template>
      <template v-else-if="userRole === 'shop'">
        <el-col :span="2">
          <div class="grid-content" @click="$router.push('/menuedit')">菜单管理</div>
        </el-col>
        <el-col :span="2">
          <div class="grid-content" @click="$router.push('/service')">评论管理</div>
        </el-col>
        <el-col :span="2">
          <div class="grid-content" @click="$router.push('/ordermanager')">订单管理</div>
        </el-col>
      </template>
      <template v-else>
        <el-col :span="2" />
      </template>
      <!--      空白-->
      <el-col :span="emptySpan()" />
      <!--      右侧， 分别为用户和商家 和 不登录状态-->
      <template v-if="userRole === 'consumer'">
        <el-col :span="2">
          <el-dropdown>
            <span class="el-dropdown-link">
              <i class="el-icon-s-custom" />
              {{ userName }}<i class="el-icon-arrow-down el-icon--right" />
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item icon="el-icon-medal-1" class="dropdown" @click.native="$router.push('/consumerinfo')">{{
                userName }}
              </el-dropdown-item>
              <el-dropdown-item
                icon="el-icon-user"
                class="dropdown"
                divided
                @click.native="$router.push('/consumerinfo/info')"
              >个人资料
              </el-dropdown-item>
              <el-dropdown-item
                icon="el-icon-s-order"
                class="dropdown"
                @click.native="$router.push('/consumerinfo/history')"
              >历史订单
              </el-dropdown-item>
              <el-dropdown-item
                icon="el-icon-bell"
                class="dropdown"
                @click.native="$router.push('/consumerinfo/contact')"
              >店家互动
              </el-dropdown-item>
              <el-dropdown-item
                icon="el-icon-setting"
                class="dropdown"
                @click.native="$router.push('/consumerinfo/setting')"
              >账号设置
              </el-dropdown-item>
              <el-dropdown-item icon="el-icon-circle-close" class="dropdown" divided @click.native="logout">退出
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </el-col>
      </template>
      <template v-else-if="userRole === 'shop'">
        <el-col :span="2">
          <el-dropdown>
            <span class="el-dropdown-link">
              <i class="el-icon-s-shop" />
              {{ userName }}<i class="el-icon-arrow-down el-icon--right" />
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item icon="el-icon-medal-1" class="dropdown" @click.native="$router.push('/shopinfo')">{{
                userName }}
              </el-dropdown-item>
              <el-dropdown-item
                icon="el-icon-platform-eleme"
                class="dropdown"
                divided
                @click.native="$router.push('/shopinfo/info')"
              >店铺资料
              </el-dropdown-item>
              <el-dropdown-item
                icon="el-icon-s-order"
                class="dropdown"
                @click.native="$router.push('/shopinfo/history')"
              >历史订单
              </el-dropdown-item>
              <el-dropdown-item icon="el-icon-bell" class="dropdown" @click.native="$router.push('/shopinfo/contact')">
                顾客互动
              </el-dropdown-item>
              <!--              <el-dropdown-item icon="el-icon-key" class="dropdown" @click.native="$router.push('/shopinfo/password')">修改密码</el-dropdown-item>-->
              <el-dropdown-item
                icon="el-icon-setting"
                class="dropdown"
                @click.native="$router.push('/shopinfo/setting')"
              >账号设置
              </el-dropdown-item>
              <el-dropdown-item icon="el-icon-circle-close" class="dropdown" divided @click.native="logout">退出
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </el-col>
      </template>
      <template v-else>
        <el-col :span="1">
          <div class="grid-content" @click="$router.push('/login')">登录</div>
        </el-col>
        <el-col :span="1">
          <div class="grid-content" @click="$router.push('/signin')">注册</div>
        </el-col>
      </template>
      <!--el-col :span="2"><div class="grid-content" @click="$router.push('/shoppingtrolley')">购物车</div></el-col-->
    </el-row>
  </div>
</template>

<script>
import store from '../store'
import { Notification } from 'element-ui'
export default {
  name: 'TopNavbar',
  data() {
    return {
      userToken: store.state.account.token,
      activeIndex: '1',
      isFixed: false,
      offsetTop: 0
    }
  },
  computed: {
    userRole() {
      let role = this.$db.get('ROLES')
      if (role === '"consumer"') {
        role = 'consumer'
      } else if (role === '"shop"') {
        role = 'shop'
      } else {
        role = 'none'
      }
      return role
    },
    userName() {
      const name = this.$db.get('USER')
      return name.substr(1).substr(0, name.length - 2)
    }
  },
  created() {
    const that = this
    if ((typeof this.userToken === 'string')) {
      if ('WebSocket' in window) {
        this.$root.$myWebscoket = new WebSocket('ws://' + process.env.BASE_URL.replace('http://', '') + 'myWebSocket?token=' + this.userToken.valueOf())
        this.$root.$myWebscoket.onmessage = function(res) {
          if (that.$route.path !== '/consumerinfo/contact') {
            const data = JSON.parse(res.data)
            if (data.state === '1') {
              Notification({
                title: '提示',
                message: '有商家给您发来了信息!',
                duration: 2000
              })
            } else {
              Notification({
                title: '提示',
                message: '有用户给您发来了信息!',
                duration: 2000
              })
            }
          }
        }
      }
    }
  },
  mounted() {
    // 设置bar浮动阈值为 #fixedBar 至页面顶部的距离
    this.offsetTop = document.querySelector('#nav-fixed').offsetTop
    // 开启滚动监听
    // window.addEventListener('scroll', this.handleScroll);
  },
  destroyed() {
    // 离开页面 关闭监听 不然会报错
    window.removeEventListener('scroll', this.handleScroll)
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath)
    },
    // 滚动监听  滚动触发的效果写在这里
    handleScroll() {
      const scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop
      this.isFixed = scrollTop >= this.offsetTop
    },

    logout() {
      console.log('登出')
      this.$db.clear()
      this.$router.push('/index')
      location.reload()
    },
    emptySpan() {
      if (this.$db.get('ROLES') === '"consumer"') {
        return 10
      } else if (this.$db.get('ROLES') === '"shop"') {
        return 7
      } else {
        return 12
      }
    }
  }
}
</script>

<style scoped>
  .top {
    margin-bottom: 1.25rem;
    box-shadow: 0 4px 8px 0 rgba(7,17,27,.1);
  }

  #nav-fixed {
    background: white;
    border: 0;
    border-radius: 10px;
  }

  .nav_fixed {
    position: fixed;
    z-index: 2;
    top: 0;
    width: 77.2%;
  }

  .el-row {
    background: rgba(255, 255, 255, 0);
    border-radius: 10px;
    line-height: 2.2rem;
  }

  .el-col {
    margin: 0.625rem 1.5rem;
  }

  .el-col div:hover {
    background: rgba(255, 255, 255, 0.6);
    border-radius: 0.625rem;
    cursor: pointer;
    transition: all 0.5s;

    box-shadow: 0px 10px 10px rgba(0,0,0,0.9);
  }

  .logo {
    background-image: url("images/dgut.jpg");
    border-radius: 5px;
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    width: 50px;
    height: 50px;
    background-repeat: no-repeat;
    position: relative;
  }

  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }

  .grid-content span {
    border: 1px solid;
    border-radius: 50px;
    padding: 5px;
    margin-right: 0.5rem;
  }

  .dropdown:hover {
    color: #008836;
    border-color: #008836;
    background-color: rgba(255, 205, 86, 0.1);
  }
</style>
