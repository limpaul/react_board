import '../../../styles/order/payment/paymentinput.css'
export default function PaymentInputComponent(){
    return (
        <>
            <div>
                {/* 키패드 화면 위에 부분 */}
                <div id="keypadCustomUI">
                    <div className='dot'></div>
                    <div className='dot'></div>
                    <div className='dot'></div>
                    <div className='dot'></div>
                </div>
                {/* 가상키패드 영역 */}
                <div>

                </div>
            </div>
        </>
    )
}