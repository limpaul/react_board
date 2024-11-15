import { useEffect, useRef, useState } from 'react'
import '../../styles/border/TabComponent.css'
import BodyComponent from './BodyComponent';

export default function TabComponent(){
    const [selectItem, setSelectItem] = useState(null)
    const whichTab = useRef(null);
    const tabClick = (event) =>{
        whichTab.current.innerText = event.target.innerText;
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
                        <li id='naverReviewBoard' onClick={tabClick} className='tab'>네이버 리뷰 게시판</li>
                        <li id='dataBoard' onClick={tabClick} className='tab'>자료실 게시판</li>
                    </ul>
                </div>
                <div>
                    <h2 ref={whichTab}></h2>
                </div>
                <div>
                    <BodyComponent category={selectItem} />
                </div>
            </div>
        </>
    )
  
    
}

