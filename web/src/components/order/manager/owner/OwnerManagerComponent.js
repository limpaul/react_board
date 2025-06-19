import axios from "axios"
import RestaurantListPartComponent from "../../list/RestaurantListPartComponent"
import TabComponent from "../../common/TabComponent"
import { useEffect, useState } from "react"
import ManagerSubComponent from "../../common/manager/ManagerSubComponent";
import { request, checkLogin } from "../../common/DataToServer";
import { useNavigate } from "react-router-dom";
import SelectBoxComponent from "../../common/check/SelectBoxComponent";

// 가게 관리 - /order/user/mananger/restaurant
export default function OwnerManagerComponent(){
    const [restauantData, setRestauantData] = useState(null);
    const naviator = useNavigate();
    const [showCheckBox, setShowCheckBox] = useState(false);
    const [checkBoxClick, setCheckBoxClick] = useState(false);
    const [deleteList, setDeleteList] = useState([]); // 삭제할 식당 리스트 ( 체크 박스 클릭 때마다 값들이 담기게 설정)

    const handleSelectBoxClick = (e) => {
        if(e.target.id === 'yes'){
            // 등록한 가게 삭제한다 

        }
        if(e.target.id === 'no'){
            setCheckBoxClick(false);
        }
    }
    useEffect(()=>{
        checkLogin()
        .then(response => {
            naviator(response.redirectTo);
        })
        
        request({
            url:'/api/order/user/manager/restaurant/list',
            method:'GET',
            authentication:'true'
        }).then((response)=>{
            if(response === ''){
                localStorage.removeItem('token');
                naviator('/order/user/login');
            }
            setRestauantData(response)
        })
    }, [])

    useEffect(()=>{
        console.log(deleteList);
    }, [deleteList])

    return (
        <>
            <TabComponent/>
            <ManagerSubComponent setShowCheckBox={setShowCheckBox}/>
            <h2>사용자 가게 관리</h2>
            {restauantData && restauantData.map((item, index)=>(
                <RestaurantListPartComponent 
                restaurantInfo={item} 
                key={index}
                showCheckBox={showCheckBox}
                setCheckBoxClick = {setCheckBoxClick}
                setDeleteList = {setDeleteList}
                />
            ))}
            {checkBoxClick && <SelectBoxComponent text={{'yes':'삭제','no':'취소'}} handleSelectBoxClick={handleSelectBoxClick} />}
        </>
    )
}