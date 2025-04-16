import React, { useState } from 'react';
import Header from './components/Header';
import SourceSelector from './components/SourceSelector';
import ClickHouseConfig from './components/ClickHouseConfig';
import FlatFileConfig from './components/FlatFileConfig';
import ColumnSelector from './components/ColumnSelector';
import StatusDisplay from './components/StatusDisplay';

const App = () => {
  const [source, setSource] = useState('');
  const [columns, setColumns] = useState([]);
  const [status, setStatus] = useState('');

  return (
    <div className="container">
      <Header />
      <SourceSelector setSource={setSource} />
      {source === 'ClickHouse' && <ClickHouseConfig setStatus={setStatus} setColumns={setColumns} />}
      {source === 'FlatFile' && <FlatFileConfig setStatus={setStatus} setColumns={setColumns} />}
      {columns.length > 0 && <ColumnSelector columns={columns} />}
      <StatusDisplay status={status} />
    </div>
  );
};

export default App;