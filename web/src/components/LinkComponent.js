import { Link } from "react-router-dom";
function LinkComponent(){
    return (
        <>
            <ul>
                <li><Link to="/">main</Link></li>
                <li><Link to="/test">test</Link></li>
                <li><Link to="/tab">tab</Link></li>
            </ul>
        </>
    )
}
export default LinkComponent;