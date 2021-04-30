<template>
  <div class="mcontaner">
    <Header v-on:headerSearch="headerSearch"></Header>
    <el-col :span="18">
      <div class="card1">
        <el-timeline>
          <el-timeline-item
            v-bind:timestamp="blog.created"
            placement="top"
            v-for="(blog, index) in blogs"
            color="pink"
          >
            <!-- <el-timeline-item placement="top"> -->
            <el-card :class="index % 2 == 0 ? 'card2_1' : 'card2_2'">
              <div v-if="blog.blogImage" class="blog-image">
                <img :src="blog.blogImage" class="card_img" />
              </div>
              <h4>
                <router-link
                  :to="{ name: 'BlogDetail', params: { blogId: blog.id } }"
                >
                  {{ blog.title }}
                </router-link>
              </h4>
              <p>{{ blog.description }}</p>
            </el-card>
          </el-timeline-item>
        </el-timeline>

        <el-pagination
          class="mpage"
          background
          layout="prev, pager, next"
          :current-page="currentPage"
          :page-size="pageSize"
          :total="total"
          @current-change="page"
        >
        </el-pagination>
      </div>
    </el-col>
    <el-col :span="6">
      <div class="card3">
        <el-card class="my-back-card" hoverable>
          <img
            src="https://snz04pap001files.storage.live.com/y4meHOPyuixvI7NqlSJ9wjp97NHYG8-nAbOShHviSSfaZF2wzU95lS67twheT8NXpyKv3yDOHlZI0gPN9NHoIGKw6ppcK55descEoRzbAHFO3Bo6mbW-W4MQzKUzyQ6cQFZDGtCIV5zrQNUHkH9RrNpCgKBasGNT1_NBy7B8_b9m5jAJMQXW5VDK-42aD7YRwmH?width=814&height=855&cropmode=none"
          />
          <div class="my-bottom-container">
            <div class="my-nickname">Chonghe Ping</div>
            <div class="my-signature">
              My dear baby, wish you health, happiness, peace to grow
              up
            </div>
            <el-collapse accordion>
              <el-collapse-item title="My Interests" name="1">
                <div>I love my Family, it's the interest of my life；</div>
                <div>
                  I like to travel to different places in the real world, to
                  explore the world of games and the world of coding to take on
                  new challenges.
                </div>
                <div></div>
              </el-collapse-item>
              <el-collapse-item title="The most memorable place" name="2">
                <div>Okayama, Japan</div>
              </el-collapse-item>
              <el-collapse-item title="Favorite game" name="3">
                <div>Fire Emblem</div>
                <div>Final Fantasy</div>
                <div>Uncharted</div>
              </el-collapse-item>
              <el-collapse-item title="About Blog" name="4">
                <div>Backend: Springboot, Mybatis plus, Shiro, Jwt</div>
                <div>Frontend: Vue.js, element UI</div>
                <div>Deployment: Aliyun, Mysql, Docker</div>
              </el-collapse-item>
            </el-collapse>
            <el-divider />
            <div class="social-container">
              <div class="social-text">Social：</div>
              <div class="social-item">
                <a href="#">
                  <img src="@/assets/icons/facebook.png" class="social-icon" />
                </a>
                <a href="#">
                  <img src="@/assets/icons/linkedin.png" class="social-icon" />
                </a>
                <a href="https://github.com/chonghe">
                  <img src="@/assets/icons/github.png" class="social-icon" />
                </a>
                <a href="https://gitee.com/cping0324">
                  <img src="@/assets/icons/gitee.png" class="social-icon" />
                </a>
              </div>
            </div>
            <el-divider />
            <div class="busuanzi">
              <span id="busuanzi_container_site_pv" style="display:none">
                Pageviews
                <span id="busuanzi_value_site_pv"></span>
              </span>
              <el-row />
              <span id="busuanzi_container_site_uv" style="display:none">
                visitor volume
                <span id="busuanzi_value_site_uv"></span>
              </span>
            </div>
          </div>
        </el-card>
      </div>
    </el-col>
  </div>
</template>
<script>
import Header from "../components/Header";
import "../assets/busuanzi.pure.mini"
export default {
  name: "Blogs.vue",
  components: { Header },
  data() {
    return {
      blogs: {},
      currentPage: 1,
      total: 0,
      pageSize: 5,
      color: {
        color1: "pink",
        color2: "#ff9f8f",
      },
      value: new Date(),
      // activeName: "1",
      headerSearchTitle: ''
    };
  },
  methods: {
    page(currentPage) {
      const _this = this;
      // _this.$axios.get("/blogs?currentPage=" + currentPage).
      _this.$axios.get("/blogs?currentPage=" + currentPage+"&headerSearchTitle=" + this.headerSearchTitle).
      then((res) => {
        console.log(this.headerSearchTitle)
        console.log(res);
        _this.blogs = res.data.data.records;
        _this.currentPage = res.data.data.current;
        _this.total = res.data.data.total;
        _this.pageSize = res.data.data.size;
        //_this.headerSearchTitle = res.data.data.title;
      });
    },
    headerSearch(headerSearch){
      this.headerSearchTitle = headerSearch
      this.page(1);
    }
  },
  created() {
    this.page(1);
  },
  watch: {
    $route(to, from) {
      if (to.path != from.path) {
        script.fetch();
      }
      // console.log(to.path);
    }
  }
};
</script>

<style scoped>
.mcontaner {
  margin: 0 auto;
  text-align: left;
}
.card_img {
  float: left;
  width: 230px;
  height: 230px;
  margin: 20px;
}
.el-timeline-item__timestamp {
  background-color: burlywood;
  text-align: left !important;
}
.card1 {
  background-color: #fafae9;
  box-shadow: 0px 0px 10px #1aab8a inset;
  border: 1px solid #fafae9;
  padding: 20px;
  /* max-width: 1024px; */
  margin: 0 auto;
}
.card2_1 {
  background-color: #c0d592;
}
.card2_2 {
  background-color: #ff9f8f;
}
.card3 {
  height: 100%;
  margin-left: 5px;
}
.card3 img {
  width: 100%;
  height: 200px;
}

.my-back-card {
  padding: 0 !important;
}

.my-bottom-container {
  display: flex;
  flex-direction: column;
  /* align-items: center; */
  width: auto;
  margin: auto;
  margin-bottom: 30px;
  text-align: center !important;
}
.my-nickname {
  font-size: 22px;
  font-weight: bold;
  line-height: 60px;
}

.my-signature {
  font-size: 15px;
  color: #878d99;
  padding-bottom: 10px;
}
.social-container {
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
}
.social-text {
  font-size: 15px;
  color: #878d99;
}

.social-icon {
  width: 40px !important;
  height: 40px !important;
}

.social-item {
  width: auto;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.el-pagination {
  text-align: center;
}
</style>