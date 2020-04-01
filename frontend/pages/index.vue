<template>
  <div class="box-content">
    <a-row>
      <a-col :xs="24" :sm="20" :md="21" :lg="21" :xl="21"><h1>Dashboard</h1></a-col>
    </a-row>
    <a-row>
      <a-col :span="24"></a-col>
    </a-row>
  </div>
</template>

<script>
export default {
  middleware: 'auth',
  layout: 'default'
}
</script>
