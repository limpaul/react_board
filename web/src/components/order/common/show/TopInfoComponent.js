import '../../../../styles/order/common/show/topInfo.css'
export default function TopInfoComponent({text}){
    return (
        <>
            <div id='topInfo'>
                <b>{text}</b>
            </div>
        </>
    )
}