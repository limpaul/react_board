import { useEffect } from "react";
import { useLocation } from "react-router-dom";
import { request } from "../../common/DataToServer";
import TabComponent from "../../common/TabComponent";

// /order/user/mananger/order/:id
export default function OwnerManagerOrderWaitComponent(){
    const location = useLocation();
    const restaurantData = location.state;

    useEffect(()=>{
        console.log(restaurantData);
        /*
        request({
            url: `/api/order/user/manager/order/list/${restaurantData.id}`,
            method: 'GET',
            authentication: true
        }).then(res => {
            console.log(`from server ${res}`);
        })
        */
    }, [])
    return (
        <>
            <TabComponent/>
            <div>가게 주문 목록</div>
            {/* 대기 | 처리중 | 배달 | 완료  */}
            <div>
                <div>
                    <div>
                        <ul>
                            <li>대기</li>
                            <li>처리중</li>
                            <li>배달</li>
                            <li>완료</li>
                        </ul>
                    </div>
                    <div>
                        <ul>
                            <li>주문번호</li>
                            <li>주문일시</li>
                            <li>주문픽업</li>
                            <li>주문배달</li>
                            <li>결재수단</li>
                            <li>주문금액</li>
                            <li>주문상태</li>
                        </ul>
                    </div>
                </div>
            </div>
        </>
    )
}