import { useEffect, useState } from 'react'
import '../../styles/border/TabComponent.css'
import BodyComponent from './BodyComponent';

export default function TabComponent(){
    const [selectItem, setSelectItem] = useState(null)
    const tabClick = (event) =>{
        setSelectItem(event.target)
    }
    useEffect(()=>{
       
    })

    return (
        <>
            <h1>TestComponent</h1>
            <div id='tabComponentRootDiv'>
                <div className="title">
                    <ul>
                        <li id='category' onClick={tabClick} className='tab'>게시판 카테고리</li>
                        <li id='freeBoard' onClick={tabClick} className='tab'>자유게시판</li>
                        <li id='reviewBoard' onClick={tabClick} className='tab'>리뷰게시판</li>
                        <li id='marketBoard' onClick={tabClick} className='tab'>장터 게시판</li>
                        <li id='dataBoard' onClick={tabClick} className='tab'>자료실 게시판</li>
                    </ul>
                </div>
    
                <div>
                    <BodyComponent category={selectItem} />
                </div>
            </div>
        </>
    )
  
    
}

