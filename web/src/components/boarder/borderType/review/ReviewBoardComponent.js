import { useEffect, useRef, useState } from 'react'
import '../../../../styles/border/ReviewBoardComponent.css'
import StudyBoardComponent from './type/StudyBoardComponent';
import NewsBoardComponent from './type/NewsBoardComponent';
export default function ReviewBoardComponent(){
    const [reviewType, setReviewType] = useState(null);
    const [searchData, setSearchData] = useState('');

    const searchText = useRef(null);
    useEffect(()=>{
        if(reviewType === null){
            setReviewType('studyTab');
        }
    }, [])

    const searchButtonCLicked = (e) => {
        setSearchData(searchText.current.value);
    }

    const whichReivewClicked = (e) => {
        if(e.target.id === 'studyTab'){
            setReviewType('studyTab')
        }
        if(e.target.id === 'newsTab'){
            setReviewType('newsTab')
        }
    }

    return (
        <div>
            <h2>리뷰 게시판</h2>
            <div className='reviewType'>
                <ul>
                    <li id='studyTab' onClick={whichReivewClicked}>도서</li>
                    <li id='newsTab' onClick={whichReivewClicked}>뉴스</li>
                </ul>
                <div>
                    <input type='text' ref={searchText} placeholder='주제 내용 검색' /><button onClick={searchButtonCLicked}>검색</button>
                </div>
            </div>


            {reviewType === 'studyTab' && searchData !== '' &&(
                <StudyBoardComponent searchData={searchData}/>
            )}
            {reviewType === 'newsTab' && searchData !== '' &&(
                <NewsBoardComponent searchData={searchData}/>
            )}

        </div>
    )
}