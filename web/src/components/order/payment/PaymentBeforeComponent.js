import { useEffect } from 'react'
import '../../../styles/order/payment/paymentbefore.css'

/*
    가격과 장바구니를 담은 UI이미지를 반환한다
*/
export default function PaymentBeforeComponent({paymentOrderInfo, onCustomClick, isPayment}){
    
    return(
        <>
            <div className='ordercard' style={{
                'display':'flex',
                'justifyContent':'space-between',
                'alignItems':'center'
            }}>
                <div style={{
                    'marginLeft':'20px',
                    'border':'1px solid rgb(192, 192, 192)',
                    'padding':'15px',
                    'borderRadius': '20px'
                }}>{paymentOrderInfo.totalprice}원</div>
                <div style={{
                    'marginRight':'20px',
                    'border':'1px solid rgb(192, 192, 192)',
                    'padding':'15px',
                    'borderRadius': '20px',
                    'background':'lightseagreen',
                    'color':'white',
                }}
                    onClick={onCustomClick}
                >({paymentOrderInfo.ordercount}){isPayment?'결재하기':'장바구니담기'}</div>
            </div>
        </>
    )
}