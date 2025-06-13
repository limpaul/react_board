import { useEffect, useRef, useState } from 'react'
import '../../../styles/order/common/countbox.css'
export default function CountBoxComponent({menuData, setCurrOrderState}){
    const  [count ,setCount] = useState(menuData.count);


    const handleMinButton = () => {
        console.log(count);
        
        if ( count - 1 < 0){
            return;
        }
        const temp = count - 1;
        setCount(temp);
    }
    const handleAddButton = () => {
        const temp = count + 1;
        setCount(temp);
    }
    
    return (
        <div style={{
            'display':'flex',
            'justifyContent':'right'
        }}>
            <button style={{
                'marginRight':'10px'
            }}>옵션변경</button>
            <div onClick={handleMinButton} className='commonCountDiv'>-</div>
            <div className='commonCountDiv'>{menuData.count}</div>
            <div onClick={handleAddButton} className='commonCountDiv'>+</div>
        </div>
    )
}