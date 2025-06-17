import { useRef } from 'react';
import '../../../../styles/order/enroll/enrollrestaurnt.css'
export default function EnrollRestaurantComponent({setVisibleRestaurantAddComponent, setBody}){
    const restaurantname = useRef();
    const restaurantaddress = useRef();
    const restaurantexplain = useRef();
    return (
        <>
            <div className="enrollRestaurntaddForm">
                <input type="text" id="restaurantname" ref={restaurantname} placeholder="식당 이름"/>
                <input type="text" id="restaurantaddress" ref={restaurantaddress} placeholder="식당 주소"/>
                <input type="password" id="restaurantexplain" ref={restaurantexplain} placeholder="식당 설명"/>
            </div>
            <div className='enrollRestaurntaddFormAgree'>
                <ul>
                    <li>
                        <input type='checkbox'/>
                        <label>느그민족 경영진에게 매달 수입료 10%납부에 대한 이의 없음에 동의</label>
                    </li>
                    <li>
                        <input type='checkbox'/>
                        <label>느그민족 배달 수수료가 추가 10%까지의 인상에 대한 이의 없음에 동의합니다</label>
                    </li>
                    <li>
                        <input type='checkbox'/>
                        <label>느그민족 경영진이 시키는 수수료는 무료임을 이의 없음에 동의합니다</label>
                    </li>
                </ul>
                
                <div style={{'marginTop':'20px'}}>
                    <button id="enrollRestaurantAddBtn" onClick={()=>{
                        setBody({
                            restaurantName : restaurantname.current.value,
                            restaurantAddress: restaurantaddress.current.value,
                            restaurantExplain: restaurantexplain.current.value
                        })
                        setVisibleRestaurantAddComponent(false);
                    }}>가게등록</button>
                </div>
            </div>
        </>
    )
}