import { useEffect, useRef, useState } from 'react'
import { Link, useNavigate } from 'react-router-dom';
import '../../../styles/order/tab.css'
import { checkUserInfoFromLocalStorage } from './DataToServer';

export default function TabComponent(){
    const tablist = useRef(null)
    const [visibleLoginTab, setVisibleLoginTab] = useState(true);
    const [visibleManagerTab, setVisibleManagerTab] = useState(false);
    const navigate = useNavigate();
    /* Tab에서 로그아웃 기능 추가  */
    const logout = () => {
        if(localStorage.getItem('token')!==null){
            localStorage.removeItem('token');
            sessionStorage.removeItem('userinfo');

            navigate('/order/user/login')
        }
    }
    useEffect(()=>{
        const userInfo = checkUserInfoFromLocalStorage();
        if(userInfo.role === 'ROLE_OWNER'){
            setVisibleManagerTab(true);
        }
        if(userInfo){
            setVisibleLoginTab(false);
        }
    }, [])
    function tabShow(){
        tablist.current.classList.add("show");
    }
    const tabclose = () =>{
        tablist.current.classList.remove('show')
    }
    return (
        <>
            <div>
                <div id='showTabBtn' onClick={tabShow}>=</div>
                <div id='tablist' ref={tablist}>
                    <div id='tabclose' onClick={tabclose}>x</div>
                    <ul>
                        <li>{visibleLoginTab && <Link to="/order/user/login">로그인</Link>}</li>
                        <li><Link to="/order/restaurant/list">주문하기</Link></li>
                        <li><Link to="/order/user/order/compelete/list">주문내역</Link></li>
                        <li>{visibleManagerTab && <Link to="/order/user/mananger/order">주문관리</Link>}</li>
                        <li>{visibleManagerTab && <Link to="/order/user/mananger/restaurant">가게관리</Link>}</li>
                        <li><Link to="/order/user/mypage">마이페이지</Link></li>
                        <li>고객센터</li>
                        <li onClick={()=>{logout()}}>{!visibleLoginTab && <div>로그아웃</div>}</li>
                    </ul>
                </div>
            </div>
        </>
    )
}