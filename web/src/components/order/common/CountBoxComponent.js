import { useEffect, useRef } from 'react'
import '../../../styles/order/common/countbox.css'
export default function CountBoxComponent({menuData}){
    const num = useRef(null)
    const handleClick = (e) => {
        if(e.target.id.indexOf('min')){
            console.log(num.current);
            debugger;
        }
        if(e.target.id.indexOf('min')){
            
        }
    }
    
    return (
        <div style={{
            'display':'flex',
            'justifyContent':'right'
        }}>
            <button>옵션변경</button>
            <div id={menuData.id+'_minus'} onClick={handleClick} className='commonCountDiv'>-</div>
            <div id={menuData.id+'_num'} ref={num}  className='commonCountDiv'><b>0</b></div>
            <div id={menuData.id+'_min'} onClick={handleClick} className='commonCountDiv'>+</div>
        </div>
    )
}