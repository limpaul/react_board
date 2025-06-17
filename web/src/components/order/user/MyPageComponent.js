import { useEffect, useState } from 'react'
import '../../../styles/order/user/mypage.css'
import TabComponent from '../common/TabComponent'
export default function MyPageComponent(){
    const [userName, setUserName] = useState('');
    useEffect(()=>{
        const token = localStorage.getItem('token');
        if(token){
            const userInfo = JSON.parse(atob(token.split('.')[1]));
            setUserName(userInfo.sub);
        }
    }, [])
    return (
        <>
            <TabComponent/>
            <div>
                <div className='userInfo'>
                    <div id='userlogo'></div>
                    <div className='userManage'>
                        <div id='userid'>{userName} &gt;</div>
                        <div className='manage'>
                            <div>리뷰관리 &gt;</div>
                            <div>주소관리 &gt;</div>
                        </div>
                    </div>
                </div>
                <div className='infoAlarm'>
                    <div className='minisub'>문의 및 알림</div>
                    <div className='flexdiv'>
                        <div className='left'>
                            <div>고객센터</div>
                            <div>자주 묻는 질문</div>
                        </div>
                        <div className='right'>
                            <div>공지사항</div>
                            <div>약관 및 정책</div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}