import '../../../../styles/border/popup/FreeeBoardPopupContent.css'
import '../../../../styles/border/popup/FreeBoardPopupRead.css'
import '../../../../styles/border/popup/FreeComponentReviews.css'
import axios from 'axios'
import { useEffect, useState } from 'react';

export default function FreeBoardPopupContentRead(){
    const [reviews, setReviews] = useState(null);

    function getReviews(){
        axios.get('/testdata/freeboardRead/reviews/data.json')
        .then((res)=>{
            setReviews(res.data);
        })
    }
    useEffect(()=>{
        getReviews();
    }, [])
    return (
        <>
            <div className='freeBoardPopupDiv'>
                 <input type="text" id="title" placeholder="제목" />
                 <textarea placeholder='내용'></textarea>

                 <div className="reviews">
                    <input type="text"  placeholder="댓글" />
                    <button className="btn">등록</button>
                    <div className="reviewers">
                        {reviews && reviews.length > 0 ? (
                            reviews.map((data, index)=>{
                                return(
                                    <ul key={index}>
                                        <li>
                                            <div className="reviewer">
                                                <ul>
                                                    <li>{data.author}</li>
                                                    <li>{data.content}</li>
                                                    <li>{data.timestamp}</li>
                                                </ul>
                                            </div>
                                        </li>
                                    </ul>
                                )
                            })
                            
                        ):(
                            <></>
                        )}

                    </div>
                 </div>
            </div>
        </>
    )
}