import { useEffect, useState } from "react"
import TabComponent from "../../common/TabComponent";
import { request } from "../../common/DataToServer"
import OrderCompeleteMyInfoPart from "./OrderCompeleteMyInfoPart"
// /order/user/order/compelete/list
export default function(){    
    const [orderCompleteInfo, setOrderCompleteInfo] = useState(null);

    useEffect(()=>{
        request({
            method:'GET',
            url:'/api/order/user/order/complete/list',
            authentication: true
        }).then(res=>{
            if(res.status){
                setOrderCompleteInfo(res.data);
            }
        })
    }, [])
    
    return (<>
        <TabComponent/>
        <div>주문내역</div>
        {
        orderCompleteInfo && orderCompleteInfo.map((item, index)=>(
            <OrderCompeleteMyInfoPart key={index} orderCompleteInfo={item}/>
        ))
        }
    </>)
}