import Http from './http'
import Cookies from 'js-cookie'

export const add = (user) => {
  return Http.post('/user', user)
    .setToken(Cookies.get('access_token'), 'Bearer')
}

export const byId = (id) => {
  return Http.get('/user/' + id)    
}
