import { useEffect, useRef, useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import '../../../styles/order/login.css'
import LinkComponent from '../../LinkComponent'
import OrderTitleComponent from '../common/OrderTitleComponent'
import TabComponent from '../common/TabComponent'
import { request } from '../common/DataToServer' 

export default function LoginComponent({isVisibleUserAddComponent}){
    const navigate = useNavigate();
    const [userLogin, setUserLogin] = useState(null);
    const username = useRef(null);
    const userpassword = useRef(null);
    const [isVisibleLoginFailInfo, setIsVisibleLoginFailInfo] = useState(false);

    const handleLogin = async () =>{
        const result = await request(
            {
                'method':'POST',
                'url':'/api/order/user/login',
                'body': {
                    username : username.current.value,
                    userpassword : userpassword.current.value
                }
            }
        )
        /*
            {
                "role": "ROLE_USER",
                "message": "로그인 성공",
                "user": "test@example.com",
                "username": "test",
                "isSuccess": true
            }
        */
        if(result.isSuccess){
            localStorage.setItem('token', result.token)
            navigate('/order/restaurant/list', {
                state: result
            })
        }else if(result.isSuccess === false){
            setIsVisibleLoginFailInfo(true)
        }

    }
    useEffect(()=>{
        // useNavigate 
        window.mVaccine_onload();
    }, [])
    return (<>
        <LinkComponent/>
        <OrderTitleComponent/>
        <TabComponent/>
        <div className="loginForm">
            <div className='loginTextDiv'>
                <div>
                    <input type="text" ref={username} placeholder="아이디"/>
                </div>
                <div>
                    <input type="password" ref={userpassword} placeholder="비밀번호"/>
                </div>
            </div>
            <div className='loginLinkDiv'>
                <button id="loginBrn" onClick={handleLogin}>로그인</button>
                {isVisibleLoginFailInfo &&<div style={{'marginTop':'10px', 'color':'red'}}>아이디 또는 비밀번호가 일치하지 않습니다</div>}
                <p>혹시 계정이 없으신가요? <b><Link to="/order/user/enroll">회원가입</Link></b></p>
            </div>
            
        </div>
    </>)
}