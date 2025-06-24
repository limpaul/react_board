import { useEffect, useState } from "react"
import axios from "axios";
import RestaurantListPartComponent from "./RestaurantListPartComponent";
import TabComponent from "../common/TabComponent";
import { request } from "../common/DataToServer";
import PaymentBeforeComponent from "../payment/PaymentBeforeComponent";
import { useLocation, useNavigate } from "react-router-dom";
import YesOrNoDivPopupComponent from "../common/check/YesOrNoDivPopupComponent";
import TopInfoComponent from "../common/show/TopInfoComponent";

// /order/restaurant/list
export default function RestaurantListComponent(){
    const location = useLocation();
    const navigate = useNavigate();
    const [userInfo ,setUserInfo] = useState(location.state);
    sessionStorage.setItem('userinfo', JSON.stringify(userInfo));
    const [restauantData, setRestauantData] = useState(null);
    const [paymentOrderInfo, setPaymentOrderInfo] = useState(null);
    const [yesOrNoPopupVerify, setYesOrNoPopupVerify] = useState(false);
    const handleShoppingCart = () => { // 장바구니 컴포넌트로 이동한다
        // 브라우저 세션 스토리지에 정보를 저장한다 
        
        let clientOrderCart = sessionStorage.getItem('saveMenuCartStorage');
        if(clientOrderCart != null){
            clientOrderCart = JSON.parse(clientOrderCart);
            navigate('/order/user/shopping/cart', {
                state: {
                    clientOrderCart:clientOrderCart, 
                    paymentOrderInfo: clientOrderCart.paymentOrderInfo
                }
            })
        }
    }

    useEffect(()=>{
            request({
                url:'/api/order/user/restaurant/list',
                method:'GET',
                authentication:'true'
            }).then((response)=>{
                setRestauantData(response)
            })
            let clientOrderCart = sessionStorage.getItem('saveMenuCartStorage');
            if(clientOrderCart != null){
                clientOrderCart = JSON.parse(clientOrderCart);
                setPaymentOrderInfo({
                    'ordercount': clientOrderCart.orderCount,
                    'totalprice': clientOrderCart.totalMount
                })
            }
            

    }, [])
 
    return (
        <>
            <TabComponent/>
            {userInfo && <TopInfoComponent text={userInfo.address}/>}
            {yesOrNoPopupVerify && <YesOrNoDivPopupComponent
                notifyContent={" 장바구니에는 같은 가게의 메뉴만 담을 수 있습니다"}
                yesText={"확인"}
                noText={"취소"}
                yesEvent={()=>{sessionStorage.removeItem('saveMenuCartStorage');setYesOrNoPopupVerify(false);}}
                noEvent={()=>{setYesOrNoPopupVerify(false)}}
            />}
            {restauantData && restauantData.map((item, index)=>(
                <RestaurantListPartComponent 
                restaurantInfo={item} 
                key={index}
                yesOrNoPopupVerify={setYesOrNoPopupVerify}
                />
            ))}

            {
                sessionStorage.getItem('saveMenuCartStorage') != null && paymentOrderInfo && <PaymentBeforeComponent paymentOrderInfo={paymentOrderInfo} onCustomClick={handleShoppingCart} isPayment={false}/>                 
            }
        </>
    )
}