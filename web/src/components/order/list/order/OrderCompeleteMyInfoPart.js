import '../../../../styles/order/common/list/order/ordercompletelist.css'

export default function OrderCompeleteMyInfoPart({orderCompleteInfo}){
    return (<>
        <div className="compeleteList">
            <div className="firtDiv">
                <div>{orderCompleteInfo.ordered_at.slice(0, 10)}</div>
                <div><b>{orderCompleteInfo.restaurant.name}<div>( {orderCompleteInfo.status=='PENDING'?'주문 대기중':'주문 완료'} )</div></b></div>
                <div>
                    <ul>
                        {
                            orderCompleteInfo.menus.map((menu, index)=>{
                                return <li key={index}>{menu.name} {menu.count}개</li>
                            })
                        }
                    </ul>
                </div>
            </div>
            <div className="orderStatusInfo">
                <div className="paymentInfo">
                    {
                        <>
                                <div>결제금액</div>
                                <div>{orderCompleteInfo.total_price}원</div>
                        </>
                    }
                </div>
                <div>
                    <div className="statusBtn">
                        <div><b>리뷰 쓰기</b></div>
                        <div><b>같은 메뉴 담기</b></div>
                    </div>
                </div>
            </div>
            <center>문의하기</center>
        </div>
    </>)
}