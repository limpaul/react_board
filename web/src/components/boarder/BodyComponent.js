import axios from "axios";
import { useEffect, useState } from "react"
import FreeBoardComponent from "./borderType/FreeBoardComponent";
import ReviewBoardComponent from "./borderType/review/ReviewBoardComponent";


export default function BodyComponent({category}){
    const [boardData, setBoardData ] = useState(null);

    useEffect(()=>{
        
        if(category == null){
            return;
        }
        if(category.id === 'freeBoard'){
            console.log('freeBorad');
            dataloadTest()
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
        
    }, [category]);
    function dataloadTest(){
        axios.get('/testdata/data.json')
        .then(response =>{
            setBoardData(response.data)
        })
    }


    return (
        <div>
            {category && category.id === 'freeBoard' &&(
                <FreeBoardComponent boardData={boardData}/>
            )}
            {category && category.id === 'reviewBoard' && (
                <ReviewBoardComponent/>
            )}
            {category && category.id === 'naverReviewBoard' && (
                <h2>네이버 리뷰 게시판</h2>
            )}
            {category && category.id === 'dataBoard' && (
                <h2>자료게시판</h2>
            )}
        </div>
    )
}