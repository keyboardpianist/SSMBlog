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
            paperDetail: '# java学习（一）\n' +
              '\n' +
              '今天继续开始系统学习java，希望能坚持下去，打卡。。。\n' +
              '\n' +
              '### java开发环境\n' +
              '\n' +
              '1. #### 运行环境JRE\n' +
              '\n' +
              '   `各种API以及JVM`\n' +
              '\n' +
              '2. #### 开发环境JDK\n' +
              '\n' +
              '   `编译器（javac命令）`\n' +
              '\n' +
              '   `JDK包含JRE`\n' +
              '\n' +
              '   `JDK包含JRE`\n' +
              '\n' +
              '   `JDK包含JRE`\n' +
              '\n' +
              '   重要的事情说三遍\n' +
              '\n' +
              '### java运行机制\n' +
              '\n' +
              '先编译为字节码（*.class）后在JVM上解释\n' +
              '\n' +
              '### java安装配置过程\n' +
              '\n' +
              '默认安装到`C:\\Program Files\\Java`路径下，据说安装在有空格的路径下会导致一些未知的问题，\n' +
              '\n' +
              '我决定试试看看有什么未知的问题\n' +
              '\n' +
              '----------------------------------\n' +
              '\n' +
              '运行批处理文件会提示 `C:\\Program` 不是内部或外部命令，那么就把环境变量中的`Program Files`设置为`PROGRA~1`\n' +
              '\n' +
              '#### 关于PROGRA~1\n' +
              '\n' +
              '> 文件夹（sub-directry)名称，以前是不允许带空白的，后来允许带空白，但由于有了空白，许多命令出现二义性，于是采用双引号括起来的办法。例如：\n' +
              '>\n' +
              '> cd Documents and Settings\n' +
              '> 按老定义 等于 CD Documents, CD 命令找不到名叫Documents 的 directry \n' +
              '>\n' +
              '> 于是采用双引号：\n' +
              '> cd “Documents and Settings“\n' +
              '>\n' +
              '> 但用到 set PATH 时很麻烦，名字太长，双引号时常括错。于是采用8个字符缩写，即写头六个字母(略去空白），另加波浪号和1。例如：\n' +
              '>\n' +
              '> "Documents and Settings“ --  DOCUME~1 \n' +
              '> "Local Settings" -- LOCALS~1   （注意略去空白，用了第二个词的字母，凑成六个，再加波浪号和1）。\n' +
              '\n' +
              '### 编译java程序\n' +
              '\n' +
              '`javac -d destdir sourceFile`\n' +
              '\n' +
              '一般而言在当前路径下可以用`javac -d . sourceFile`  其中sourceFile指的是`.java`文件\n' +
              '\n' +
              '生成`.class`文件\n' +
              '\n' +
              '### 运行java程序\n' +
              '\n' +
              '`java .class文件名`\n' +
              '\n' +
              '可以在`.class文件名`前加入`-classpath`选项以指定JRE搜索目录\n' +
              '\n' +
              '并且可以通过`%CLASSPATH%`来引用该环境变量\n' +
              '\n' +
              '#### 历史\n' +
              '\n' +
              '1.4JDK版本以前需要在CLASSPATH环境变量中添加`.`以告诉JRE运行环境。\n' +
              '\n' +
              '1.4版本以前的需要设置CLASSPATH环境变量值为\n' +
              '\n' +
              '`.;%JAVA_HOME%\\lib\\dt.jar;%JAVA_HOME%\\lib\\tools.jar` ,`%JAVA_HOME%`代表JDK安装目录\n' +
              '\n' +
              '\n' +
              '\n',
          }
        },
        mounted(){
          this.paperDetail = Marked(this.paperDetail);
          /*this.getPaperDetail();*/
        },
        methods:{
          getPaperDetail()
          {
            let _this = this;
            $.ajax({
              data: "",
              type: "POST",
              url: "Article.do/" + _this.$route.params.pid,
              success(result)
              {
                _this.paperDetail = Marked(result);
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
