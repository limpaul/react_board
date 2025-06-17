import { useEffect, useRef } from 'react'
import { Link } from 'react-router-dom';
import '../../../styles/order/tab.css'
import { checkUserInfoFromLocalStorage } from './DataToServer';

export default function TabComponent(){
    const tablist = useRef(null)
    useEffect(()=>{
        const userInfo = checkUserInfoFromLocalStorage();
        if(userInfo){
            console.log('TabComponent');
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
                        <li><Link to="/order/user/login">로그인</Link></li>
                        <li><Link to="/order/restaurant/list">주문하기</Link></li>
                        <li><Link to="/order/user/shopping/cart">주문내역</Link></li>
                        <li>마이페이지</li>
                        <li>고객센터</li>
                        <li><Link to="">로그아웃</Link></li>
                    </ul>
                </div>
            </div>
        </>
    )
}