<template>
  <div class="box-content">
    <a-row>
      <a-col :xs="24" :sm="20" :md="21" :lg="21" :xl="21"><h1>Título da Página</h1></a-col>
      <a-col :xs="24" :sm="24" :md="3" :lg="3" :xl="3" class="buttons-page">
        <a-dropdown>
          <a-menu slot="overlay">
            <a-menu-item key="1">1st item</a-menu-item>
            <a-menu-item key="2">2nd item</a-menu-item>
            <a-menu-item key="3">3rd item</a-menu-item>
          </a-menu>
          <a-button block> Actions11 <a-icon type="down" /> </a-button>
        </a-dropdown>
      </a-col>
    </a-row>
    <a-row>
      <a-col :span="24"></a-col>
    </a-row>
  </div>
</template>

<script>
export default {
  layout: 'default'
}
</script>
