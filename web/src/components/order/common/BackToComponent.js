import { useNavigate } from "react-router-dom"

export default function BackToComponent(){
    const navigate = useNavigate();

    const handleBack = () => {
        navigate(-1);
    }
    return (
        <>
            <div id="backToBtn" onClick={handleBack}>&lt;</div>
        </>
    )
}