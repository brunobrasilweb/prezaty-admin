<template>
  <a-layout style="min-height: 100vh">
    <a-layout-sider
      theme="light"
      class="sider"
      breakpoint="lg"
      collapsedWidth="0"
      @collapse="onCollapse"
      @breakpoint="onBreakpoint"
    >
      <div class="logo"/>
      <a-menu theme="light" :defaultSelectedKeys="['1']" mode="inline">
        <a-menu-item key="1">
          <a-icon type="dashboard"/>
          <span>Dashboard</span>
        </a-menu-item>
        <a-menu-item key="3">
          <a-icon type="shop"/>
          <span>Cliente</span>
        </a-menu-item>
        <a-sub-menu key="2">
          <span slot="title">
            <a-icon type="lock"/>
            <span>Permissões</span>
          </span>
          <a-menu-item key="setup:1">Usuários</a-menu-item>
          <a-menu-item key="setup:2">Grupos de Usuários</a-menu-item>
        </a-sub-menu>
      </a-menu>
    </a-layout-sider>
    <a-layout class="layout">
      <a-layout-header class="header">
        <a-menu class="menu-account" v-model="current" :style="{ lineHeight: '64px' }" mode="horizontal">
          <a-sub-menu>
            <span slot="title" class="submenu-title-wrapper">
              <a-icon type="user" /> Bruno Brasil
            </span>
            <a-menu-item-group>
              <a-menu-item key="setting:1">Minha Conta</a-menu-item>
              <a-menu-item key="setting:3">Sair</a-menu-item>
            </a-menu-item-group>
          </a-sub-menu>
        </a-menu>
      </a-layout-header>
      <a-layout-content class="content">
        <div class="box-content">
          <a-row>
            <a-col :xs="24" :sm="20" :md="21" :lg="21" :xl="21"><h1>Título da Página</h1></a-col>
            <a-col :xs="24" :sm="24" :md="3" :lg="3" :xl="3" class="buttons-page">
              <a-dropdown>
                <a-menu slot="overlay" @click="handleMenuClick">
                  <a-menu-item key="1">1st item</a-menu-item>
                  <a-menu-item key="2">2nd item</a-menu-item>
                  <a-menu-item key="3">3rd item</a-menu-item>
                </a-menu>
                <a-button block> Actions <a-icon type="down" /> </a-button>
              </a-dropdown>
            </a-col>
          </a-row>
          <a-row>
            <a-col :span="24"></a-col>
          </a-row>
        </div>
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>
<script>
export default {
  data() {
    return {
      collapsed: false
    };
  },
  methods: {
    onCollapse(collapsed, type) {
      console.log(collapsed, type);
    },
    onBreakpoint(broken) {
      console.log(broken);
    }
  }
};
</script>

<style>
.layout {
  background: #F2F5FA;
}

h1 {
  color: #9094A0;
  text-transform: uppercase;
  font-size: 18px;
}

.buttons-page {
  text-align: right;
}

.box-content {
  padding: 24px;
  background: #fff;
  min-height: 360px;
  border: 2px #F1F0F5 solid;
  border-radius: 3px 3px 3px 3px;
  -moz-border-radius: 3px 3px 3px 3px;
  -webkit-border-radius: 3px 3px 3px 3px;
}

.menu-account {
  text-align: right;
  border: none;
}

.menu-account li {
  border-left: 1px #F0F0F0 solid;
}

.logo {
  height: 32px;
  background: #F0F0F0;
  margin: 16px;
}

.content {
  margin: 24px 16px 0;
}

.header {
  background: #fff;
  padding: 0;
}

.header, .sider {
  -webkit-box-shadow: 2px 3px 2px -1px rgba(0,0,0,0.1);
  -moz-box-shadow: 2px 3px 2px -1px rgba(0,0,0,0.1);
  box-shadow: 2px 3px 2px -1px rgba(0,0,0,0.1);
}

.ant-layout-sider-zero-width-trigger {
    top: 11px;
}
</style>
