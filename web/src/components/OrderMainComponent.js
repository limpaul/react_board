import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import axios from "axios";
import RestaurantListComponent from "./order/list/RestaurantListComponent";

export default function OrderMainComponent(){
    const navigate = useNavigate();
    const [menuData, setMenuData] = useState(null);

    const handleMenuData = (data) => {
        setMenuData(data)
    }
    

    // useEffect(()=>{
    //     navigate('/order/login');
    // }, [])
    useEffect(()=>{
        //navigate('/order/login');
        navigate('/order/restaurant/list');
        console.log('effect?');
        
        axios.get('/testdata/order/orderlist.json')
        .then((response)=>{
            handleMenuData(response.data)
        })
    }, [])

    return (<>
        {menuData && menuData.map((item, index)=>(
          <RestaurantListComponent key={index} menuData={item}/>  
        ))}
    </>);
}