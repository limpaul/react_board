import { useEffect, useState } from 'react'
import '../../../styles/order/payment/paymentinput.css'
export default function PaymentInputComponent(){
    useEffect(()=>{
        const transkeyInputObj = document.getElementById('transkeyinput');
        window.mtk.setKeyboard(transkeyInputObj);
        window.mtk.onKeyboard(transkeyInputObj)
        
    }, [])
    const [fillDot, setFillDot] = useState(0);
    window.mTranskey.prototype.startCallBack = (ele) => {
        if(ele.getAttribute('aria-label') === '빈칸'){
            return;
        }
        setFillDot(fillDot + 1);
    }
    window.mTranskey.prototype.delCallBack = () => {
        setFillDot(fillDot - 1);
    }
    window.mTranskey.prototype.relocateCallback = () => {
        setFillDot(0);
    }
    return (
        <>
            <div>
                {/* 키패드 화면 위에 부분 */}
                <div id="keypadCustomUI">
                    <div className={fillDot>0?'dot fill':'dot'}></div>
                    <div className={fillDot>1?'dot fill':'dot'}></div>
                    <div className={fillDot>2?'dot fill':'dot'}></div>
                    <div className={fillDot>3?'dot fill':'dot'}></div>
                    <input type="password" id='transkeyinput' name='transkeyinput' data-tk-kbdtype='number' data-tk-bottom='true' maxLength='4'/> 
                </div>
                {/* 가상키패드 영역 */}
                <div>

                </div>
            </div>
        </>
    )
}