import '../../../styles/order/common/downtopopup.css'

export default function DownToUpComponent(){
    return (<>
        <div id='popupoption' >
            <div><h2>결재방식</h2></div>
            <div className='paymentSelect'>
                <ul>
                    <li>
                        <div>
                            <input type="radio" id='ts2' name='payment'/>
                            <b>팀장님 카드 ( 배달비 20% )</b>
                        </div>
                    </li>
                    <li>
                        <div>
                            <input type="radio" id='peronsal' name='payment'/>
                            <b>개인 카드</b>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </>);
}