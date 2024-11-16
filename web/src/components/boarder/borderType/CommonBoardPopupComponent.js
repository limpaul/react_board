import { useEffect} from 'react'
import '../../../styles/border/popup/CommonpopupComponent.css'
import FreeBoardPopupContentWrite from './popup/FreeBoardPopupContentWrite';
import FreeBoardPopupContentRead from './popup/FreeBoardPopupContentRead';


export default function CommonBoardPopupComponent({props, onClose}){

    useEffect(()=>{     
        console.log(props.popupType);
        
    },[])


    return (
        <>


            {props.popupType && props.popupType === 'freeBoardWritePopup' && (
                <div id="CommonBoardPopupComponent" className="popup" >
                    <div className="popup-content-freeBoardWrite">
                        <span className="close" onClick={onClose}>&times;</span>
                        <FreeBoardPopupContentWrite onClose={onClose}/>
                    </div>
                </div>
            )}
            
            {props.popupType && props.popupType === 'freeBoardReadPopup' && (
                <div id="CommonBoardPopupComponent" className="popup" >
                    <div className="popup-content-freeBoardRead">
                        <span className="close" onClick={onClose}>&times;</span>
                        <FreeBoardPopupContentRead onClose={onClose}/>
                    </div>
                </div>
            )}
                
        </>
    )
}