import Cookies from 'js-cookie'
import { Base64 } from 'js-base64'
import Http from './http'
import vue from 'vue'

export function setUser (user, timeout) {
  user = Base64.encode(JSON.stringify(user))
  Cookies.set('user', user, { expires: timeout })
}

export function getUser (field) {
  if (Cookies.get('user')) {
    const user = JSON.parse(Base64.decode(Cookies.get('user')))
    if (!field) {
      return user
    } else {
      return user[field]
    }
  }
  return null
}

export const signin = (data) => {
  return Http.post('/auth/signin', data)
}

export function logout () {
  Cookies.remove('auth')
  Cookies.remove('user')
}
