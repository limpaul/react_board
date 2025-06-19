import '../../../../styles/order/common/check/selectbox.css'
export default function SelectBoxComponent({text, handleSelectBoxClick}){
    return (
        <>
            <div className='selectDiv'>
                <div className='yes divCommon' id='yes'
                onClick={(e)=>{
                    handleSelectBoxClick(e)
                }}
                >{text.yes}</div>
                
                
                <div className='no divCommon' id='no'
                onClick={(e)=>{
                    handleSelectBoxClick(e)
                }}
                >{text.no}</div>
            </div>
        </>
    )
}