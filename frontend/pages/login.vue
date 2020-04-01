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
export default {
  middleware: 'guest',
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
          try {
            this.$auth.loginWith('local', {
              data: values,
            }).then(response => {
              if (response.status == 200) {
                this.$router.push('/')
              } else {
                this.$message.error('Dados incorretos. Tente novamente.')
              }
            }).catch(error => {
                this.$message.error('Dados incorretos. Tente novamente.')
            });
          } catch (e) {
            this.$message.error('Não foi possível fazer o login. ' + e.response.data.message)
          }
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
