import React, { useState } from 'react';
import { useHistory } from 'react-router-dom';
import axios from 'axios';

function SignUp() {
  const history = useHistory();
  const [formData, setFormData] = useState({
    username: '',
    password: '',
    nickname: '',
  });

  const handleSignUp = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('/api/signup', formData); // Adjust the API endpoint
      if (response.status === 200) {
        history.push('/signin');
      }
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div>
      <h2>Sign Up</h2>
      <form onSubmit={handleSignUp}>
        {/* Form fields */}
        <button type="submit">Sign Up</button>
      </form>
    </div>
  );
}

export default SignUp;