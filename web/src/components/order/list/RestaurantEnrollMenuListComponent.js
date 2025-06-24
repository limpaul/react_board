import { useEffect, useState } from "react";
import RestaurantEnrollMenuComponent from "./RestaurantEnrollMenuComponent";
import { useLocation, useNavigate } from "react-router-dom";
import axios from "axios";
import PaymentBeforeComponent from "../payment/PaymentBeforeComponent";
import TabComponent from "../common/TabComponent";
import { request } from "../common/DataToServer";

/*
    식당에서 등록한 상세 이미지들을 로드한다
    order/restaurant/view/
*/
export default function RestaurantEnrollMenuListComponent(){
    const navigate = useNavigate();
    const location = useLocation();
    const restaurantData = location.state;
    const [testMenuData, setTestMenuData] = useState(null);
    /*
        사용자 장바구니
    */
    const [clientOrderCart, setClientOrderCart] = useState({
        restaurantInfo: restaurantData,
        menuData: [],
        totalMount: 0,
        orderCount:0,
    })

    
    const [paymentOrderInfo, setPaymentOrderInfo] = useState({
        'totalprice':0,
        'ordercount':0
    });
useEffect(()=>{
    let userInfo = sessionStorage.getItem('userinfo');
    if(userInfo != null){
        userInfo = JSON.parse(userInfo);
        setClientOrderCart(prev => ({
            ...prev,
            'address':userInfo.address // 사용자 주소 추가
        }))
    }
}, [])
useEffect(() => {
  console.log("🛒 장바구니 상태 변경:", clientOrderCart);
}, [clientOrderCart]);

useEffect(() => {
  console.log("💳 결제 정보 변경:", paymentOrderInfo);
}, [paymentOrderInfo]);


    const handleShoppingCart = () => { // 장바구니 컴포넌트로 이동한다
        // 브라우저 세션 스토리지에 정보를 저장한다 
        sessionStorage.setItem('saveMenuCartStorage', JSON.stringify(clientOrderCart))
        navigate('/order/user/shopping/cart', {
            state: {clientOrderCart, paymentOrderInfo}
        })
    }
    const handleMenu = (menuData) => { // 메뉴로 부터 정보를 가져온다 
        // 식당 정보 
        //console.log(restaurantData);
        // 클릭한 메뉴 정보들 
        //console.log(menuData);


        // 장바구니에 담는다 
        const clientOrderStorage = localStorage.getItem("clientOrderStorage");
        if(clientOrderStorage){ // 기존 장바구니에 담은게 있다면 
            
        }else{ 
            setClientOrderCart(prev => { // 기존 메뉴가 담겨있을 경우 0 이상을 반환하며, 새로 담긴 메뉴라면 -1을 반환한다  

            const exsitingMenuIndex = prev.menuData.findIndex(item=>{
                
                if(item.id === menuData.id){
                    menuData.count += 1; // 동일메뉴의 주문수량을 1로 늘린다
                    return true;
                }else{
                    return false;
                }
            })

            
            
            const updatedMenuData = exsitingMenuIndex!=-1?[...prev.menuData]:[...prev.menuData,menuData];
            const updatedTotal = prev.totalMount + menuData.price;
            var updatedCount = prev.orderCount + 1;
            

            // 동시에 결제 정보도 업데이트
            setPaymentOrderInfo({
                totalprice: updatedTotal,
                ordercount: updatedCount,
            });

                return {
                    ...prev,
                    menuData: updatedMenuData,
                    totalMount: updatedTotal,
                    orderCount: updatedCount,
                };
            });
            
        }

        // 메뉴가 담아질 경우, 메뉴 리스트 아래에 주문하기 메뉴창이 떠야한다 
        
    }
    useEffect(()=>{
        console.log(restaurantData); // 고객이 식당별 리스트를 누르고 들어왔을때 이전 메뉴로 부터 가져온 정보들 
        // 식당 아이디 + 19 더한 만큼의 데이터를 가져와본다 
        request({
            method:'GET',
            url:`/api/order/menu/list/${restaurantData.id}`,
            authentication:false
        }).then((res)=>{
            const start = restaurantData.storeId; // 식당 ID 1,2,3,5
            const menuDataList = res; // 테스트 데이터를 임의로 20개씩 끊어 가져온다 
            
            // 장바구니 동일 메뉴 선정시 count옵션 넣기
            const menuCountDataList = menuDataList.map(menu => ({
               ...menu,
               count: 0 // 장바구니에 담기지 않은 수량은 0으로 표현한다
            }))
            
            setTestMenuData(menuCountDataList);
       })
    }, [])
    return (
        <>
            <TabComponent/>
             <div>
                <div>
                    <div><img src={restaurantData.image} width="100%" height="100px"/></div>
                    <div><h1>{restaurantData.storeName}</h1></div>
                    <div style={{ 
                            'border':'1px solid #c0c0c0',
                            'margin':'10px',
                            'padding': '5px',
                            'borderRadius':'10px'
                        }}>
                        
                        <div>평점: {restaurantData.rating}</div>
                        <div>최소주문 {restaurantData.minOrderPrice} </div>
                        <div>가게배달: {restaurantData.deliveryTimeMin}~{restaurantData.deliveryTimeMax}분</div>
                        <div>배달비: {restaurantData.deliveryFee=='0'?'무료배달':restaurantData.deliveryFee}</div>
                    </div>
                    
                </div>
                {// 가격과 장바구니를  확인하는 UI
                paymentOrderInfo.ordercount !=0 && <PaymentBeforeComponent paymentOrderInfo={paymentOrderInfo} onCustomClick={handleShoppingCart} isPayment={false}/>
                }

                <div>
                    {testMenuData && testMenuData.map((item, index) => { // 식당에서 등록한 메뉴들을 나열한다
                        return <RestaurantEnrollMenuComponent key={index} restaurantMenu={item} onCustomClick={handleMenu}/>
                    })}
                </div>
            </div>
        </>
    )
}