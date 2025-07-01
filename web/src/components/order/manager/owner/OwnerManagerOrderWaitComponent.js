import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import { request } from "../../common/DataToServer";
import TabComponent from "../../common/TabComponent";
import '../../../../styles/order/manager/owner/orderwait.css'

// /order/user/mananger/order/:id
export default function OwnerManagerOrderWaitComponent(){
    const location = useLocation();
    const restaurantData = location.state;
    const [orderListData, setOrderListData] = useState();
    const [orderDetailListData, setOrderDetailListData] = useState();
    useEffect(()=>{
        console.log(restaurantData);
        
        request({
            url: `/api/order/user/manager/order/list/${restaurantData.id}`,
            method: 'GET',
            authentication: true
        }).then(res => {
            console.log(`from server ${res}`);
            setOrderListData(res);
        })
    }, [])
    const dateStr = (dateString) => {
        const date = new Date(dateString);
        // 월 (0부터 시작하므로 +1 필요)
        const year = date.getFullYear();
        const month = date.getMonth() + 1;
        const day = date.getDate();
        const hour = date.getHours();
        const minute = date.getMinutes();
        return `${year}.${month}.${day}.${hour}시${minute}분`;
    }
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
                                {
                                    orderListData && orderListData.map((value, index)=>(
                                        <ul key={index} style={{'marginTop':'10px'}} className='orderWaitColum' onClick={()=>{
                                            setOrderDetailListData(value);
                                        }}>
                                            <li>{value.id}</li>
                                            <li>{dateStr(value.ordered_at)}</li>
                                            <li>배달</li>
                                            <li>{value.deliveryPersonId==null?'대기중':'배달'}</li>
                                            <li>카드</li>
                                            <li>{value.total_price}원</li>
                                            <li>{value.status=='PENDING'?'주문 대기중':'조리중'}</li>
                                        </ul>
                                    ))
                                }
                        </div>
                    </div>
                    <div className="resultContainer">
                        <div>주문번호 상태</div>
                        { orderDetailListData && 
                            <div>
                            <ul className="subOrderInfoList">
                                <li>
                                    <div>주문일시</div>
                                    <div>{dateStr(orderDetailListData.ordered_at)}</div>
                                </li>
                                <li>
                                    <div>주문번호</div>
                                    <div>{orderDetailListData.id}</div>
                                </li>
                                <li>
                                    <div>주소</div>
                                    <div>{orderDetailListData.address}</div>
                                </li>
                                <li>
                                    <div>연락처</div>
                                    <div>비공개</div>
                                </li>
                                <li>
                                    <div>고객요청</div>
                                    <div>없음</div>
                                </li>
                                <li>
                                    <div>라이더요청</div>
                                    <div>없음</div>
                                </li>
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
                                {
                                    orderDetailListData.items.map((value, key)=>(
                                        <ul key={key} style={{'display':'flex', 'gap':'20px'}}>
                                            <li>{value.menu.name}</li>
                                            <li>{value.menu.count}개</li>
                                            <li>{value.price_at_order}원</li>
                                        </ul>
                                    ))
                                }
                            </ul>
                            <div className="orderAccept">
                                <div onClick={()=>{
                                    debugger;
                                    request({
                                        method: 'POST',
                                        url:'/api/order/user/manager/order/accept',
                                        contentType:'application/json',
                                        body:{
                                            'uniqueStr':`${orderDetailListData.unique_str}`
                                        },
                                        authentication: true
                                    })
                                }}>주문 수략</div>
                                
                                <div onClick={()=>{
                                    request({
                                        method: 'POST',
                                        url:'/api/order/user/manager/order/cancel',
                                        contentType:'application/json',
                                        body:{
                                            'uniqueStr':`${orderDetailListData.unique_str}`
                                        },
                                        authentication: true
                                    })
                                }}>주문 거절</div>
                            </div>
                        </div>
                        }
                        
                    </div>
                </div>
            </div>
        </>
    )
}