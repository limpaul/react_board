import {BrowserRouter, Routes, Route} from 'react-router-dom'
import MainComponent from './components/MainComponent';
import TestComponent from './components/TestComponent';
import TabComponent from './components/boarder/TabComponent';
function App() {
  return (
    <div className="App">
      
      <BrowserRouter>
          <Routes>
              <Route path="/" element={<MainComponent/>} ></Route>
              <Route path="/test" element={<TestComponent/>} ></Route>
              <Route path="/tab" element={<TabComponent/>} ></Route>
          </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
