<template>
  <div class="box-content">
    <a-row>
      <a-col :xs="24" :sm="20" :md="21" :lg="21" :xl="21"><h1>Clientes</h1></a-col>
      <a-col :xs="24" :sm="24" :md="3" :lg="3" :xl="3" class="buttons-page">
        <a-dropdown>
          <a-menu slot="overlay">
            <a-menu-item key="1">Cadastrar</a-menu-item>
          </a-menu>
          <a-button block>Ir para: <a-icon type="down" /> </a-button>
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
