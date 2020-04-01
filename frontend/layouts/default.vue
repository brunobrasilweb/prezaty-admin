<template>
  <div>
    <a-locale-provider :locale="locale">
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
              <span><NLink to="/">Dashboard</NLink></span>
            </a-menu-item>
            <a-sub-menu key="2">
              <span slot="title">
                <a-icon type="lock"/>
                <span>Permissões</span>
              </span>
              <a-menu-item key="setup:1"><NLink to="/user">Usuários</NLink></a-menu-item>
            </a-sub-menu>
          </a-menu>
        </a-layout-sider>
        <a-layout class="layout">
          <a-layout-header class="header">
            <a-menu class="menu-account" :style="{ lineHeight: '64px' }" mode="horizontal">
              <a-sub-menu>
                <span slot="title" class="submenu-title-wrapper">
                  <a-icon type="user" /> {{ loggedInUser.name }}
                </span>
                <a-menu-item-group>
                  <a-menu-item key="setting:1"><NLink to="/my-account">Minha Conta</NLink></a-menu-item>
                  <a-menu-item @click="logout" key="setting:3">Sair</a-menu-item>
                </a-menu-item-group>
              </a-sub-menu>
            </a-menu>
          </a-layout-header>
          <a-layout-content class="content">
            <nuxt />
          </a-layout-content>
        </a-layout>
      </a-layout>
    </a-locale-provider>  
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import ptBR from 'ant-design-vue/lib/locale-provider/pt_BR'

export default {
  data() {
    return {
      collapsed: false,
      locale: ptBR,
    };
  },
  methods: {
    onCollapse(collapsed, type) {
      console.log(collapsed, type)
    },
    onBreakpoint(broken) {
      console.log(broken)
    },
    logout () {
      this.$auth.logout();
      this.$router.push('login')
    }
  },
  computed: {
    ...mapGetters(['loggedInUser']),
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

.gutter-row {
  padding-right: 10px;
}
</style>
