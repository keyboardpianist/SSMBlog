<template>
  <el-dialog
    title="上传"
    :visible.sync="visible"
    width="30%"
    :destroy-on-close="true"
    @close="handleClose"
  >
    <div>
      <input id="uploadFile" type="file" multiple
             @change="uploadData"
             ref="singleInputer"
             style="display:none"
      >
      <el-button size="small" type="primary" @click="showFile">上传文件</el-button>
      <input id="uploadDir" type='file' webkitdirectory
             @change="uploadDirData"
             ref="inputer"
             style="display:none"
      >
      <el-button style="margin-left: 10px;" size="small" type="primary" @click="showDir">上传文件夹</el-button>
    </div>
  </el-dialog>
</template>

<script>
  import $ from 'jquery';

  export default {
    name: "Upload",
    props: ['dialogVisible'],
    data(){
      return{
        fileList: [],
        files: [],
        fileName: '',
        progress: false,
      }
    },
    computed:{
      visible:{
        get(){
          return this.dialogVisible;
        },
        set(){
          return this.dialogVisible;
        }
      }
    },
    methods:{
      showFile()
      {
        $('#uploadFile').click();
      },
      uploadData()
      {
        let _this = this;
        let inputDOM = _this.$refs.singleInputer;
        let file = inputDOM.files;

        let fileCount = file.length;
        if (fileCount > 10)
        {
          this.$message.warning("超出上传数量限制，当前选中了" + fileCount + "个文件");
          return;
        }

        let formData = new FormData();
        for (let i = 0; i < file.length; i++) {
          formData.append("file[]", file[i]);
        }

        $.ajax({
          url: '/SSMBlog/Upload',
          type: "POST",
          data: formData,
          processData: false,
          contentType: false,
          success: function(data) {
            if (data.code == 200)
            {
              _this.$message({
                message: '上传成功',
                type: 'success',
              });
              _this.handleSuccess();
            }
            else
            {
              _this.$message({
                message: '上传失败',
                type: 'error',
              });
            }
          },
          error: function(error){
            _this.$message({
              message: '上传失败',
              type: 'error',
            });
          }
        });
      },
      uploadDirData()
      {
        let _this = this;
        let inputDOM = _this.$refs.inputer;
        let file = inputDOM.files;

        let fileCount = file.length;
        if (fileCount > 10)
        {
          this.$message.warning("超出上传数量限制，当前选中了" + fileCount + "个文件");
          return;
        }

        let formData = new FormData();
        for (let i = 0; i < file.length; i++) {
          formData.append("file[]", file[i]);
        }

        $.ajax({
          url: '/SSMBlog/Upload',
          type: "POST",
          data: formData ,
          processData: false,
          contentType: false,
          success: function(data) {
            if (data.code == 200)
            {
              _this.$message({
                message: '上传成功',
                type: 'success',
              });
              _this.handleSuccess();
            }
            else
            {
              _this.$message({
                message: '上传失败',
                type: 'error',
              });
            }
          },
          error: function(error){
            _this.$message({
              message: '上传失败',
              type: 'error',
            });
          }
        });
      },
      showDir()
      {
        $('#uploadDir').click();
      },
      handleSuccess(response, file, fileList)
      {
        this.progress = false;
        this.$emit('uploadSubmit');
      },
      handleClose()
      {
        this.$emit('uploadClose');
      },
    }
  }
</script>

<style scoped>

</style>
