<template>
  <!-- <div class="m-content">
    <div><img src="../assets/home.png"/></div>
    <h3>Welcome to Chonghe's blog</h3>
    <div class="block">
      <el-avatar :size="50" :src="user.avatar"></el-avatar>
      <div>{{ user.username }}</div>
    </div>

    <div class="maction">
      <span><el-link href="/blogs">Home</el-link></span>
      <el-divider direction="vertical"></el-divider>
      <span><el-link type="success" href="/blog/add">New Blog</el-link></span>

      <el-divider direction="vertical"></el-divider>
      <span v-show="!hasLogin"><el-link type="primary" href="/login">Login</el-link></span>

      <span v-show="hasLogin"><el-link type="danger" @click="logout">Logout</el-link></span>
    </div>
  </div> -->
  <div class="menu-container">
    <div class="nav-logo">
      <img src="../assets/logo.png" class="logo-img" />
      <span class="logo-text">Apple Family</span>
    </div>
    <div class="nav-menu-search">
      <el-menu v-model="current" mode="horizontal" background-color="pink">
        <el-menu-item key="1">
          <router-link to="/"> Home </router-link>
        </el-menu-item>
        <!-- <el-menu-item key="2">
          <el-dropdown>
            <span class="el-dropdown-link">
              Type<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>黄金糕</el-dropdown-item>
              <el-dropdown-item>狮子头</el-dropdown-item>
              <el-dropdown-item>螺蛳粉</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </el-menu-item> -->
         
        <!-- <el-menu-item key="3">
          <router-link to="/search"> Search </router-link>
        </el-menu-item> -->
        <el-menu-item key="4">
          <router-link to="/about"> About </router-link>
        </el-menu-item>
      </el-menu>
      <el-input
        v-model="searchTitle"
        placeholder="search strange things here"
        style="width: 240px"
        suffix-icon="el-icon-search"
        @keyup.enter.native="sendSearchTitle"
      />
      <div class="maction">
        <!-- <span><el-link href="/blogs">Home</el-link></span> -->
        <el-divider direction="vertical"></el-divider>
        <span v-show="hasLogin"
          ><el-link type="success" href="/blog/add">New Blog</el-link></span
        >

        <el-divider direction="vertical"></el-divider>
        <span v-show="!hasLogin"
          ><el-link type="primary" href="/login">Login</el-link></span
        >

        <span v-show="hasLogin"
          ><el-link type="danger" @click="logout">Logout</el-link></span
        >
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Header",
  data() {
    return {
      user: {
        username: "Please Login",
        avatar: "",
      },
      hasLogin: false,
      current: "",
      searchTitle: "",
    };
  },
  methods: {
    logout() {
      const _this = this;
      _this.$axios
        .get("/logout", {
          headers: {
            Authorization: localStorage.getItem("token"),
          },
        })
        .then((res) => {
          _this.$store.commit("REMOVE_INFO");
          _this.$router.push("/login");
        });
    },
    sendSearchTitle() {
      this.$emit("headerSearch", this.searchTitle);
    },
  },
  created() {
    if (this.$store.getters.getUser.username) {
      this.user.username = this.$store.getters.getUser.username;
      this.user.avatar = this.$store.getters.getUser.avatar;

      this.hasLogin = true;
    }
  },
};
</script>

<style scoped>
.menu-container {
  /* max-width: 960px;
  margin: 0 auto;
  text-align: center; */

  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  padding: 0 20px;
  line-height: 100px;
  background-color: pink;
  margin-bottom: 10px;
}

.nav-logo {
  display: flex;
  flex-direction: row;
  align-items: center;
}

.logo-img {
  width: 80px;
  height: 80px;
  border-radius: 50%;
  border: 3px solid #e5e5e5;
  margin-right: 10px;
}

.logo-text {
  font-size: 24px;
}

.nav-menu-search {
  display: flex;
  flex-direction: row;
  align-items: center;
  margin-right: 10px;
}
.demo-form-inline {
  display: flex;
  flex-direction: row;
  align-items: center;
  margin-top: 60px;
}

.ant-menu {
  font-size: 16px !important;
}

.ant-menu-horizontal {
  border-bottom: none !important;
}

.login {
  color: black;
  margin-left: 20px;
}

.login:hover {
  color: black;
}

.login-header {
  color: black;
  margin-left: 20px;
}

.login-header-img {
  width: 60px;
  height: 60px;
  border-radius: 50%;
  border: 3px solid #e5e5e5;
}
/* .m-content {
    max-width: 960px;
    margin: 0 auto;
    text-align: center;
  }*/
.maction {
  margin: 10px 0;
}
</style>