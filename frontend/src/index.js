import { Paper, ThemeProvider } from '@material-ui/core';
import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import theme from './Assets/theme';

ReactDOM.render(

    <ThemeProvider theme={theme}>
      <Paper style={{ height: "100vh", minHeight:"100vh" }}>
        <App />
      </Paper>
    </ThemeProvider>,
  document.getElementById('root')
);
