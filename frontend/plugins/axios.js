export default function ({ $axios, redirect }, inject) {
    // Create a custom axios instance
    $axios.create({
      headers: {
        common: {
          Accept: 'text/plain, */*'
        }
      }
    })
  
    // Set baseURL to something different
    $axios.setBaseURL(process.env.baseApiUrl)
  }