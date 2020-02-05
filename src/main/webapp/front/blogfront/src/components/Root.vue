<template>
    <el-container>
      <el-header>
        <Header
          @Upload="handleUpload"
        />
      </el-header>
      <el-main>
        <div class="blog-title">
          <Title :h1data="h1Str" :h2data="h2Str"/>
        </div>
        <div class="blog-content">
          <keep-alive>
            <router-view
              ref="routerView"
              @getDetail="showDetail"
              @paperChangeTitle="changeTitle"
            />
          </keep-alive>
          <UploadDialog :dialogVisible="uploadDialog"
                        @uploadCancle="handleUploadCancle"
                        @uploadSubmit="handleUploadSubmit"
                        @uploadClose="handleUploadClose"
          />
        </div>
      </el-main>
    </el-container>
</template>

<script>
    import Vue from 'vue';
    import ElementUI from 'element-ui';
    import 'element-ui/lib/theme-chalk/index.css';
    import Header from "./Header";
    import Title from "./Title";
    import List from "./List";
    import Paper from "./Paper";
    import UploadDialog from "./UploadDialog";
    Vue.use(ElementUI)

    export default {
        name: "Root",
        components: {Header, Title, List, Paper, UploadDialog},
        data(){
          return {
            h1Str: 'deltaplus',
            h2Str: 'see what you want to see',
            uploadDialog: false,
          }
        },
        watch:{
          $route(to, from)
          {
            if (to.path == '/')
            {
              this.h1Str = 'deltaplus';
              this.h2Str = 'see what you want to see';
            }
          }
        },
        methods:{
          showDetail(info)
          {
            this.h1Str = info.title;
            this.$router.push({path:'/detail/' + info.id});
          },
          changeTitle(title)
          {
            this.h1Str = title;
          },
          handleUpload(){
            this.uploadDialog = true;
          },
          handleUploadCancle()
          {
            this.uploadDialog = false;
          },
          handleUploadSubmit()
          {
            this.uploadDialog = false;
            this.$router.push({path:'/list'});
            this.$refs.routeView.getPaperList();
          },
          handleUploadClose()
          {
            this.uploadDialog = false;
          },
        }
    }
</script>

<style>
  body {
    font-size: 14px;
    font-weight: normal;
    margin: 0;
    padding: 0;
    background-color: #f0f2f5;
  }

   code{
     color: #c7254e;
     border-radius: 2px;
   }

  pre {
    background-color: #2d2d2d!important;
    color: #ccc!important;
  }

  .blog-content > pre > code{
    background-color: #2d2d2d!important;
    color: #ccc!important;
  }
</style>

<style scoped>
  .el-container{
    height:100vh;
    padding:0;
    margin:0;
    width:100vw;
  }

   .el-header{
      width: 100vw;
      height: 60px !important;
      padding: 0;
      margin: auto;
      background-color: #222222;
      border-bottom: solid 1px #d3e2ec;
  }

  .el-main{
    padding:0;
    /*padding-top:0px;
    padding-bottom: 0px;*/
    margin:auto;
    width:100vw;
    height: 100%;
    overflow-x: hidden;
    background-color: #f0f2f5;
  }

  .blog-title{
    width: 100vw;
    height: 45vh;
  }

  .blog-content{
    height: auto;
    width: 60vw;
    overflow: hidden;
    background-color: #ffffff;
    margin: auto;
  }
</style>
