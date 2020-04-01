<template>
  <div class="box-content">
    <a-modal
      title="Cadastro de Usuário"
      width="800px"
      :visible="visibleModalForm"
      @ok="save"
      okText="Salvar"
      :maskClosable="false"
      :confirmLoading="confirmLoadingModalForm"
      @cancel="cancelModalForm"
    >
      <a-form :form="form" class="login-form">
        <a-row>
          <a-col class="gutter-row" :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
            <a-form-item label="Nome">
              <a-input
                v-decorator="[
                  'name',
                  {
                    rules: [{ required: true, message: 'Preencha o nome' }],
                  },
                ]"
              />
            </a-form-item>
          </a-col>
          <a-col class="gutter-row" :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
            <a-form-item label="E-mail">
              <a-input type="email"
                v-decorator="[
                  'email',
                  {
                    rules: [{ required: true, message: 'Preencha o e-mail' }],
                  },
                ]"
              />
            </a-form-item>
          </a-col>
          <a-col v-if="id == null" class="gutter-row" :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
            <a-form-item label="Senha">
              <a-input type="password"
                v-decorator="[
                  'password',
                  {
                    rules: [{ required: true, message: 'Preencha a senha' }],
                  },
                ]"
              />
            </a-form-item>
          </a-col>
          <a-col v-if="id != null" class="gutter-row" :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
            <a-form-item label="Nova Senha">
              <a-input type="newPassword" v-decorator="['newPassword']" />
            </a-form-item>
          </a-col>
        </a-row>
      </a-form>
    </a-modal>
    <a-row>
      <a-col :xs="24" :sm="20" :md="21" :lg="21" :xl="21"><h1>Usuários</h1></a-col>
      <a-col :xs="24" :sm="24" :md="3" :lg="3" :xl="3" class="buttons-page">
        <a-button @click="addRow()" type="primary">Cadastrar Usuário</a-button>
      </a-col>
    </a-row>
    <a-row>
      <a-col :span="24">
        <a-table
          :columns="columns"
          :dataSource="rows"
          :pagination="pagination"
          :loading="loading"
          @change="handleTableChange"
        >
         <template slot="action" slot-scope="text, record">
          <a-icon @click="editRow(record)" style="color: #333" type="edit" />
          <a-popconfirm v-if="rows.length"
            title="Deseja realmente excluir o registro?"
            @confirm="() => deleteRow(record.id)">
            <a href="javascript:;"><a-icon style="color: #333" type="delete" /></a>
          </a-popconfirm>
        </template>
        </a-table>
      </a-col>
    </a-row>
  </div>
</template>

<script>

export default {
  middleware: 'auth',
  layout: 'default',
  mounted() {
    this.list()
  },
  data() {
    return {
      id: null,
      rows: [],
      form: this.$form.createForm(this, { name: 'user_form' }),
      pagination: {},
      loading: false,
      selectedRowKeys: [],
      visibleModalForm: false,
      confirmLoadingModalForm: false,
      columns: [
        {
          title: 'Nome',
          dataIndex: 'name',
          width: '40%',
        },
        {
          title: 'E-mail',
          dataIndex: 'email',
          width: '20%',
        },
        {
          title: 'Ação',
          dataIndex: 'action',
          scopedSlots: { customRender: 'action' },
        },
      ]
    }
  },
  methods: {
    handleTableChange(pagination, filters, sorter) {
      const pager = { ...this.pagination }
      pager.current = pagination.current
      this.pagination = pager
      this.list({
        results: pagination.pageSize,
        page: pagination.current,
        sortField: sorter.field,
        sortOrder: sorter.order,
        ...filters,
      })
    },
    async list(params = {}) {
      const pagination = { ...this.pagination };
      const response = await this.$axios.$get('/user')
      this.rows = response.content
      pagination.total = response.totalPages
      this.pagination = pagination
      this.loading = false
    },
    async deleteRow(id) {
      const rowsSource = this.rows
      this.rows = rowsSource.filter(item => item.id !== id)
      await this.$axios.$delete('/user/' + id)
    },
    save (e) {
      e.preventDefault();
      this.form.validateFields((err, values) => {
        if (!err) {
          this.confirmLoadingModalForm = true
          if (this.id == null) {
            this.$axios.$post('/user', values).then(response => {
              this.visibleModalForm = false
              this.confirmLoadingModalForm = false
              this.list()
              this.form.resetFields()
              this.id = null
              this.$message.success('Usuário cadastrado com sucesso.')
            }).catch(error => {
              this.$message.error(error.response.data.message)
              this.confirmLoadingModalForm = false   
            });
          } else {
            this.$axios.$put('/user/' + this.id, values).then(response => {
              this.visibleModalForm = false
              this.confirmLoadingModalForm = false
              this.list()
              this.id = null
              this.form.resetFields()
              this.$message.success('Usuário editado com sucesso.')
            }).catch(error => {
              this.$message.error(error.response.data.message)
              this.confirmLoadingModalForm = false   
            });  
          }
        }
      })
    },
    addRow() {
      this.showModalForm()
    },
    async editRow(record) {
      this.showModalForm()
      const response = await this.$axios.$get('/user/' + record.id)
      this.id = record.id
      this.form.setFieldsValue(response)
    },
    showModalForm() {
      this.visibleModalForm = true
    },
    cancelModalForm(e) {
      this.visibleModalForm = false
      this.form.resetFields()
      this.id = null
    }
  }
}
</script>
