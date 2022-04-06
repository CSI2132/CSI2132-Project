import logo from './logo.svg';
import './App.css';
import Login from './Login';
import Dentist from './Dentist';
import Receptionist from './Receptionist';
import { BrowserRouter as Router, Route, Routes} from 'react-router-dom';

function App() {
  return (
    <Router>
      <Routes>
        <Route exact path="/" element={<Login/>} ></Route>
        <Route exact path="/dentist" element={<Dentist/>}></Route>
        <Route exact path="/receptionist" element={<Receptionist />} ></Route>

      </Routes>
    </Router>
  );
}

export default App;
