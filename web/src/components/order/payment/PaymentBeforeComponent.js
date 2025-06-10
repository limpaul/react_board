import '../../../styles/order/payment/paymentbefore.css'
export default function PaymentBeforeComponent(){
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
                }}>17,000원</div>
                <div style={{
                    'marginRight':'20px',
                    'border':'1px solid rgb(192, 192, 192)',
                    'padding':'15px',
                    'borderRadius': '20px',
                    'background':'lightseagreen',
                    'color':'white',
                }}>(2)장바구니보기</div>
            </div>
        </>
    )
}