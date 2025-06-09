import { useEffect } from "react"

export default function RestaurantEnrollMenuComponent({restaurantMenu, onCustomClick}){
    const onClickMenu = () => { // 메뉴를 클릭하였을때 로컬 스토리지에 저장하다 
        // 상점 아이디, 메뉴 아이디, 메뉴, 가격, 배달비
        const storageTest = {
            'storeId': 2,
            'deliveryTime':{'min':1, 'max':3},
            'order': [
                {
                    'menu':'메뉴1',
                    'price':'가격1',
                    'image':'이미지1'
                },
                {
                    'menu':'메뉴2',
                    'price':'가격2',
                    'image':'이미지2'
                }
            ],
            'totalPrice':'최종결재 금액'
        };

        const orderStorageTest = {};

        
    }
    useEffect(()=>{
        //console.log(restaurantMenu);
    }, [])
    return (
        <>
            <div id={restaurantMenu.id} style={{
                'display':'flex',
                'justifyContent':'space-between',
                'border':'1px solid #c0c0c0',
                'margin':'10px',
                'padding': '5px',
                'borderRadius':'10px'
            }}
            onClick={()=>{onCustomClick(restaurantMenu)}}
            >
                <div>
                    <div>{restaurantMenu.name}</div>
                    <div>{restaurantMenu.price}원</div>
                </div> 
                <div><img src={restaurantMenu.image} width="100px" height="100px"/></div>
            </div>
        </>
    )
}