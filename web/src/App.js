import {BrowserRouter, Routes, Route} from 'react-router-dom'
import MainComponent from './components/MainComponent';
import TestComponent from './components/TestComponent';
import TabComponent from './components/boarder/TabComponent';
import OrderMainComponent from './components/OrderMainComponent';
import UserAddComponent from './components/order/UserAddComponent';
import EnrollRestaurantComponent from './components/order/agreement/EnrollRestaurantComponent';
function App() {
  return (
    <div className="App">
      
      <BrowserRouter>
          <Routes>
              <Route path="/" element={<MainComponent/>} ></Route>
              <Route path="/test" element={<TestComponent/>} ></Route>
              <Route path="/tab" element={<TabComponent/>} ></Route>
              <Route path="/order" element={<OrderMainComponent/>}></Route>
              <Route path="/order/enroll/user" element={<UserAddComponent/>}></Route>
              <Route path="/order/enroll/restaurant" element={<EnrollRestaurantComponent/>}></Route>
          </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
