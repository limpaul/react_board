import { useEffect, useState } from "react";
import { Navigate, useLocation, useNavigate } from "react-router-dom"
import TabComponent from "../common/TabComponent";
import RestaurantOrderShoppingListComponent from "../list/RestaurantOrderShoppingListComponent";
import PaymentBeforeComponent from "../payment/PaymentBeforeComponent";
import DownToUpComponent from "../common/DownToUpComponent";
import PaymentInputComponent from "../payment/PaymentInputComponent";
import { checkLogin, request } from "../common/DataToServer";
import YesOrNoDivPopupComponent from "../common/check/YesOrNoDivPopupComponent";

//order/user/shopping/cart
export default function ShoppingCartComponent(){
    const navigate = useNavigate();
    const location = useLocation();
    const [showOrderSelectPopup, setShowOrderSelectPopup] = useState(false);
    const [showCardDialogPopup, setShowCardDialogPopup] = useState(false);
    const [currOrderState, setCurrOrderState] = useState(JSON.parse(sessionStorage.getItem('saveMenuCartStorage')));
    const [visiblePaymentRelative, setVisiblePaymentRelative] = useState(true);
    const [visibleYesOrNo, setVisibleYesOrNo] = useState(false);
    const [paymentOrderInfo, setPaymentOrderInfo] = useState({
        'totalprice': currOrderState.totalMount,
        'ordercount': currOrderState.orderCount
    });
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
        const transkeyInput = document.getElementById('transkeyinput');
        if(transkeyInput.value.length === 4){
            // 서버로 결재를 진행한다 
            // 사용자 정보, 헤더에 있는 정보를 서버에 넣으면 된다  

            // 가게 정보를 서버에 보낸다 
            request({
                method:'POST',
                url:'/api/order/user/order',
                contentType:'application/json',
                body:JSON.stringify(currOrderState),
                authentication:true
            }).then(res =>{
                console.log(res);
                setShowCardDialogPopup(false);
                setVisiblePaymentRelative(false);
                setVisibleYesOrNo(true);
            })
        }
   }
   window.mTranskey.prototype.doneCallBack = () => {
        setShowCardDialogPopup(false);
        setVisiblePaymentRelative(false);
   }
   const handlePayment = (e) => { // 결재 버튼 누르면 
        setShowCardDialogPopup(true)
   }
    return (
        <>
            <TabComponent/>
            <h2>{currOrderState.restaurantInfo.name}</h2>
            {currOrderState && currOrderState.menuData.map((item, index)=>{
                return <RestaurantOrderShoppingListComponent key={index} menuData={item} setCurrOrderState={setCurrOrderState}/>
            })}
            {
                // 결재화면, 모바일 웹 가상키패드와 같이 활성화 
                showCardDialogPopup && <PaymentInputComponent/>   
            }
            {
                // 결재 수단 화면
                 visiblePaymentRelative && <DownToUpComponent/>
            }
            {
                // 결재하기 팝업
                visiblePaymentRelative && <PaymentBeforeComponent paymentOrderInfo={paymentOrderInfo} onCustomClick={handlePayment} isPayment={true}/>    
            }
            {
                visibleYesOrNo && <YesOrNoDivPopupComponent notifyContent={"주문을 완료하였습니다"} yesText={"예"} yesEvent={()=>{
                    sessionStorage.removeItem('saveMenuCartStorage');
                    navigate('/order/restaurant/list');
                }}  onlyOne={true}/>
            }
        </>
    )
}