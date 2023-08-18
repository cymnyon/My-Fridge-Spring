import React from 'react';
import axios from 'axios';
import { Link, useHistory } from 'react-router-dom';

function Dashboard() {
  const history = useHistory(); // Initialize the useHistory hook

  const handleSignOut = async () => {
    try {
      // Make a logout API request here
      await axios.post('/api/logout');
      history.push('/signin'); // Redirect the user to the sign-in page
    } catch (error) {
      console.error('Error logging out:', error);
    }
  };

  return (
    <div>
      <h2>Welcome to the Dashboard</h2>
      <Link to="/categories">Categories</Link>
      <button onClick={handleSignOut}>Sign Out</button>
    </div>
  );
}

export default Dashboard;