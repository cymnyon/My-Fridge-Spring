import React, { useState, useEffect } from 'react';
import axios from 'axios';

function CategoryPage() {
  const [categories, setCategories] = useState([]);
  const [newCategoryTitle, setNewCategoryTitle] = useState('');

  useEffect(() => {
    fetchCategories();
  }, []);

  async function fetchCategories() {
    try {
      const response = await axios.get('/api/categories');
      setCategories(response.data);
    } catch (error) {
      console.error('Error fetching categories:', error);
    }
  }

  async function handleAddCategory() {
    try {
      await axios.post('/api/categories', { title: newCategoryTitle });
      setNewCategoryTitle('');
      fetchCategories();
    } catch (error) {
      console.error('Error adding category:', error);
    }
  }

  async function handleRemoveCategory(id) {
    try {
      await axios.delete(`/api/categories/${id}`);
      fetchCategories();
    } catch (error) {
      console.error('Error removing category:', error);
    }
  }

  return (
    <div>
      <h2>Categories</h2>
      <div>
        <input
          type="text"
          placeholder="Enter category title"
          value={newCategoryTitle}
          onChange={(e) => setNewCategoryTitle(e.target.value)}
        />
        <button onClick={handleAddCategory}>Add Category</button>
      </div>
      <ul>
        {categories.map((category) => (
          <li key={category.id}>
            {category.title}
            <button onClick={() => handleRemoveCategory(category.id)}>Remove</button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default CategoryPage;