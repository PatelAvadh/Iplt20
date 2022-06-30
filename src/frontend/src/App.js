import React from 'react'
import './App.scss';
import { TeamPage } from "./pages/TeamPage";
import { Route, Routes} from 'react-router-dom';
import { MatchPage } from './pages/MatchPage';
import { HomePage } from './pages/HomePage';

function App() {
  return (
    <div className="App">
        <Routes>
            <Route path='/teams/:teamName/matches/:year' element={<MatchPage />}></Route>
            <Route path='/teams/:teamName' element={<TeamPage />}></Route>
            <Route path='/' element={<HomePage />}></Route>
        </Routes>
    </div>
  );
}

export default App;
