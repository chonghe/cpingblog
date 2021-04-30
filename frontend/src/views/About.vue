<template>
  <div class="mcontaner">
    <Header></Header>
    <el-card class="el-card-d" shadow="always">
      <div class="infinite-list-wrapper" style="overflow: auto">
        <el-timeline infinite-scroll-disabled="disabled">
          <div v-if="allmessages.length > 0">
            <el-timeline-item
              v-for="(item, index) in allmessages"
              :key="index"
              :timestamp="item.createTime"
              placement="top"
            >
              <el-card class="el-card-m" style="height: 120px">
                <h4>{{ item.title }}：</h4>
                <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{ item.content }}</p>
              </el-card>
            </el-timeline-item>
          </div>
          <div v-else>
            <el-timeline-item placement="top">
              <el-card class="el-card-m" style="height: 120px">
                <h4>大黄子：</h4>
                <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 说点什么吧😁</p>
              </el-card>
            </el-timeline-item>
          </div>
        </el-timeline>
      </div>
      <el-card class="el-card-messages">
        <el-form ref="form" :model="form" label-width="80px">
          <el-form-item label="Title">
            <el-input v-model="form.title"></el-input>
          </el-form-item>
          <el-form-item label="Content">
            <el-input
              type="textarea"
              :rows="5"
              placeholder="Input message..."
              maxlength="200"
              v-model="form.content"
            ></el-input>
          </el-form-item>
          <el-button
            type="info"
            round
            class="submit-message"
            @click="submitMessage"
            >Submit</el-button
          >
        </el-form>
      </el-card>
    </el-card>
  </div>
</template>

<script>
import Header from "../components/Header";
export default {
  components: { Header },
  data() {
    return {
      form:{
        title:'',
        content:''
      },
      allmessages: "",
    };
  },
  submitMessage() {
    if (
      this.message == "" ||
      this.message.replace(/(^\s*)|(\s*$)/g, "") == ""
    ) {
      this.$message("写点啥提交也行呀😉");
      return;
    }
  },
};
</script>

<style>
.mcontaner {
  margin: 0 auto;
  text-align: left;
}
.infinite-list-wrapper {
  width: 100%;
  height: 500px;
}
.submit-message {
  width: 100%;
  background: rgb(235, 245, 247);
  color: cadetblue;
  letter-spacing: 20px;
}
@media screen and (max-width: 3000px) and (min-width: 767px) {
  .el-card-d {
    float: left;
    margin-top: 20px;
    margin-left: 10%;
    width: 80%;
    height: 90%;
  }
}
/*
  屏幕小于768px的
  */
@media screen and (max-width: 768px) and (min-width: 100px) {
  .el-card-d {
    width: 100%;
    height: 100%;
  }
}
</style>