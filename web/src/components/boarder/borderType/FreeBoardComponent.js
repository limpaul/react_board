import { useState } from "react"
import CommonBoardPopupComponent from "./CommonBoardPopupComponent";

export default function FreeBoardComponent({boardData}){
    const [isVisible, setIsVisible] = useState(false);
    const [popupInfo, setPopupInfo] = useState(null);

    const visiblePopup = (event, data) =>{
        setIsVisible(!isVisible);
        const popupType = event.target.getAttribute('popuptype');
        
        /* 어느 게시판, 게시판 정보  */
        setPopupInfo({
            'type':popupType,
            'data':data,
        })
    }



    return (
        <>
        <div className="fade-in">
                    <h2>자유게시판</h2>
                    <table className="bbsTable">
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>제목</th>
                                <th>작성자</th>
                                <th>날짜</th>
                            </tr>
                        </thead>
                        <tbody>
                        {boardData && boardData.length > 0 ? (
                                boardData.map((data, index)=>{
                                    return <tr key={index} popuptype="freeBoardReadPopup" onClick={(event)=>{visiblePopup(event, data)}}>
                                        <td popuptype="freeBoardReadPopup">{data.no}</td>
                                        <td popuptype="freeBoardReadPopup">{data.title}</td>
                                        <td popuptype="freeBoardReadPopup">{data.author}</td>
                                        <td popuptype="freeBoardReadPopup">{data.date}</td>
                                    </tr>
                                })
                            ):(
                              <>
                              </>
                            )}
                        </tbody>
                       
                    </table>
            </div>
            <div className="writeButtonContainer fade-in">
                <button popuptype="freeBoardWritePopup"  onClick={visiblePopup} className="writeButton">글쓰기</button>
            </div>

            {isVisible && popupInfo.type && <CommonBoardPopupComponent onClose={visiblePopup}  props={{
                'popupType':popupInfo.type,
                'popupData':popupInfo.data
            }}/>}
        </>
    )
}