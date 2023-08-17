import React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import Home from './components/Home';
import SignIn from './components/SignIn';
import SignUp from './components/SignUp';
import Dashboard from './components/Dashboard';
import Categories from './components/Categories';
import axios from 'axios';

function App() {
  // Set the base URL for Axios requests
  axios.defaults.baseURL = '/api';

  return (
    <Router>
      <div className="App">
        <Switch>
          <Route path="/" exact component={Home} />
          <Route path="/signin" component={SignIn} />
          <Route path="/signup" component={SignUp} />
          <Route path="/dashboard" component={Dashboard} />
          <Route path="/categories" component={Categories} />
        </Switch>
      </div>
    </Router>
  );
}

export default App;