import {BrowserRouter, Routes, Route} from 'react-router-dom'
import MainComponent from './components/MainComponent';
import TestComponent from './components/TestComponent';
import TabComponent from './components/boarder/TabComponent';
import OrderMainComponent from './components/OrderMainComponent';
import UserAddComponent from './components/order/enroll/user/UserAddComponent';
import EnrollRestaurantComponent from './components/order/enroll/restaurant/EnrollRestaurantComponent';
import LoginComponent from './components/order/login/LoginComponent';
import RestaurantListComponent from './components/order/list/RestaurantListComponent';
import RestaurantEnrollMenuListComponent from './components/order/list/RestaurantEnrollMenuListComponent';
import ShoppingCartComponent from './components/order/shoppingcart/ShoppingCartComponent';
import MyPageComponent from './components/order/user/MyPageComponent';
import OwnerManagerComponent from './components/order/manager/owner/OwnerManagerComponent';
import OrderCompleteList from './components/order/list/order/OrderCompleteList';
function App() {
  return (
    <div className="App">
      
      <BrowserRouter>
          <Routes>
              <Route path="/" element={<MainComponent/>} ></Route>
              <Route path="/test" element={<TestComponent/>} ></Route>
              <Route path="/tab" element={<TabComponent/>} ></Route>
              <Route path="/order" element={<OrderMainComponent/>}></Route>
              <Route path="/order/user/login" element={<LoginComponent/>}></Route>
              <Route path="/order/user/enroll" element={<UserAddComponent/>}></Route>
              <Route path="/order/user/enroll/restaurant" element={<EnrollRestaurantComponent/>}></Route>
              <Route path="/order/restaurant/list" element={<RestaurantListComponent/>}></Route>
              <Route path="/order/restaurant/view/:id" element={<RestaurantEnrollMenuListComponent/>}></Route>
              <Route path="/order/user/shopping/cart" element={<ShoppingCartComponent/>}></Route>
              <Route path="/order/user/mypage" element={<MyPageComponent/>}></Route>
              <Route path="/order/user/order/compelete/list" element={<OrderCompleteList/>}></Route>
              <Route path="/order/user/mananger/restaurant" element={<OwnerManagerComponent/>}></Route>
              
          </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
