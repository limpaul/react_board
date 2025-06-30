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
        const testData = [
            {
                "id": 1,
                "user_id": 4,
                "user": null,
                "restaurant": null,
                "items": [
                    {
                        "id": 1,
                        "order": null,
                        "menu": {
                            "id": 41,
                            "name": "에그타르트",
                            "price": 18844,
                            "category": 2,
                            "description": "특제 소스로 깊은 맛을 낸 요리",
                            "image": "https://source.unsplash.com/featured/300x200?food,5124",
                            "score": 4.0,
                            "created_at": "2025-01-14T05:38:00",
                            "restaurant_id": 3,
                            "menu": null,
                            "count": 0,
                            "restaurant": null
                        },
                        "quantity": 0,
                        "price_at_order": 18844
                    },
                    {
                        "id": 2,
                        "order": null,
                        "menu": {
                            "id": 43,
                            "name": "칠리새우",
                            "price": 8821,
                            "category": 2,
                            "description": "신선한 재료로 만든 인기 메뉴",
                            "image": "https://source.unsplash.com/featured/300x200?food,1321",
                            "score": 2.0,
                            "created_at": "2025-03-21T17:37:00",
                            "restaurant_id": 3,
                            "menu": null,
                            "count": 0,
                            "restaurant": null
                        },
                        "quantity": 0,
                        "price_at_order": 8821
                    },
                    {
                        "id": 3,
                        "order": null,
                        "menu": {
                            "id": 45,
                            "name": "에그타르트",
                            "price": 8540,
                            "category": 2,
                            "description": "고객 만족도가 높은 추천 메뉴",
                            "image": "https://source.unsplash.com/featured/300x200?food,2591",
                            "score": 2.0,
                            "created_at": "2025-01-12T07:41:00",
                            "restaurant_id": 3,
                            "menu": null,
                            "count": 0,
                            "restaurant": null
                        },
                        "quantity": 0,
                        "price_at_order": 8540
                    }
                ],
                "menus": null,
                "total_mount": 0,
                "order_count": 0,
                "delivery_person_id": null,
                "total_price": 36205,
                "status": "PENDING",
                "address": "서울특별시 노원구 상계로 31",
                "ordered_at": "2025-06-30T20:17:12.704849+09:00",
                "unique_str": "a2c3235e-8f50-40c6-83c9-dd1c03b4229c"
            },
            {
                "id": 2,
                "user_id": 1,
                "user": null,
                "restaurant": null,
                "items": [
                    {
                        "id": 4,
                        "order": null,
                        "menu": {
                            "id": 123,
                            "name": "카레도시락",
                            "price": 13539,
                            "category": 2,
                            "description": "신선한 재료로 만든 인기 메뉴",
                            "image": "https://source.unsplash.com/featured/300x200?food,1138",
                            "score": 5.0,
                            "created_at": "2025-03-08T07:17:00",
                            "restaurant_id": 3,
                            "menu": null,
                            "count": 0,
                            "restaurant": null
                        },
                        "quantity": 0,
                        "price_at_order": 13539
                    },
                    {
                        "id": 5,
                        "order": null,
                        "menu": {
                            "id": 124,
                            "name": "오징어볶음",
                            "price": 7911,
                            "category": 2,
                            "description": "특제 소스로 깊은 맛을 낸 요리",
                            "image": "https://source.unsplash.com/featured/300x200?food,4026",
                            "score": 3.0,
                            "created_at": "2025-01-16T09:46:00",
                            "restaurant_id": 3,
                            "menu": null,
                            "count": 0,
                            "restaurant": null
                        },
                        "quantity": 0,
                        "price_at_order": 7911
                    },
                    {
                        "id": 6,
                        "order": null,
                        "menu": {
                            "id": 125,
                            "name": "불고기도시락",
                            "price": 5062,
                            "category": 2,
                            "description": "고객 만족도가 높은 추천 메뉴",
                            "image": "https://source.unsplash.com/featured/300x200?food,983",
                            "score": 5.0,
                            "created_at": "2025-03-11T03:58:00",
                            "restaurant_id": 3,
                            "menu": null,
                            "count": 0,
                            "restaurant": null
                        },
                        "quantity": 0,
                        "price_at_order": 5062
                    }
                ],
                "menus": null,
                "total_mount": 0,
                "order_count": 0,
                "delivery_person_id": null,
                "total_price": 26512,
                "status": "PENDING",
                "address": "서울특별시 강남구 테헤란로 123",
                "ordered_at": "2025-06-30T20:19:48.74923+09:00",
                "unique_str": "17c56795-042a-4168-8f83-96dcec942d5c"
            }
        ]
        /*
        request({
            url: `/api/order/user/manager/order/list/${restaurantData.id}`,
            method: 'GET',
            authentication: true
        }).then(res => {
            console.log(`from server ${res}`);
        })
        */
       setOrderListData(testData);
       console.log(testData);
    }, [])
    const dateStr = (dateStr) => {
        const date = new Date(dateStr);
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
                                            <li>{value.delivery_person_id==null?'대기중':'배달'}</li>
                                            <li>카드</li>
                                            <li>{value.total_price}</li>
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
                                            <li>{value.quantity}</li>
                                            <li>{value.price_at_order}</li>
                                        </ul>
                                    ))
                                }
                            </ul>
                            <div className="orderAccept">
                                <div>주문 수략</div>
                                <div>주문 거절</div>
                            </div>
                        </div>
                        }
                        
                    </div>
                </div>
            </div>
        </>
    )
}