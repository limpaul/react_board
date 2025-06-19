import { useState } from 'react'
import '../../../../styles/order/common/manager/managersubcomponent.css'
export default function({setShowCheckBox}){
    const [clickManagerBtn, setClickManagerBtn] = useState(false);

    return (
        <>
            <div>
                {
                    clickManagerBtn &&
                    <div id='controlPannel'>
                    <div id='restaurantmanagerlist'>
                        <ul>
                            <li>가게 등록</li>
                            <li onClick={()=>{
                                setShowCheckBox(true)
                            }}>가게 삭제</li>
                        </ul>       
                    </div> 
                    </div>
                }
                <div id='controlBtn' onClick={()=>{
                    if(clickManagerBtn){
                        setClickManagerBtn(false)
                    }else{
                        setClickManagerBtn(true)
                    }
                }}>{clickManagerBtn?'x':'+'}</div>

                <div></div>
            </div>
        </>
    )
}