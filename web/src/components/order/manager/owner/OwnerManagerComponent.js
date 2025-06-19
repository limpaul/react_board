import axios from "axios"
import RestaurantListPartComponent from "../../list/RestaurantListPartComponent"
import TabComponent from "../../common/TabComponent"
import { useEffect, useState } from "react"
import ManagerSubComponent from "../../common/manager/ManagerSubComponent";
import { request, checkLogin } from "../../common/DataToServer";
import { useNavigate } from "react-router-dom";

// 가게 관리 - /order/user/mananger/restaurant
export default function OwnerManagerComponent(){
    const [restauantData, setRestauantData] = useState(null);
    const naviator = useNavigate();
    const [showCheckBox, setShowCheckBox] = useState(false);
    useEffect(()=>{
        checkLogin()
        .then(response => {
            naviator(response.redirectTo);
        })
        
        request({
            url:'/api/order/user/manager/restaurant/list',
            method:'GET',
            authentication:'true'
        }).then((response)=>{
            setRestauantData(response)
        })
    }, [])

    return (
        <>
            <TabComponent/>
            <ManagerSubComponent setShowCheckBox={setShowCheckBox}/>
            <h2>사용자 가게 관리</h2>
            {restauantData && restauantData.map((item, index)=>(
                <RestaurantListPartComponent 
                restaurantInfo={item} 
                key={index}
                showCheckBox={showCheckBox}
                />
            ))}
        </>
    )
}