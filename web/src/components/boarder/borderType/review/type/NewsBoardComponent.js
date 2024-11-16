import axios from "axios";
import { useEffect, useState } from "react"

export default function NewsBoardComponent({searchData}){
    const [searchResultItems, setSearchResultItems]  = useState(null);
    const stripTags = (html) => html.replace(/<\/?[^>]+(>|$)/g, ""); // HTML 태그 제거
    function getSearchData(data){ // data는 검색할 품복
        axios.get('/testdata/reviewBoard/news/data.json')
        .then((res)=>{
            const items = res.data.items;
            setSearchResultItems(items);
        })
    }
    useEffect(()=>{
        console.log('NewsBoardComponent.js : '+searchData);
        getSearchData(searchData);
    }, [])
    return (
        <>
            <h1>NewsBoardComponent.js</h1>
            <div>
                <div>
                    {searchResultItems && searchResultItems.length > 0 && (
                        searchResultItems.map((data, index)=>{
                            return <ul key={index}>
                                <li>{stripTags(data.title)}</li>
                                <li>참조 링크: <a href={stripTags(data.originallink)}>클릭</a></li>
                                <li>{stripTags(data.description)}</li>
                            </ul>
                        })
                    )}

                </div>
            </div>
        </>
    )
}