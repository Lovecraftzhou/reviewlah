<template>
  <div class="navbar" style="display: flex">
    <div>
      <img alt="Vue logo" src="@/assets/reviewlah.png" style="height: 44px">
    </div>
    <div style="flex-grow: 1"></div>
    <div class="mt-4" style="margin-top: 8px">
      <el-input
          v-model="input"
          placeholder="Please input"
          class="input-with-select"
      >
        <template #prepend>
          <el-button :icon="Search" @click="doSearch(input)"/>
        </template>
        <template #append>
          <el-select v-model="searchType" placeholder="Select" style="width: 115px">
            <el-option label="Restaurant" value="1" />
            <el-option label="Post" value="2" />
          </el-select>
        </template>
      </el-input>
    </div>
    <div class="right-menu">
      <template>
        <el-tooltip content="Global Size" effect="dark" placement="bottom">
          <size-select id="size-select" class="right-menu-item hover-effect"/>
        </el-tooltip>
      </template>
      <el-dropdown class="avatar-container right-menu-item hover-effect" trigger="click">
        <div class="avatar-wrapper">
          <img :src="avatar" class="user-avatar">
          <i class="el-icon-caret-bottom"/>
        </div>
        <el-dropdown-menu>
          <router-link to="/profile/index">
            <el-dropdown-item>Profile</el-dropdown-item>
          </router-link>
          <router-link to="/">
            <el-dropdown-item>Dashboard</el-dropdown-item>
          </router-link>
          <a target="_blank" href="https://github.com/PanJiaChen/vue-element-admin/">
            <el-dropdown-item>Github</el-dropdown-item>
          </a>
          <a target="_blank" href="https://panjiachen.github.io/vue-element-admin-site/#/">
            <el-dropdown-item>Docs</el-dropdown-item>
          </a>
          <el-dropdown-item divided @click="logout">
            <span style="display:block;">Log Out</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
  <div class="main_container">
    <div class="area_title" style="text-align: center;margin-bottom: 48px !important;">
      <h2 class="css-rlqqlq">Merchants</h2>
    </div>
    <el-col
        v-for="(merchant,index) in merchantList"
        :key="index"
        :span="8"
        :offset="2"
    >
      <el-card :body-style="{ padding: '16px' }">
        <img
            src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
            class="image"
        />
        <div style="padding: 14px">
          <span>{{merchant.name}}</span>
          <div class="bottom" style="margin-bottom: 10px">
            <el-rate
                v-model="merchant.avg_rate"
                disabled
                show-score
                text-color="#ff9900"
                score-template="{value} points"
            />

            <el-button type="danger" round>Edit</el-button>
          </div>
          <el-text class="mx-1">{{'address: '+merchant.address_code}}</el-text>
        </div>
      </el-card>
    </el-col>
  </div>
</template>

<script>
import SizeSelect from '@/components/SizeSelect'
import {getPostDetail, getPosts} from "@/api/post";
import {Search} from '@element-plus/icons-vue'
import {getMerchantDeatail, getMerchantRecommendList, searchMerchant} from "@/api/merchant";

