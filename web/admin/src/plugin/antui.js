import Vue from 'vue'
import { Button, FormModel, Input, Icon, message, Layout, Menu, Card, Table, Row, Col, Pagination, Modal, Drawer, Form, Select , Upload, Switch, Popconfirm
        ,Avatar, Tag, Radio
    } from 'ant-design-vue'


message.config({
  top: '50px',
  duration: 2,
  maxCount: 3
})

Vue.prototype.$message = message
Vue.prototype.$confirm = Modal.confirm



Vue.use(Button)
Vue.use(FormModel)
Vue.use(Input)
Vue.use(Icon)
Vue.use(Layout)
Vue.use(Menu)
Vue.use(Card)
Vue.use(Table)
Vue.use(Row)
Vue.use(Col)
Vue.use(Pagination)
Vue.use(Drawer)
Vue.use(Form)
Vue.use(Select)
Vue.use(Upload)
Vue.use(Switch)
Vue.use(Modal)
Vue.use(Avatar)
Vue.use(Tag)
Vue.use(Radio)

