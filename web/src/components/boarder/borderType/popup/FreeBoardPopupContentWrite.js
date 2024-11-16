import '../../../../styles/border/popup/FreeeBoardPopupContent.css'
export default function FreeBoardPopupContent({onClose}){
    return (
        <>
            <div className='freeBoardPopupDiv'>
                 <input type="text" id="title" placeholder="제목" />
                 <textarea placeholder='내용'></textarea>
                 <input type="password" placeholder="게시판 작성 비밀번호" />
                 <div>
                    <button id="compelete" className="btn">작성완료</button>
                    <button id="tmpSave" className="btn">임시저장</button>
                    <button id="cancel" className="btn" onClick={onClose}>취소</button>
                 </div>
            </div>
        </>
    )
}