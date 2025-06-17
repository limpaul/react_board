import { useEffect, useState } from "react"
import axios from "axios";
import RestaurantListPartComponent from "./RestaurantListPartComponent";
import TabComponent from "../common/TabComponent";

export default function RestaurantListComponent(){
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
            {restauantData && restauantData.map((item, index)=>(
                <RestaurantListPartComponent 
                restaurantInfo={item} 
                key={index}
                />
            ))}
        </>
    )
}