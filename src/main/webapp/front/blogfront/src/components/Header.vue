<template>
  <div class="blog-header">
    <div class="blog-nav">
      <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect"
               background-color="#333333"
               text-color="#fff"
               active-text-color="#ffd04b"
               style="height: 60px"
      >
        <el-menu-item index="1">SSMBlog</el-menu-item>
        <el-submenu index="2">
          <template slot="title">类别</template>
          <el-menu-item v-for="(tName,index) in types" :key="index" :index="'2-' + tName">选项{{tName}}</el-menu-item>
        </el-submenu>
        <el-menu-item index="3" style="float: right">上传</el-menu-item>
        <el-menu-item index="4" style="float: right">写博客</el-menu-item>
        <!--div style="height:100%;float: right;margin:0 20px">
          <div style="display: flex; align-items: center; justify-content: center; height: 100%;">
            <a href="javascript:void(0)" class="header_a" >上传</a>
          </div>
        </div>
        <div style="height:100%;float: right;margin:0 20px">
          <div style="display: flex; align-items: center; justify-content: center; height: 100%;">
            <a href="javascript:void(0)" class="header_a" >写博客</a>
          </div>
        </div-->
      </el-menu>
    </div>
  </div>
</template>

<script>
    export default {
        name: "Header",
        data()
        {
          return {
            activeIndex: "1",
            types: 5,
          }
        },
        methods:{
          handleSelect(key, keyPath)
          {
            switch (key) {
              case '3':
                this.$emit('Upload');
                break;
              default :
                break;
            }
          }
        }
    }
</script>

<style scoped>
    .blog-header{
        height: 100%;
        width: 100%;
    }

    .el-menu.el-menu--horizontal{
      border-bottom: none;
    }

    .header_a{
        text-decoration: none;
        color: white;
    }
</style>
