<template>
  <div class="blog-detail">
    <el-card>
      <div class="blog-content" v-html="paperDetail">
        {{paperDetail}}
      </div>
    </el-card>
  </div>
</template>

<script>
    import Marked from "marked";
    import $ from 'jquery';

    export default {
        name: "Paper",
        data(){
          return{
            /*paperDetail: '# java学习（一）\n' +
              '\n' +
              '今天继续开始系统学习java，希望能坚持下去，打卡。。。\n',*/
            paperDetail: '',
          }
        },
        mounted(){
          this.getPaperDetail();
        },
        watch:{  /*要想实现路由切换马上调用某个方法只能采用watch*/
          $route(to, from){
            if (to.path.search(/\/detail/i) >= 0)
            {
              this.getPaperDetail();
            }
          }
        },
        methods:{
          getPaperDetail()
          {
            let _this = this;
            $.ajax({
              data: "",
              type: "POST",
              url: "/SSMBlog/Article/" + _this.$route.params.pid,
              success(result)
              {
                _this.paperDetail = Marked(result.articleDetail.detail);
                _this.$emit('paperChangeTitle', result.title);
              },
              error()
              {
                _this.$message({
                  message: '博文加载失败',
                  type: 'error',
                });
              }
            })
          }
        }
    }
</script>

<style scoped>
  .blog-detail{
    height: 100%;
    width: auto;
    margin: auto;
    padding: 40px 80px;
    overflow: hidden;
  }

  .el-card{
    height: auto;
    width: auto;
    margin: 30px;
  }

  >>> .el-card__body{
    height: 51%;
  }

  .blog-content{
    height: 80%;
    color: #3a4145;
    word-wrap: break-word;
    word-break: break-all;
    overflow: hidden;
    line-height: 1.5;
    font-size: 16px;
  }
</style>
