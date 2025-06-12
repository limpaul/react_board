import { useEffect, useRef, useState } from 'react'
import '../../../styles/order/common/countbox.css'
export default function CountBoxComponent({menuData}){
    const  [count ,setCount] = useState(0);

    const handleMinButton = () => {
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
            <div className='commonCountDiv'>{count}</div>
            <div onClick={handleAddButton} className='commonCountDiv'>+</div>
        </div>
    )
}