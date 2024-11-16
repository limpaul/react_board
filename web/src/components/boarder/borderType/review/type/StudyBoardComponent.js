import axios from "axios";
import { useEffect, useState } from "react"

export default function StudyBoardComponent({searchData}){
    const [searchResultItems, setSearchResultItems]  = useState(null);

    function getSearchData(data){ // data는 검색할 품복
        axios.get('/testdata/reviewBoard/study/data.json')
        .then((res)=>{
            const items = res.data.items;
            setSearchResultItems(items);
        })
    }
    useEffect(()=>{
        console.log('StudyCompoenent.js : '+searchData);
        getSearchData(searchData);
    }, [])
    return (
        <>
            <h1>StudyBoardComponent.js</h1>
            <div>
                <div>
                    {searchResultItems && searchResultItems.length > 0 && (
                        searchResultItems.map((data, index)=>{
                            return <ul key={index}>
                                <li>제목: {data.title}</li>
                                <li>참조 링크: <a href={data.link}>클릭</a></li>
                                <li><img src={data.image}/></li>
                                <li>저자: {data.author}</li>
                                <li>가격: {data.discount}</li>
                                <li>설명: {data.description}</li>
                            </ul>
                        })
                    )}

                </div>
            </div>
        </>
    )
}