import { useEffect } from "react"

export default function BodyComponent({category}){

    useEffect(()=>{
      
        if(category == null){
            return;
        }
        if(category.id === 'freeBoard'){
            console.log('freeBorad');
        }
        if(category.id === 'reviewBoard'){
            console.log('reviewBoard');
        }
        if(category.id === 'naverReviewBoard'){
            console.log('naverReviewBoard');
        }
        if(category.id === 'dataBoard'){
            console.log('dataBoard');
        }
        
    })
    return (
        <div>
            <h2> 선택되었습니다</h2>        
        </div>
    )
}