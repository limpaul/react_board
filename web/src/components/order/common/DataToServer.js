import axios from "axios"

export const checkUserInfoFromLocalStorage = () => {
  const token = localStorage.getItem('token');
  if(token){
    return JSON.parse(atob(token.split('.')[1]));
  }else{
    return false;
  }
}

export const checkLogin = async () => {
  const token = localStorage.getItem('token');
  
  if(token){
    return await request({
      'method':'GET',
      'url':'/order/user/api/checkLogin',
      'authentication':`Bearer ${token}`
    })
  }else{
    return await request({
      'method':'GET',
      'url':'/order/user/api/checkLogin',
    })
    
  }
}
export const request = async ({ method, url, contentType, body, authentication=false }) => {
    // local storage에 token이 있을 경우 서버에 요청한다
    const token = localStorage.getItem('token')
    try {
      if (method === 'GET') {
        const headers = token?{
          'Authorization':`Bearer ${token}`,
          'Content-Type':'text/html;charset=UTF-8'
        }:
        {
          'Content-Type':'text/html;charset=UTF-8'
        }
        const response = await axios.get(url, {
            headers:headers
        });
        return response.data;
      }
  
      if (method === 'POST') {
        const headers = authentication?{
          'Content-Type':contentType,
          'Authorization':`Bearer ${token}`
        }
        :
        {
          'Content-Type':contentType
        };

        const response = await axios.post(url, body, {
          headers: headers,
        });
        return response.data;
      }
    } catch (error) {
      console.error('API 요청 에러:', error);
      throw error; 
    }
  };