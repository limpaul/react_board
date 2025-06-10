import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom"
import TabComponent from "../common/TabComponent";
import RestaurantOrderShoppingListComponent from "../list/RestaurantOrderShoppingListComponent";

export default function ShoppingCartComponent(){
    const location = useLocation();
    const [currOrderState, setCurrOrderState] = useState(location.state)
    useEffect(()=>{
        
    })
    return (
        <>
            <TabComponent/>
            <h2>{currOrderState.clientOrderCart.restaurantInfo.storeName}</h2>
            {currOrderState && currOrderState.clientOrderCart.menuData.map((item, index)=>{
                return <RestaurantOrderShoppingListComponent key={index} menuData={item}/>
            })}
        </>
    )
}