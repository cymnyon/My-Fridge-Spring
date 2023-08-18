import React, { useState, useEffect } from 'react';
import axios from 'axios';

function Dashboard() {
  const [categories, setCategories] = useState([]);

  const fetchCategories = async () => {
    try {
      const response = await axios.get('/api/categories');
      setCategories(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    fetchCategories();
  }, []);

  return (
    <div>
      <h2>Dashboard</h2>
      <button>Sign Out</button>
      <ul>
        {categories.map((category) => (
          <li key={category.id}>{category.name}</li>
        ))}
      </ul>
      {/* Add forms and buttons for adding, editing, and removing categories */}
    </div>
  );
}

export default Dashboard;