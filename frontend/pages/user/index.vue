<template>
  <div class="box-content">
    <a-row>
      <a-col :xs="24" :sm="20" :md="21" :lg="21" :xl="21"><h1>Usuários</h1></a-col>
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
      <a-col :span="24">
        <a-table
          :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
          :columns="columns"
          :dataSource="data"
          :pagination="pagination"
          :loading="loading"
          @change="handleTableChange"
        >
          <template slot="name" slot-scope="name">
            {{name.first}} {{name.last}}
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
      data: [],
      pagination: {},
      loading: false,
      selectedRowKeys: [],
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
        }
      ]
    }
  },
  computed: {
    hasSelected() {
      return this.selectedRowKeys.length > 0
    },
  },
  methods: {
    onSelectChange(selectedRowKeys) {
      console.log('selectedRowKeys changed: ', selectedRowKeys)
      this.selectedRowKeys = selectedRowKeys
    },
    handleTableChange(pagination, filters, sorter) {
      console.log(pagination)
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
    list(params = {}) {
      /*
      console.log('params:', params)
      this.loading = true
      userService.list().then(response => {
        if (response.status == 200) {
          const pagination = { ...this.pagination };
          const data = response.data
          console.log('dta')
          console.log(data)
          pagination.total = data.totalPages;
          this.loading = false;
          this.data = data.content;
          this.pagination = pagination;
        }
      })
      */

      /*
      reqwest({
        url: 'https://randomuser.me/api',
        method: 'get',
        data: {
          results: 10,
          ...params,
        },
        type: 'json',
      }).then(data => {
        const pagination = { ...this.pagination };
        // Read total count from server
        // pagination.total = data.totalCount;
        pagination.total = 200;
        this.loading = false;
        this.data = data.results;
        this.pagination = pagination;
      });
      */
    },
  }
}
</script>
