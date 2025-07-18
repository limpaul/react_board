import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

/*
    메뉴 리스트들 화면에 뿌려줄때 하나의 컴포넌트
*/
export default function RestaurantListPartComponent({restaurantInfo, showCheckBox, setCheckBoxClick, setDeleteList, yesOrNoPopupVerify, isManager}){
    const navigate = useNavigate();
    return (
        <>
            <div className="menuLists" 
            onClick={()=>{
                if(typeof yesOrNoPopupVerify != undefined){
                    let saveMenuCartStorage = sessionStorage.getItem('saveMenuCartStorage');
                    if(saveMenuCartStorage != null){
                        saveMenuCartStorage = JSON.parse(saveMenuCartStorage);
                        if(saveMenuCartStorage.restaurantInfo.id != restaurantInfo.id){
                            yesOrNoPopupVerify(true);
                            return;
                        }
                    }
                }
                if(typeof isManager != 'undefined' && isManager){ 
                    // 식당 주인이 주문 확인 하러 갈때
                    navigate(`/order/user/mananger/order/${restaurantInfo.id}`, {
                        state: restaurantInfo
                    })
                }else{
                    //해당 식당으로 이동시켜준다
                    navigate(`/order/restaurant/view/${restaurantInfo.id}`, {
                        state: restaurantInfo
                    })
                }
                
            }}
            style={{ 
                'border':'1px solid #c0c0c0',
                'margin':'10px',
                'padding':'5px'
            }}>
                <div><img src={restaurantInfo.image} width="80px" height="80px"/></div>
                <div style={{
                    display:'flex',
                    justifyContent: 'space-between'
                }}>
                    <div>
                        <div>{restaurantInfo.name} 평점: <b>{restaurantInfo.rating}</b></div>
                        <div>{restaurantInfo.deliveryTimeMin}~{restaurantInfo.deliveryTimeMax}분 / {restaurantInfo.deliveryFee=='0'?'무료배달':restaurantInfo.deliveryFee} / 최소주문 {restaurantInfo.minOrderPrice}</div>
                    </div>
                   {showCheckBox && <div><input type="checkbox" style={{
                        width:'50px',
                        height:'50px'
                    }} onClick={(e)=>{
                        e.stopPropagation();
                        e.isPropagationStopped();
                        setCheckBoxClick(true);

                        if(e.target.checked){
                            setDeleteList(prev => {
                                if(prev.find(item => item.id === restaurantInfo.id)) return prev;
                                return [...prev, restaurantInfo];
                            })
                        }else{
                            setDeleteList(prev => {
                                return prev.filter(item => item.id !== restaurantInfo.id);
                            })
                        }
                        
                    }}></input></div>}
                </div>
            </div>
        </>
        
    )
}