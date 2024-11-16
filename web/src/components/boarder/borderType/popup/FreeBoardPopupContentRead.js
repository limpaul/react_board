import '../../../../styles/border/popup/FreeeBoardPopupContent.css'
import '../../../../styles/border/popup/FreeBoardPopupRead.css'
export default function FreeBoardPopupContentRead({onClose}){
    return (
        <>
            <div className='freeBoardPopupDiv'>
                 <input type="text" id="title" placeholder="제목" />
                 <textarea placeholder='내용'></textarea>
                 
                 <div className="reviews">
                    <input type="text"  placeholder="댓글" />
                    <button className="btn">등록</button>
                 </div>
            </div>
        </>
    )
}