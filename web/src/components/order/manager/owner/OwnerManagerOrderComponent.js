import RestaurantListPartComponent from "../../list/RestaurantListPartComponent"
import TabComponent from "../../common/TabComponent"
import { useEffect, useState } from "react"
import ManagerSubComponent from "../../common/manager/ManagerSubComponent";
import { request, checkLogin } from "../../common/DataToServer";
import { useNavigate } from "react-router-dom";
import SelectBoxComponent from "../../common/check/SelectBoxComponent";
// /order/user/mananger/order 식당 주인이 주문을 받고 나서 화면에서 확인 

export default function OwnerManagerOrderComponent(){

    const [restauantData, setRestauantData] = useState(null);
    const naviator = useNavigate();
    const [showCheckBox, setShowCheckBox] = useState(false);
    const [checkBoxClick, setCheckBoxClick] = useState(false);
    const [deleteList, setDeleteList] = useState([]); // 삭제할 식당 리스트 ( 체크 박스 클릭 때마다 값들이 담기게 설정)

    const handleSelectBoxClick = (e) => {
        if(e.target.id === 'yes'){
            // 등록한 가게 삭제한다 
            request({
                method:'POST',
                url:'/api/order/user/manager/restaurant/delete',
                contentType:'application/json',
                body:{
                    deleteList
                },
                authentication:true
            }).then(res=>{
                if(res.status){ // 삭제가 완료 되면, 가게 리스트를 갱신한다 
                    setRestauantData(prev => {
                        return prev.filter(item => !deleteList.some(del => del.id === item.id))
                    })
                    setShowCheckBox(false);
                    setCheckBoxClick(false);
                    setDeleteList([]);
                }else{ // 그렇지 않을 경우, 리턴 받은 가게에 대해서만 삭제를 진행하지 않는다 
                    console.error('가게를 삭제할 수 없습니다')
                }
            })

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

    useEffect(()=>{
        console.log(restauantData);
    })


    return (<>
            <TabComponent/>
            <ManagerSubComponent setShowCheckBox={setShowCheckBox}/>
            <h2>사용자 주문 관리</h2>
            {restauantData && restauantData.map((item, index)=>(
                <RestaurantListPartComponent 
                restaurantInfo={item} 
                key={index}
                showCheckBox={showCheckBox}
                setCheckBoxClick = {setCheckBoxClick}
                setDeleteList = {setDeleteList}
                isManager={true}
                />
            ))}
            {checkBoxClick && <SelectBoxComponent text={{'yes':'삭제','no':'취소'}} handleSelectBoxClick={handleSelectBoxClick} />}
    </>)
}