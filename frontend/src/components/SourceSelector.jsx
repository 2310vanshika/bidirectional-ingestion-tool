import React from 'react';

const SourceSelector = ({ setSource }) => (
  <div>
    <h2>Select Data Source</h2>
    <select onChange={(e) => setSource(e.target.value)} defaultValue="">
      <option value="" disabled>Select Source</option>
      <option value="ClickHouse">ClickHouse</option>
      <option value="FlatFile">Flat File</option>
    </select>
  </div>
);

export default SourceSelector;