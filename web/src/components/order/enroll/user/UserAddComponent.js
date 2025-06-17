import { useEffect, useRef, useState } from "react"
import { useNavigate } from "react-router-dom";
import '../../../../styles/order/login.css'
import '../../../../styles/order/useradd.css'
import OrderTitleComponent from "../../common/OrderTitleComponent";
import TabComponent from "../../common/TabComponent";
import LinkComponent from "../../../LinkComponent";
import { request } from "../../common/DataToServer";
import EnrollRestaurantComponent from "../restaurant/EnrollRestaurantComponent";

/*
    - 사용자 회원가입
    /order/user/enroll
*/
export default function UserAddComponent(){
    const navigate = useNavigate();
    const restaurantEnrollChecked = useRef();
    const username = useRef(); // 사용자 아이디 
    const useremail = useRef(); // 사용자 이메일 
    const userpassword = useRef(); // 사용자 비밀번호 

    const [visibleRestaurantAddComponent, setVisibleRestaurantAddComponent] = useState(false);
    const [body, setBody] = useState({});

    const useradd = () => {
        const checked = restaurantEnrollChecked.current.checked;
        // 판매용 계정이 check가 되어있다면 식당도 데이터에 추가한다 
        const userInfo = {
             username: username.current.value,
            useremail: useremail.current.value,
            userpassword: userpassword.current.value,
            isChecked: false,
        }
        if(checked){
            
            setBody(prev => {
                const newBody = {
                    ...prev,
                    ...userInfo,
                    isChecked: true
                }
                request(
                {
                    'method':'POST',
                    'url':'/api/order/user/enroll/account',
                    'contentType':'application/json',
                    'body':newBody,
                    'authentication':false
                }
            )
            });

            
        }else{ // 일반 계정 
            request(
                {
                    'method':'POST',
                    'url':'/api/order/user/enroll/account',
                    'contentType':'application/json',
                    'body':userInfo,
                    'authentication':false
                }
            )
        }
    }
    return (
        <>
            <LinkComponent/>
            <TabComponent/>
            <OrderTitleComponent/>
            <div style={{display:visibleRestaurantAddComponent?'none':'block'}}>
                <div className="useraddForm">
                    <input type="text" id="username" ref={username} placeholder="이름(별명)" />
                    <input type="text" id="useremail" ref={useremail} placeholder="이메일" />
                    <input type="password" id="userpassword" ref={userpassword} placeholder="비밀번호" />
                    <input type="password" id="repassword" placeholder="비밀번호 확인" />
                </div>
                <div>
                    <input type='checkbox' id="role_user" checked={true} readOnly={true} />
                    <label>일반 사용자</label>
                    <input type='checkbox' id="role_restaurant" ref={restaurantEnrollChecked} onClick={() => {
                        setVisibleRestaurantAddComponent(true);
                    }} />
                    <label>판매용 계정</label>
                    <div style={{ 'marginTop': '20px' }}>
                        <button id="useraddBrn" onClick={useradd}>회원가입</button>
                    </div>
                </div>
            </div>
             { visibleRestaurantAddComponent && <EnrollRestaurantComponent setVisibleRestaurantAddComponent={setVisibleRestaurantAddComponent} setBody={setBody}/>}
        </>
    )
}