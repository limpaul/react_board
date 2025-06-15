import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom";
import '../../../../styles/order/login.css'
import '../../../../styles/order/useradd.css'
import OrderTitleComponent from "../../common/OrderTitleComponent";
import TabComponent from "../../common/TabComponent";
import LinkComponent from "../../../LinkComponent";

export default function UserAddComponent(){
    const navigate = useNavigate();

    return (
        <>
            <LinkComponent/>
            <TabComponent/>
            <OrderTitleComponent/>
            <div className="useraddForm">
                <input type="text" id="username" placeholder="이름(별명)"/>
                <input type="text" id="useremail" placeholder="이메일"/>
                <input type="password" id="userpassword" placeholder="비밀번호"/>
                <input type="password" id="repassword" placeholder="비밀번호 확인"/>
            </div>
            <div>
                <input type='checkbox' id="role_user" checked={true} readOnly={true}/>
                <label>일반 사용자</label>
                <input type='checkbox' id="role_restaurant" onClick={(e)=>{
                    if(e.target.checked){
                        navigate('/order/user/enroll/restaurant');
                    }
                }}/>
                <label>판매용 계정</label>
                <div style={{'marginTop':'20px'}}>
                    <button id="useraddBrn">회원가입</button>
                </div>
            </div>
        </>
    )
}