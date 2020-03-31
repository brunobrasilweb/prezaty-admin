import axios from 'axios'
import Cookies from 'js-cookie'

const http = axios.create({
  baseURL: process.env.baseApiUrl,
  timeout: 5000
})

http.interceptors.request.use(config => {
  return config
}, error => {
  console.log(error)
})

export default http
