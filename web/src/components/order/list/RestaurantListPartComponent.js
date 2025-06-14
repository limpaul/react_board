import TabComponent from "../common/TabComponent";
import { useNavigate } from "react-router-dom";

/*
    메뉴 리스트들 화면에 뿌려줄때 하나의 컴포넌트
*/
export default function RestaurantListPartComponent({restaurantInfo}){
    const navigate = useNavigate();
    return (
        <>
            <TabComponent/>
            <div className="menuLists" 
            onClick={()=>{
                navigate(`/order/restaurant/view/${restaurantInfo.storeId}`, {
                    state: restaurantInfo
                })
            }}
            style={{ 
                'border':'1px solid #c0c0c0',
                'margin':'10px',
                'padding':'5px'
            }}>
                <div><img src={restaurantInfo.image} width="80px" height="80px"/></div>
                <div>{restaurantInfo.storeName} 평점: <b>{restaurantInfo.rating}</b></div>
                <div>{restaurantInfo.deliveryTime.min}~{restaurantInfo.deliveryTime.max}분 / {restaurantInfo.deliveryFee=='0'?'무료배달':restaurantInfo.deliveryFee} / 최소주문 {restaurantInfo.minOrderPrice}</div>
            </div>
        </>
        
    )
}