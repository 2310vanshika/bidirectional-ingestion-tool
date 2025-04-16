import React, { useState } from 'react';

const ColumnSelector = ({ columns }) => {
  const [selected, setSelected] = useState([]);

  const toggleColumn = (col) => {
    setSelected(prev => prev.includes(col) ? prev.filter(c => c !== col) : [...prev, col]);
  };

  return (
    <div>
      <h2>Select Columns</h2>
      {columns.map(col => (
        <div key={col}>
          <input
            type="checkbox"
            id={col}
            onChange={() => toggleColumn(col)}
            checked={selected.includes(col)}
          />
          <label htmlFor={col}>{col}</label>
        </div>
      ))}
    </div>
  );
};

export default ColumnSelector;