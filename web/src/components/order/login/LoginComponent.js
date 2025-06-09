import { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import '../../../styles/order/login.css'
import LinkComponent from '../../LinkComponent'
import OrderTitleComponent from '../common/OrderTitleComponent'

export default function LoginComponent({isVisibleUserAddComponent}){
    
    useEffect(()=>{
        
    }, [])
    return (<>
        <LinkComponent/>
        <OrderTitleComponent/>
        <div className="loginForm">
            <div className='loginTextDiv'>
                <div>
                    <input type="text" placeholder="아이디"/>
                </div>
                <div>
                    <input type="password" placeholder="비밀번호"/>
                </div>
            </div>
            <div className='loginLinkDiv'>
                <button id="loginBrn">로그인</button>
                <p>혹시 계정이 없으신가요? <b><Link to="/order/enroll/user">회원가입</Link></b></p>
            </div>
            
        </div>
    </>)
}