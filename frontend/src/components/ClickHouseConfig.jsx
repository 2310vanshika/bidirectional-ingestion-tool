// import React, { useState } from 'react';

// const ClickHouseConfig = ({ setStatus, setColumns }) => {
//   const [form, setForm] = useState({ host: '', port: '', database: '', user: '', token: '' });

//   const handleConnect = () => {
//     setStatus('Connecting to ClickHouse...');
//     setTimeout(() => {
//       setColumns(['id', 'name', 'email']);
//       setStatus('Connected. Columns loaded.');
//     }, 1000);
//   };

//   return (
//     <div>
//       <h2>ClickHouse Configuration</h2>
//       {['host', 'port', 'database', 'user', 'token'].map(field => (
//         <input
//           key={field}
//           type="text"
//           placeholder={field}
//           value={form[field]}
//           onChange={(e) => setForm({ ...form, [field]: e.target.value })}
//         />
//       ))}
//       <button onClick={handleConnect}>Connect</button>
//     </div>
//   );
// };

// export default ClickHouseConfig;

import React, { useState } from 'react';
import { connectClickHouse } from '../api';

const ClickHouseConfig = ({ setStatus, setColumns }) => {
  const [form, setForm] = useState({ host: '', port: '', database: '', user: '', token: '' });

  const handleConnect = async () => {
    setStatus('Connecting to ClickHouse...');
    try {
      const data = await connectClickHouse(form);
      setColumns(data.columns); // assuming backend returns { columns: [...] }
      setStatus('Connected. Columns loaded.');
    } catch (error) {
      setStatus(`Error: ${error.message}`);
    }
  };

  return (
    <div>
      <h2>ClickHouse Configuration</h2>
      {Object.keys(form).map(field => (
        <input
          key={field}
          type="text"
          placeholder={field}
          value={form[field]}
          onChange={(e) => setForm({ ...form, [field]: e.target.value })}
        />
      ))}
      <button onClick={handleConnect}>Connect</button>
    </div>
  );
};

export default ClickHouseConfig;
// src/components/ClickHouseConfig.jsx
// import React, { useState } from 'react';
// import { fetchClickHouseTables, fetchClickHouseColumns, fetchClickHousePreview } from '../api';

// const ClickHouseConfig = ({ onConfigSubmit, onPreviewData }) => {
//   const [config, setConfig] = useState({
//     url: '',
//     user: '',
//     jwtToken: '',
//   });
//   const [tables, setTables] = useState([]);
//   const [selectedTables, setSelectedTables] = useState([]);
//   const [columns, setColumns] = useState([]);
//   const [joinQuery, setJoinQuery] = useState('');

//   const handleInputChange = (e) => {
//     const { name, value } = e.target;
//     setConfig(prev => ({ ...prev, [name]: value }));
//   };

//   const loadTables = async () => {
//     const res = await fetchClickHouseTables(config);
//     setTables(res.tables);
//   };

//   const handleTableSelect = (e) => {
//     const value = e.target.value;
//     setSelectedTables(
//       selectedTables.includes(value)
//         ? selectedTables.filter(t => t !== value)
//         : [...selectedTables, value]
//     );
//   };

//   const loadColumns = async () => {
//     const res = await fetchClickHouseColumns(config, selectedTables);
//     setColumns(res.columns);
//     onConfigSubmit({ ...config, tables: selectedTables });
//   };

//   const preview = async () => {
//     const previewConfig = { ...config, joinQuery }; // include the join query inside config
//     const res = await fetchClickHousePreview(previewConfig, selectedTables, columns);
//     onPreviewData(res.preview);
//   };
  

//   return (
//     <div>
//       <h3>ClickHouse Config</h3>
//       <input name="url" placeholder="URL" onChange={handleInputChange} />
//       <input name="user" placeholder="User" onChange={handleInputChange} />
//       <input name="jwtToken" placeholder="JWT Token" onChange={handleInputChange} />

//       <button onClick={loadTables}>Load Tables</button>
//       {tables.map(table => (
//         <label key={table}>
//           <input
//             type="checkbox"
//             value={table}
//             checked={selectedTables.includes(table)}
//             onChange={handleTableSelect}
//           />
//           {table}
//         </label>
//       ))}

//       <button onClick={loadColumns}>Load Columns</button>
//       <div>
//         <textarea
//           placeholder="Enter custom JOIN query here"
//           value={joinQuery}
//           onChange={e => setJoinQuery(e.target.value)}
//         />
//         <button onClick={preview}>Preview Data</button>
//       </div>

//       <h4>Available Columns</h4>
//       <ul>
//         {columns.map(col => (
//           <li key={`${col.table}.${col.name}`}>{col.table}.{col.name}</li>
//         ))}
//       </ul>
//     </div>
//   );
// };

// export default ClickHouseConfig;

// import axios from 'axios';

// const API_BASE_URL = 'http://localhost:8080';

// export const fetchClickHouseTables = async (config) => {
//   try {
//     const response = await axios.post(`${API_BASE_URL}/clickhouse/tables`, config);
//     return {
//       tables: response.data.tables || [],
//     };
//   } catch (error) {
//     console.error('Error fetching tables:', error);
//     return { tables: [] };
//   }
// };

// export const fetchClickHouseColumns = async (config, selectedTables) => {
//   try {
//     const response = await axios.post(`${API_BASE_URL}/clickhouse/columns`, {
//       config,
//       tables: selectedTables,
//     });
//     return {
//       columns: response.data.columns || [],
//     };
//   } catch (error) {
//     console.error('Error fetching columns:', error);
//     return { columns: [] };
//   }
// };

// export const fetchClickHousePreview = async (config, selectedTables, columns) => {
//   try {
//     const response = await axios.post(`${API_BASE_URL}/clickhouse/preview`, {
//       config,
//       tables: selectedTables,
//       columns,
//     });
//     return {
//       preview: response.data.preview || [],
//     };
//   } catch (error) {
//     console.error('Error fetching preview:', error);
//     return { preview: [] };
//   }
// };
