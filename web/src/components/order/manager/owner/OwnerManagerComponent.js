import axios from "axios"
import RestaurantListPartComponent from "../../list/RestaurantListPartComponent"
import TabComponent from "../../common/TabComponent"
import { useEffect, useState } from "react"
import ManagerSubComponent from "../../common/manager/ManagerSubComponent";

export default function OwnerManagerComponent(){
    const [restauantData, setRestauantData] = useState(null);
    useEffect(()=>{
        axios.get('/testdata/order/orderlist.json')
        .then((response)=>{
            setRestauantData(response.data)
        }, [])
        
    }, [])

    return (
        <>
            <TabComponent/>
            <ManagerSubComponent/>
            <h2>사용자 가게 관리</h2>
            {restauantData && restauantData.map((item, index)=>(
                <RestaurantListPartComponent 
                restaurantInfo={item} 
                key={index}
                />
            ))}
        </>
    )
}