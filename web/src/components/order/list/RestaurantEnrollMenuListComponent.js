import { useEffect, useState } from "react";
import RestaurantEnrollMenuComponent from "./RestaurantEnrollMenuComponent";
import { useLocation } from "react-router-dom";
import axios from "axios";

/*
    식당에서 등록한 상세 이미지들을 로드한다
*/
export default function RestaurantEnrollMenuListComponent(){
    const location = useLocation();
    const restaurantData = location.state;
    const [testMenuData, setTestMenuData] = useState(null);
    const [menuHandle, setMenuHandle] = useState(null);
    const handleMenu = (menuData) => { // 메뉴로 부터 정보를 가져온다 
        // 식당 정보 
        console.log(restaurantData);
        // 클릭한 메뉴 정보들 
        console.log(menuData);

        // 장바구니에 담는다 
        
    }
    useEffect(()=>{
        console.log(restaurantData); // 고객이 식당별 리스트를 누르고 들어왔을때 이전 메뉴로 부터 가져온 정보들 
        // 식당 아이디 + 19 더한 만큼의 데이터를 가져와본다 
        axios.get('/testdata/order/menu/menu.json')
        .then((res)=>{
             const start = restaurantData.storeId; // 식당 ID 1,2,3,5
             const end = start * 20
             const menuDataList = res.data.slice((start-1)*20, end); // 테스트 데이터를 임의로 20개씩 끊어 가져온다 
             setTestMenuData(menuDataList);
        })
    }, [])
    return (
        <>
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
                        <div>가게배달: {restaurantData.deliveryTime.min}~{restaurantData.deliveryTime.max}분</div>
                        <div>배달비: {restaurantData.deliveryFee=='0'?'무료배달':restaurantData.deliveryFee}</div>
                    </div>
                    
                </div>

                <div>
                    {testMenuData && testMenuData.map((item, index) => { // 식당에서 등록한 메뉴들을 나열한다
                        return <RestaurantEnrollMenuComponent key={index} restaurantMenu={item} onCustomClick={handleMenu}/>
                    })}
                </div>
            </div>
        </>
    )
}