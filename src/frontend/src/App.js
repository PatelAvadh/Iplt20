import React from 'react'
import './App.css';
import { TeamPage } from "./pages/TeamPage";
import { Route} from 'react-router-dom';
import { Routes} from 'react-router-dom';
import { MatchPage } from './pages/MatchPage';

function App() {
  return (
    <div className="App">
        <Routes>
            <Route path='/teams/:teamName/matches/:year' element={<MatchPage />}></Route>
            <Route path='/teams/:teamName' element={<TeamPage />}></Route>
        </Routes>
    </div>
  );
}

export default App;
