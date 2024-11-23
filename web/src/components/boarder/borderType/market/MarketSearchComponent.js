import axios from 'axios'
import { useRef, useState } from 'react';
import '../../../../styles/border/market/MarketSearchComponent.css'
import MarketSearchResultComponent from './MarketSearchResultComponent';

export default function MarketSearchComponent(){
    const searchRef = useRef();
    const [searchText, setSearchText] = useState(null);

    const searchBtnClickEvent = () =>{
        setSearchText({
            type:'buy',
            text: searchRef.current.value
        });
    }
  
    return (
        <>
            <div>
                <div className='which-trade-type'>
                    <ul>
                        <li>삽니다</li>
                        <li>팝니다</li>
                    </ul>
                </div>

                <div>
                    <div className='categories'>
                        <ul>
                            <li>디지털 기기</li>
                            <li>생활가전</li>
                            <li>가구/ 인테리어</li>
                            <li>도서</li>
                        </ul>
                    </div>
                    <div className='searchText'>
                        <input type="text" ref={searchRef}/>
                        <button onClick={searchBtnClickEvent}>검색</button>
                    </div>
                    
                    {searchText && (
                        <div className='searchResult'>
                            <MarketSearchResultComponent props={searchText}/>
                        </div>
                    )}
                    
                </div>
            </div>
        </>
    )
}