import request from '@/utils/request'

export function login(data) {
  return request({
    // url:"/vue-element-admin/user/info",
    url:'http://47.236.103.174:8086/admin/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: 'http://localhost:9527/dev-api/vue-element-admin/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: 'http://localhost:9527/dev-api/vue-element-admin/user/logout',
    method: 'post'
  })
}
