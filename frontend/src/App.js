import React from 'react';
import './App.css';
import SimulationParameters from './components/SimulationParameters/SimulationParameters';
import UserProfileList from './components/UserProfileList.js/UserProfileList';
function App() {
  return (
    <div className="App">
      <UserProfileList></UserProfileList>
    </div>
  );
}

export default App;