export default {
  computed: {
    Search() {
      return Search
    }
  },
  components: {
    SizeSelect,
  },
  data() {
    return {
      avatar: '',
      input:'',
      searchType:'',
      posts:[],
      postDetailVisible:false,
      postDetail:{},
      merchantList:[
        {
          "address_code": "120751",
          "user_id": 29,
          "name": "JemBBQ",
          "avator": "http://defaultUserAvator",
          "avg_rate": 3.2,
          "category": [
            "BBQ"
          ]
        }
      ],
      currentIndex: 0, // 当前显示的图片的索引
      timer: null,// 自动切换的定时器
      searchInput: '',
    }
  },
  created() {
    this.avatar = localStorage.getItem('Reviewlah.avatar')
    getPosts(localStorage.getItem('Reviewlah.id')).then((res)=>{
      this.posts=res.list
    })
    let searchInput = this.$route.query.searchInput
    if (typeof searchInput === "string"){
      this.searchInput = searchInput
      searchMerchant(searchInput).then((res)=>{
        this.merchantList = res.list
      })
    }
  },
  // mounted() {
  //   // 页面加载完成后，开始自动播放
  //   this.go()
  // },
  methods: {
    async logout() {
      await this.$store.dispatch('user/logout')
      this.$router.push(`/login?redirect=${this.$route.fullPath}`)
    },
    showPostDeatail(postId){
      this.postDetailVisible = true
      getPostDetail(postId).then((res)=>{
        this.postDetail = res.list
      })
    },
    play() {
      this.timer = setInterval(() => {
        this.currentIndex++;
        if (this.currentIndex >= this.merchantList.length) {
          this.currentIndex = 0;
        }
      }, 3000);
    },
    stop(){
      clearInterval(this.timer);
    },
    go(){
      this.play();
    },
    change(index){
      this.currentIndex = index;
    },
    toMerchantPage(userId){
      this.$router.push({path: '/merchantDetail',query:{userId:userId}});
    },
    doSearch(input){
      searchMerchant(input).then((res)=>{
        this.merchantList = res.list
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.navbar {
  height: 50px;
  overflow: hidden;
  position: relative;
  background: #fff;
  box-shadow: 0 1px 4px rgba(0, 21, 41, .08);

  .right-menu {
    float: right;
    height: 100%;
    line-height: 50px;

    &:focus {
      outline: none;
    }

    .right-menu-item {
      display: inline-block;
      padding: 0 8px;
      height: 100%;
      font-size: 18px;
      color: #5a5e66;
      vertical-align: text-bottom;

      &.hover-effect {
        cursor: pointer;
        transition: background .3s;

        &:hover {
          background: rgba(0, 0, 0, .025)
        }
      }
    }

    .avatar-container {
      margin-right: 30px;

      .avatar-wrapper {
        margin-top: 5px;
        position: relative;

        .user-avatar {
          cursor: pointer;
          width: 40px;
          height: 40px;
          border-radius: 10px;
        }

        .el-icon-caret-bottom {
          cursor: pointer;
          position: absolute;
          right: -20px;
          top: 25px;
          font-size: 12px;
        }
      }
    }
  }
}

.css-rlqqlq {
  font-family: 'Poppins', 'Helvetica Neue', Helvetica, Arial, sans-serif;
  font-size: 28px;
  font-weight: 700;
  -webkit-letter-spacing: -0.4px;
  -moz-letter-spacing: -0.4px;
  -ms-letter-spacing: -0.4px;
  letter-spacing: -0.4px;
  line-height: 36px;
  word-wrap: break-word !important;
  word-break: break-word !important;
  overflow-wrap: break-word !important;
  color: rgba(45, 46, 47, 1);
}

.main_container {
  box-sizing: border-box;
  min-width: 1144px;
  max-width: 1224px;
  width: 100%;
  margin: auto;
  padding: 0px 40px;
  padding-top: 56px !important;
  padding-bottom: 56px !important;
  border-top: 1px solid rgba(235, 235, 235, 1);
  border-color: rgba(235, 235, 235, 1);
}

.posts_contaner {
  margin-top: -32px;
  margin-left: -16px;
  margin-right: -16px;
  display: block;
  font-size: 0;
  line-height: 1;
  text-align: left;
  border-collapse: initial;
  border-spacing: 32px 0;
  min-width: 100%;
  table-layout: auto;
  border-color: rgba(235, 235, 235, 1);
}

.post_container {
  margin-top: 32px;
  margin-left: auto;
  margin-right: auto;
  padding-left: 16px;
  padding-right: 16px;
  width: 33.33333%;
  zoom: 1;
  display: inline-block;
  border-collapse: collapse;
  border-spacing: 0;
  box-sizing: border-box;
  vertical-align: top;
  border-color: rgba(235, 235, 235, 1);
}

.post_content {
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  height: 376px;
  padding: 16px 24px;
  border-width: 1px;
  border-style: solid;
  border-color: rgb(235, 235, 235);
  border-collapse: collapse;
  border-spacing: 0;
  margin-left: 16px;
  margin-right: 16px;
}

.post_header {
  border-collapse: initial;
  border-spacing: 8px 0;
  margin-left: -8px;
  margin-right: -8px;
  display: table;
  min-width: 100%;
  table-layout: auto;
}

.post_title_container {
  border-color: rgb(235, 235, 235);
  margin-top: 16px !important;
}

.post_bottom {
  flex: 1;
  margin: 8px -24px 0 -24px;
}

.post_pic {
  border-color: rgb(235, 235, 235);
  height: 100%;
  position: relative;
  width: 100%;
  background-position: center center;
  background-repeat: no-repeat;
  background-size: cover;
  cursor: pointer;
}

.post_pic_img{
  outline: none;
  position: absolute;
  inset: 0px;
  width: 100%;
  height: 100%;
}

.post_title {
  max-width: 100%;
  font-family: 'Open Sans', 'Helvetica Neue', Helvetica, Arial, sans-serif;
  font-size: 16px;
  font-weight: 700;
  -webkit-letter-spacing: 0px;
  -moz-letter-spacing: 0px;
  -ms-letter-spacing: 0px;
  letter-spacing: 0px;
  line-height: 24px;
  color: rgba(45, 46, 47, 1);
  text-align: left;
}

.post_title_link {
  font-weight: bold;
  display: block;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  width: auto;
  font-family: 'Open Sans', 'Helvetica Neue', Helvetica, Arial, sans-serif;
  border-radius: 3px;
  -webkit-text-decoration: none;
  text-decoration: none;
  font-weight: 600;
  color: rgba(2, 122, 151, 1);
  font-size: 14px;
  line-height: 20px;
  cursor: pointer;
}

.post_avatar_container {
  border-collapse: collapse;
  border-spacing: 0;
  margin-left: 4px;
  margin-right: 4px;
  vertical-align: middle;
  box-sizing: border-box;
  display: table-cell;
}

.post_avatar_link {
  font-family: "Open Sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  text-decoration: none;
  font-weight: 600;
  color: rgb(2, 122, 151);
  font-size: 14px;
  line-height: 20px;
  display: block;
  border-radius: 50%;
  vertical-align: baseline;
}

.post_avatar {
  height: 40px;
  aspect-ratio: auto 40 / 40;
  width: 40px;
  -webkit-user-drag: element;
  user-select: none;
  border-radius: 50%;
  vertical-align: middle;
  box-sizing: border-box;
}

.post_author_container {
  border-collapse: collapse;
  border-spacing: 0;
  margin-left: 4px;
  margin-right: 4px;
  vertical-align: middle;
  box-sizing: border-box;
  display: table-cell;
  width: 100%;
}

.post_author{
  font-family: 'Open Sans','Helvetica Neue',Helvetica,Arial,sans-serif;
  font-size: 16px;
  font-weight: 700;
  -webkit-letter-spacing: 0px;
  -moz-letter-spacing: 0px;
  -ms-letter-spacing: 0px;
  letter-spacing: 0px;
  line-height: 24px;
  color: rgba(45,46,47,1);
  text-align: left;
}

.post_time{
  font-family: 'Open Sans','Helvetica Neue',Helvetica,Arial,sans-serif;
  font-size: 14px;
  font-weight: 400;
  -webkit-letter-spacing: 0px;
  -moz-letter-spacing: 0px;
  -ms-letter-spacing: 0px;
  letter-spacing: 0px;
  line-height: 20px;
  color: rgba(45,46,47,1);
  text-align: left;
}

.input-with-select .el-input-group__prepend {
  background-color: var(--el-fill-color-blank);
}

*{
  margin: 0;
  padding: 0;
}
ul li{
  list-style: none;
}
a{
  text-decoration: none;
}
.banner {
  width: 1080px;
  height: 720px;
  margin: 20px auto;
  position: relative;
}
.bannerImg img{
  width: 1080px;
  height: 720px;
  z-index: 800;
  position: relative;
}
.bannerItems{
  position: absolute;
  z-index: 1000;
  bottom: 0;
  width: 100%;
  padding: 10px 0;
  display: flex;
  flex-direction: row;
  justify-content: center;
}
.bannerItems span{
  width: 30px;
  height: 30px;
  background:  rgba(100, 100, 100, .6);
  margin: 0 5px;
  border-radius: 50%;
  font-size: 16px;
  font-weight: bold;
  font-family: Microsoft YaHei;
  line-height: 30px;
  text-align: center;
  color: white;
  cursor: pointer;
}
.bannerItems span.active{
  background-color: red;
}
</style>
