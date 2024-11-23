import axios from "axios";
import { useEffect, useState } from "react"

export default function MarketSearchResultComponent({props}){
    const [testData, setTestData] = useState(null);

    useEffect(()=>{
        console.log(props);
        searchTestData()
    },[])
    const searchTestData = () =>{
        axios.get('/testdata/marketBoard/data.json')
        .then((response)=>{
            setTestData(response.data.items)
        })
    }
    return (
        <>
            {testData && testData.length > 0 && (
                testData.map((data, index)=>{
                    return (
                        <ul key={index}>
                            <li><img src={data.image} width="300px" height="300px" /></li>
                            <li>{data.lprice}원</li>
                            <li>{data.title}</li>
                        </ul>
                    )
                })
            )}
        </>
    )
}