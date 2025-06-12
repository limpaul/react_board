import { useEffect } from "react"
import CountBoxComponent from "../common/CountBoxComponent";

/*
    가게에서 장바구니로 담은 UI메뉴 화면을 반환한다 
    
 */
export default function RestaurantOrderShoppingListComponent({menuData}){
    useEffect(()=>{
        console.log(menuData);
    })
    return(
        <>
            
                <div style={{
                    'border':'1px solid #c0c0c0',
                    'marginBottom':'5px'
                }}>
                    <div style={{
                        'display':'flex',
                        'justifyContent':'space-between',
                    }}>
                        <div>
                            <div>{menuData.name}</div>
                            <div>{menuData.price}원</div>
                        </div>
                        <div>
                            <img src={menuData.image} width='100px' height='100px'/>
                        </div>
                    </div>
                    {
                        /*
                            CounBoxComponent는 최종 결재 하기전에 메뉴의 수량을 최종 수정해주는 역활하는 컴포넌트이다 
                        */
                    }
                    <CountBoxComponent menuData={menuData}/>
                </div>
                
        </>
    )
}