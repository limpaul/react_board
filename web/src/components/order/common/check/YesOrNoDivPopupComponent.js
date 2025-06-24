import '../../../../styles/order/common/check/yesorno.css'
export default function YesOrNoDivPopupComponent({notifyContent, yesText, noText, yesEvent, noEvent}){
    return(
        <>
            <div id='mainNotfyPopup'>
                <div id='mainNotify'>
                    <div>{notifyContent}</div>
                    <div id='selectNotify'>
                        <div id='yes' onClick={()=>{yesEvent()}}>{yesText}</div>
                        <div id='no' onClick={()=>{noEvent()}}>{noText}</div>
                    </div>
                </div>
            </div>
        </>
    )
}