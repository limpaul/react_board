import '../../../../styles/order/common/check/yesorno.css'
export default function YesOrNoDivPopupComponent({notifyContent, yesText, noText, yesEvent, noEvent, onlyOne}){
    return(
        <>
            <div id='mainNotfyPopup'>
                <div id='mainNotify'>
                    <div>{notifyContent}</div>
                    <div id='selectNotify'>
                        <div id='yes' onClick={()=>{yesEvent()}}>{yesText}</div>
                        {
                        onlyOne == false && <div id='no' onClick={()=>{noEvent()}}>{noText}</div>
                        }
                    </div>
                </div>
            </div>
        </>
    )
}