import React from 'react';
import { Link } from 'react-router-dom';

function Main() {
  return (
    <div>
      <h2>Welcome to My Fridge App</h2>
      <div>
        <Link to="/signin">
          <button>Sign In</button>
        </Link>
        <Link to="/signup">
          <button>Sign Up</button>
        </Link>
      </div>
    </div>
  );
}

export default Main;