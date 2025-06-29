import { useEffect } from "react";
import { useLocation } from "react-router-dom";
import { request } from "../../common/DataToServer";
import TabComponent from "../../common/TabComponent";
import '../../../../styles/order/manager/owner/orderwait.css'

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
                <div className="container">
                    <div className="selectContainer">
                        <div className="selectTab">
                            <ul>
                                <li>대기</li>
                                <li>처리중</li>
                                <li>배달</li>
                                <li>완료</li>
                            </ul>
                        </div>
                        <div className="orderStatusList">
                            <ul>
                                <li>주문번호</li>
                                <li>주문일시</li>
                                <li>주문픽업</li>
                                <li>주문배달</li>
                                <li>결재수단</li>
                                <li>주문금액</li>
                                <li>주문상태</li>
                            </ul>
                            <ul style={{'marginTop':'10px'}}>
                                <li>1</li>
                                <li>2025.09.13</li>
                                <li>배달</li>
                                <li>대기</li>
                                <li>카드</li>
                                <li>34000</li>
                                <li>대기</li>
                            </ul>
                            <ul style={{'marginTop':'10px'}}>
                                <li>2</li>
                                <li>2025.02.13</li>
                                <li>배달</li>
                                <li>대기</li>
                                <li>카드</li>
                                <li>10200</li>
                                <li>대기</li>
                            </ul>
                        </div>
                    </div>
                    <div className="resultContainer">
                        <div>주문번호 상태</div>
                        <div>
                            <ul>
                                <li>주문일시</li>
                                <li>주문번호</li>
                                <li>주소</li>
                                <li>연락처</li>
                                <li>고객요청</li>
                                <li>라이더요청</li>
                            </ul>
                            <div>배달 정보</div>
                            <ul style={{'display':'flex', 'gap':'20px'}}>
                                <li>라이더정보</li>
                                <li>비용</li>
                                <li>라이더</li>
                            </ul>
                            <div>메뉴</div>
                            <ul style={{'display':'flex', 'gap':'20px'}}>
                                <li>메뉴</li>
                                <li>수량</li>
                                <li>금액</li>
                            </ul>
                            <ul>
                                <li>치즈피자</li>
                                <li>포테이토</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}