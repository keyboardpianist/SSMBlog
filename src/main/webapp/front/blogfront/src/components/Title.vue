<template>
  <div class="blog-title">
    <h1 v-if="h1data==='deltaplus'"><a class="header_a" href="https://github.com/keyboardpianist?tab=repositories">{{ h1data }}</a></h1>
    <h1 v-else>{{ h1data }}</h1>
    <h2 v-if="h1data==='deltaplus'">{{ h2data }}</h2>
  </div>
</template>

<script>
    export default {
        name: "Title",
        props: ['h1data', 'h2data'],
    }
</script>

<style scoped>
  .blog-title{
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
    background-color: #222222;
  }

  .blog-title > h1{
    color:white;
    font-size: 4rem;
    margin: 10px 0;
  }

  .blog-title > h2{
    color:rgba(255,255,255,0.8);
    font-size: 1.8rem;
  }

  .header_a{
    text-decoration: none;
    color: white;
  }
</style>
