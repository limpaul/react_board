import { useEffect } from "react";
import RestaurantEnrollMenuComponent from "./RestaurantEnrollMenuComponent";
import { useLocation } from "react-router-dom";

export default function RestaurantEnrollMenuListComponent(){
    const location = useLocation();
    const restaurantData = location.state?.restaurantInfo;
    useEffect(()=>{
        console.log(restaurantData);
        
    }, [])
    return (
        <>
            <h2>RestaurantEnrollMenuListComponent</h2>
            {/* <div>
                <div>
                    <div>{restaurantData.image}</div>
                    <div>{restaurantData.storeName} / 평점: {restaurantData.rating}</div>
                    <div>최소주문 {restaurantData.minOrderPrice} </div>
                    <div>{restaurantData.deliveryTime.min}~{restaurantData.deliveryTime.max}분</div>
                    <div>배달비: {restaurantData.deliveryFee=='0'?'무료배달':restaurantData.deliveryFee}</div>
                </div>

                <RestaurantEnrollMenuComponent restaurantMenuEnrollId={restaurantData.storeId}/>
            </div> */}
        </>
    )
}