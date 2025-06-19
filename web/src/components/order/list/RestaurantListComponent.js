import { useEffect, useState } from "react"
import axios from "axios";
import RestaurantListPartComponent from "./RestaurantListPartComponent";
import TabComponent from "../common/TabComponent";
import { request } from "../common/DataToServer";

// /order/restaurant/list
export default function RestaurantListComponent(){
    const [restauantData, setRestauantData] = useState(null);
    
    useEffect(()=>{
            request({
                url:'/api/order/user/restaurant/list',
                method:'GET',
                authentication:'true'
            }).then((response)=>{
                setRestauantData(response)
            })
    }, [])
 
    return (
        <>
            <TabComponent/>
            {restauantData && restauantData.map((item, index)=>(
                <RestaurantListPartComponent 
                restaurantInfo={item} 
                key={index}
                />
            ))}
        </>
    )
}