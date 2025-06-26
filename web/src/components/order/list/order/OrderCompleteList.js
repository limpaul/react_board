import { useEffect } from "react"
import TabComponent from "../../common/TabComponent"
import { request } from "../../common/DataToServer"

export default function(){    
    useEffect(()=>{
        request({
            method:'GET',
            url:'/api/order/user/order/complete/list',
            authentication: true
        })
    }, [])
    return (<>
        <TabComponent/>
        <div>주문목록</div>
    </>)
}