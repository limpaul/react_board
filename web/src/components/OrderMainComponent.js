import { useState } from "react";
import LinkComponent from "./LinkComponent";
import EnrollRestaurantComponent from "./order/agreement/EnrollRestaurantComponent";
import LoginComponent from "./order/LoginComponent";
import UserAddComponent from "./order/UserAddComponent";

export default function OrderMainComponent(){
    const [visibleUserAdd, setVisibleUserAdd] = useState(false);
    const [visibleRestaurantAdd, setVisibleRestaurantAdd] = useState(false);

    const handleVisibleUserAdd = () => {
        setVisibleUserAdd(true)
    }

    
    return (<>
        <LoginComponent/>
    </>);
}