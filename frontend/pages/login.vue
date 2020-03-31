<template>
  <a-layout class="layout" style="min-height: 100vh">
    <a-layout-content>
      <div class="area-login">
        <a-card title="Entrar no Sistema" :bordered="false">
          <a-form :form="form" class="login-form" @submit="postLogin">
            <a-form-item>
              <a-input v-decorator="['usernameOrEmail', { rules: [{ required: true, message: 'Digite o e-mail!' }] }]" type="email" placeholder="E-mail">
                <a-icon slot="prefix" type="user" style="color: rgba(0,0,0,.25)"/>
              </a-input>
            </a-form-item>
            <a-form-item>
              <a-input v-decorator="['password', { rules: [{ required: true, message: 'Digite a senha!' }] }]" type="password" placeholder="Senha">
                <a-icon slot="prefix" type="lock" style="color: rgba(0,0,0,.25)"/>
              </a-input>
            </a-form-item>
            <a-form-item>
              <a-button type="primary" html-type="submit" class="login-form-button">Entrar</a-button>
              <a class="login-form-forgot" href>Esqueci a Senha</a>
            </a-form-item>
          </a-form>
        </a-card>
      </div>
    </a-layout-content>
  </a-layout>
</template>
<script>
import Cookies from 'js-cookie'
import * as authService from '../service/auth'
import * as userService from '../service/user'
import jwt_decode from 'jwt-decode'
import Http from '../service/http'

export default {
  layout: 'blank',
  data() {
    return {
      collapsed: false
    };
  },
  beforeCreate() {
    this.form = this.$form.createForm(this, { name: 'normal_login' });
  },
  methods: {
    postLogin (e) {
      e.preventDefault();
      this.form.validateFields((err, values) => {
        if (!err) {
          authService.signin(values).then(response => {
            if (response.status == 200) {
              const data = response.data
              const auth = {
                accessToken: data.accessToken
              }
              const accessTokenDecode = jwt_decode(auth.accessToken)
              Http.defaults.headers.common = {'Authorization': `Bearer ${auth.accessToken}`}
              
              userService.byId(accessTokenDecode.sub).then(responseUser => {
                if (responseUser.status == 200) {
                  this.$store.commit('setAuth', auth)
                  Cookies.set('auth', auth)
                  const dataUser = responseUser.data
                  authService.setUser(dataUser, accessTokenDecode.exp)
                  this.$router.push('/')
                } else {
                  this.$message.error('Erro ao buscar os dados do usuário.')
                }     
              }) 
            }
          })
        } else {
          this.$message.error('Preencha os dados do login corretamente.')
        }
      })
    }
  }
};
</script>

<style>
.area-login {
  background: #fff;
  width: 350px;
  height: 350px;
  margin: 150px auto;
  -webkit-box-shadow: 2px 3px 2px -1px rgba(0, 0, 0, 0.1);
  -moz-box-shadow: 2px 3px 2px -1px rgba(0, 0, 0, 0.1);
  box-shadow: 2px 3px 2px -1px rgba(0, 0, 0, 0.1);
  border: 2px #f1f0f5 solid;
  border-radius: 3px 3px 3px 3px;
  -moz-border-radius: 3px 3px 3px 3px;
  -webkit-border-radius: 3px 3px 3px 3px;
}

.login-form-forgot {
  float: right;
}
.login-form-button {
  width: 100%;
}
</style>
