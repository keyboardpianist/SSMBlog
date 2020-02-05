<template>
    <div class="blog-list">
      <el-card v-for="(item, index) in paperData" :key="index">
        <div slot="header">
          <span class="paper-title" ><a href="javascript:void(0)" class="list_a" @click="jumpToDetail(item.id, item.title)">{{item.title}}</a></span>
        </div>
        <div class="blog-abstract" v-html="item.abs">
          {{item.abs}}
        </div>
        <div class="paper-tail">
          posted @ {{item.time}} <a href="/">deltaplus</a>
        </div>
      </el-card>
    </div>
</template>

<script>
    import Marked from 'marked';
    import $ from 'jquery';
    export default {
        name: "List",
        data(){
          return {
            /*paperData: [{id: 1, title: 'tst1', abs:
                'java学习（一）\n' +
                '\n' +
                '今天继续开始系统学习java，希望能坚持下去，打卡。。。\n' +
                '\n' +
                'java开发环境\n' +
                '\n' +
                '1. 运行环境JRE\n' +
                '   各种API以及JVM\n' +
                '2. 开发环境JDK\n' +
                '   编译器（javac命令）\n' +
                '   JDK包含JRE\n' +
                '   JDK包含JRE\n' +
                '   JDK包含JRE\n' +
                '   重要的事情说三遍\n' +
                '\n' +
                'java运行机制\n', time: '2019-01-08 01:02', category: 'Java'},
              {id: 2, title: 'tst2', abs: 'abs2', time: '2019-01-08 01:02', category: 'Java'},
              {id: 3, title: 'tst3', abs: 'abs3', time: '2019-01-08 01:02', category: 'Java'},
              {id: 4, title: 'tst4', abs: 'abs4', time: '2019-01-08 01:02', category: 'Java'},
              {id: 5, title: 'tst1', abs: 'abs1', time: '2019-01-08 01:02', category: 'Java'},
              {id: 6, title: 'tst2', abs: 'abs2', time: '2019-01-08 01:02', category: 'Java'},
              {id: 7, title: 'tst3', abs: 'abs3', time: '2019-01-08 01:02', category: 'Java'},
              {id: 8, title: 'tst4', abs: 'abs4', time: '2019-01-08 01:02', category: 'Java'},
            ]*/
            paperData: [],
          }
        },
        mounted() {
          this.getPaperList();
        },
        methods:{
          getPaperList(){
            let _this = this;
            $.ajax({
              data: "",
              type: "POST",
              url: "/SSMBlog/List",
              success(list)
              {
                  _this.paperData = [];
                  for (let i in list)
                  {
                    _this.paperData.push({id: list[i].aid, title: list[i].title,
                      abs: list[i].article_abstract, time: new Date(list[i].createtime)});
                  }
                  _this.transferMarkdown();
              },
              error()
              {
                _this.$message({
                  message: '博文加载失败',
                  type: 'error',
                });
              }
            })
          },
          transferMarkdown(){
            for (let i = 0;i < this.paperData.length;i++)
            {
              this.paperData[i].abs = Marked(this.paperData[i].abs);
              this.paperData[i].abs = this.paperData[i].abs.replace(/<\/?[^>]*>/g,'');/*markdown先转html再消去html标签*/
            }
          },
          jumpToDetail(id, title)
          {
            this.$emit("getDetail", {id: id, title: title});
          }
        }
    }
</script>

<style scoped>
  >>> .blog-abstract > p{
    margin: 0;
  }

  .blog-list{
    height: 100%;
    width: auto;
    margin: auto;
    padding: 40px 80px;
    overflow: hidden;
  }

  .el-card{
    height: 25vh;
    width: auto;
    margin: 30px;
  }

  >>> .el-card__body{
    height: 51%;
  }

  .paper-title{
    font-size: 2rem;
  }

  .blog-abstract{
    height: 80%;
    color: #3a4145;
    word-wrap: break-word;
    word-break: break-all;
    overflow: hidden;
    line-height: 1.5;
    font-size: 16px;
  }

  .paper-tail{
    height: 20%;
  }

  .list_a{
    text-decoration: none;
    color: black;
  }
</style>
