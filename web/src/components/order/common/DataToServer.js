import axios from "axios"

export const request = async ({ method, url, body }) => {
    try {
      if (method === 'GET') {
        const response = await axios.get(url);
        return response.data;
      }
  
      if (method === 'POST') {
        const response = await axios.post(url, body, {
          headers: {
            'Content-Type': 'application/json',
          },
        });
        return response.data;
      }
    } catch (error) {
      console.error('API 요청 에러:', error);
      throw error; 
    }
  };