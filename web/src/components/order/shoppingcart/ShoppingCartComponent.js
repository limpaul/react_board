import { useEffect, useState } from "react";
import { Navigate, useLocation, useNavigate } from "react-router-dom"
import TabComponent from "../common/TabComponent";
import RestaurantOrderShoppingListComponent from "../list/RestaurantOrderShoppingListComponent";
import PaymentBeforeComponent from "../payment/PaymentBeforeComponent";
import DownToUpComponent from "../common/DownToUpComponent";
import PaymentInputComponent from "../payment/PaymentInputComponent";
import { checkLogin } from "../common/DataToServer";

//order/user/shopping/cart
export default function ShoppingCartComponent(){
    const navigate = useNavigate();
    const location = useLocation();
    const [showOrderSelectPopup, setShowOrderSelectPopup] = useState(false);
    const [showCardDialogPopup, setShowCardDialogPopup] = useState(false);
    const [currOrderState, setCurrOrderState] = useState(location.state)
    const [paymentOrderInfo, setPaymentOrderInfo] = useState(currOrderState.paymentOrderInfo);
    /*
        {currOrderState, paymentOrderInfo}
    */

   useEffect(()=>{
        checkLogin().then(response=>{
            if(response.authentication !== true ){
                navigate(response.redirectTo);
            }
        })
        
        window.initmTranskey();
   }, [])
   window.mTranskey.prototype.closeCallBack = () => {
        setShowCardDialogPopup(false);
   }
   window.mTranskey.prototype.doneCallBack = () => {
        setShowCardDialogPopup(false);
   }
   const handlePayment = () => {
        setShowCardDialogPopup(true)
        //setShowOrderSelectPopup(true);
   }
    useEffect(()=>{
         console.log(currOrderState);
         
    })
    return (
        <>
            <TabComponent/>
            <h2>{currOrderState.clientOrderCart.restaurantInfo.storeName}</h2>
            {currOrderState && currOrderState.clientOrderCart.menuData.map((item, index)=>{
                return <RestaurantOrderShoppingListComponent key={index} menuData={item} setCurrOrderState={setCurrOrderState}/>
            })}
            {
                // 결재화면, 모바일 웹 가상키패드와 같이 활성화 
                showCardDialogPopup && <PaymentInputComponent/>   
            }
            {
                // 결재 수단 화면
                 <DownToUpComponent/>
            }
            {
                // 결재하기 팝업
                <PaymentBeforeComponent paymentOrderInfo={paymentOrderInfo} onCustomClick={handlePayment} isPayment={true}/>    
            }
        </>
    )
}