import React, { useState } from 'react';
import OutputConsole from './components/OutputConsole/OutputConsole';


function App() {
  const [outputData, setOutputData] = useState([{date: new Date(), data: "This is a sample action log."}]);

  const appendOutputData = (data) => {
    setOutputData(outputData => [...outputData, data]);
    console.log(outputData)
  }

  return (
    <div>
      <h1>Welcome to the home page for our SOEN343 project!</h1>
      <OutputConsole 
        outputData={outputData}
      />
    </div>
  );
}

export default App;
