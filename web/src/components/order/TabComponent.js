import { useRef } from 'react'
import '../../styles/order/tab.css'
export default function TabComponent(){
    const tablist = useRef(null)
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
                        <li>로그인</li>
                        <li>회원가입</li>
                        <li>주문하기</li>
                        <li>주문내역</li>
                        <li>로그아웃</li>
                    </ul>
                </div>
            </div>
        </>
    )
}