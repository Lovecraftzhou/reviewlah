<script lang="ts">
import {defineComponent} from 'vue'
import {getMerchantDeatail} from "@/api/merchant";
import {getComments} from "@/api/review";

export default defineComponent({
  name: "MerchantDetailView",
  data(){
    return{
      userId:"",
      merchant:{
        address_code: "119123",
        user_id: 18,
        dish: [],
        name: "E2",
        diningComment: [
          {
            dining_com_id: 4,
            merchant_id: 12,
            customer_id: 2,
            content: "So Great",
            rate: 5,
            time_dc: "2023-10-15 16:45:20",
            pic_dc: "http://respic3"
          }
        ],
        avator: "http://pic3",
        avg_rate: 5.0,
        phone_number: "00000000",
        category: [
          "HotPot"
        ],
        announcement: []
      },
      slideList:[
        {
          image:"https://picsum.photos/800/300?random=1",
          desc:"第一张照片",
          clickURL:"https://picsum.photos/800/300?random=1"
        },
        {
          image:"https://picsum.photos/800/300?random=2",
          desc:"第二张照片",
          clickURL:"https://picsum.photos/800/300?random=2"
        },
        {
          image:"https://picsum.photos/800/300?random=3",
          desc:"第三张照片",
          clickURL:"https://picsum.photos/800/300?random=3"
        },
        {
          image:"https://picsum.photos/800/300?random=4",
          desc:"第四张照片",
          clickURL:"https://picsum.photos/800/300?random=4"
        },
        {
          image:"https://picsum.photos/800/300?random=5",
          desc:"第五张照片",
          clickURL:"https://picsum.photos/800/300?random=5"
        },
        {
          image:"https://picsum.photos/800/300?random=6",
          desc:"第六张照片",
          clickURL:"https://picsum.photos/800/300?random=6"
        }
      ],
      currentIndex: 0, // 当前显示的图片的索引
      timer: 1000, // 自动切换的定时器
      posts:[],
      //[{"dining_com_id":4,"pic_dc":"","rate":5,"name":"zzh","avator":"","content":"So Great","time_dc":"2023-10-15T16:45:20"}]
      postDetailVisible:false,
      postDetail:{},
      merchantList:[
        {
          "address_code": "127371",
          "user_id": 8,
          "name": "wxf",
          "avator": "D://pic/reviewlah.jpg",
          "avg_rate": 0,
          "category": [
            "BBQ",
            "HotPot"
          ]
        },
        {
          "address_code": "119077",
          "user_id": 11,
          "name": "九里香",
          "avator": "http://pic3",
          "avg_rate": 0,
          "category": [
            "HotPot",
            "BBQ"
          ]
        },
        {
          "address_code": "119123",
          "user_id": 18,
          "name": "E2",
          "avator": "http://pic3",
          "avg_rate": 5,
          "category": [
            "HotPot"
          ]
        }
      ],
    }
  },
  watch: {
    $route() {
      let userId = this.$route.query && this.$route.query.userId
      if (typeof userId === "string"){
        this.userId = userId
      }
    }
  },
  created() {
    let userId = this.$route.query.userId
    console.log(userId)
    if (typeof userId === "string"){
      this.userId = userId
      getMerchantDeatail(userId).then((res:any)=>{
        this.merchant = res.list

      })
    }
    getComments(userId).then((res:any)=>{
      this.posts=res.list
    })
  },
  methods:{
    play() {
      this.timer = setInterval(() => {
        this.currentIndex++;
        if (this.currentIndex >= this.slideList.length) {
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
    change(index:any){
      this.currentIndex = index;
    },
  },

})
</script>

<template>
  <div class="main_container">
    <el-row style="padding: 0 16px 0 16px;">
      <el-col
          v-for="(o) in 1"
          :key="o"
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
      <el-col :span="2"></el-col>
      <el-col
          :span="14"
          :offset="2"
      >
        <el-card :body-style="{ padding: '16px' }" style="height: 100%">
          <h1 style="margin:20px">Announcement</h1>
          <p>{{merchant.announcement[0]?merchant.announcement[0].content:'none'}}</p>
        </el-card>
      </el-col>
    </el-row>
    <div class="area_title" style="text-align: center;margin-top: 48px !important;">
      <h2 class="css-rlqqlq">Dishes</h2>
    </div>
    <div style="width: 100%;padding: 16px">
      <div id="box">
        <div class="banner">
          <!--切换图片-->
          <div class="bannerImg">
            <transition-group name="fade" tag="ul" class="slideUl">
              <li v-for="(list, index) in slideList" :key="index" v-show="index===currentIndex" @mouseenter="stop">
                <a :herf="list.clickURL">
                  <img :src="list.image" :alt="list.desc">
                </a>
              </li>
            </transition-group>
          </div>
          <!--切换小按钮-->
          <div class="bannerItems">
            <span v-for="(item,index) in slideList.length" :key="index" :class="{'active':index===currentIndex}" @click="change(index)">{{index+1}}</span>
          </div>
        </div>
      </div>
    </div>
    <div class="area_title" style="text-align: center;margin-bottom: 48px !important;">
      <h2 class="css-rlqqlq">Comments</h2>
    </div>
    <div class="posts_contaner">
      <div class="post_container" v-for="(post,index) in posts" :key="index">
        <div class="post_content">
          <div class="post_header">
            <div class="post_avatar_container">
              <div class="post_avatar_link">
                <img class="post_avatar"
                     :src="post.avator"
                     alt="Avatar" height="40" width="40"
                     loading="lazy"
                     draggable="true">
              </div>
            </div>
            <div class="post_author_container">
              <span class="post_author" data-font-weight="bold">
                {{ post.name }}
              </span>
              <div class="post_time_container">
                <span class="post_time">{{post.time_dc}}</span>
              </div>
            </div>
          </div>
          <div class="post_title_container">
            <p class="post_title" data-font-weight="bold">
              <text class="post_title_link">{{post.title}}</text>
            </p>
          </div>
          <div class="post_bottom">
            <div class="pic" style="height: 200px">
              <div class="post_pic">
                <img class="post_pic_img" :src="post.content"
                     alt="" loading="eager" draggable="true">
              </div>
            </div>
            <el-rate
                v-model="post.rate"
                disabled
                show-score
                text-color="#ff9900"
                score-template="{value} points"
                style="margin-top: 10px"
            />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.time {
  font-size: 12px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.image {
  width: 100%;
  display: block;
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
</style>