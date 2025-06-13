import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom"
import TabComponent from "../common/TabComponent";
import RestaurantOrderShoppingListComponent from "../list/RestaurantOrderShoppingListComponent";
import PaymentBeforeComponent from "../payment/PaymentBeforeComponent";

//order/user/shopping/cart
export default function ShoppingCartComponent(){
    const location = useLocation();
    const [currOrderState, setCurrOrderState] = useState(location.state)
    const [paymentOrderInfo, setPaymentOrderInfo] = useState(currOrderState.paymentOrderInfo);
    /*
        {currOrderState, paymentOrderInfo}
    */
   const handlePayment = () => {
        console.log("handlePayment");
        
   }
    useEffect(()=>{
         console.log(currOrderState);
         
    })
    return (
        <>
            <TabComponent/>
            <h2>{currOrderState.clientOrderCart.restaurantInfo.storeName}</h2>
            {currOrderState && currOrderState.clientOrderCart.menuData.map((item, index)=>{
                return <RestaurantOrderShoppingListComponent key={index} menuData={item}/>
            })}
            
            {
                // 결재하기 팝업
                <PaymentBeforeComponent paymentOrderInfo={paymentOrderInfo} onCustomClick={handlePayment} isPayment={true}/>    
            }
        </>
    )
}