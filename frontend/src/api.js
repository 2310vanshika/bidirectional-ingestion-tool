// // import axios from 'axios';

// // const API_BASE_URL = 'http://localhost:8080'; // adjust if using a proxy or different port

// // export const connectClickHouse = async (config) => {
// //   const response = await axios.post(`${API_BASE_URL}/connect/clickhouse`, config);
// //   return response.data;
// // };

// // export const uploadFlatFile = async (file) => {
// //   const formData = new FormData();
// //   formData.append('file', file);

// //   const response = await axios.post(`${API_BASE_URL}/upload/flatfile`, formData, {
// //     headers: { 'Content-Type': 'multipart/form-data' },
// //   });
// //   return response.data;
// // };
// import axios from 'axios';

// const API_BASE_URL = 'http://localhost:8080';

// // Existing functions
// export const connectClickHouse = async (config) => {
//   const response = await axios.post(`${API_BASE_URL}/connect/clickhouse`, config);
//   return response.data;
// };

// export const uploadFlatFile = async (file) => {
//   const formData = new FormData();
//   formData.append('file', file);

//   const response = await axios.post(`${API_BASE_URL}/upload/flatfile`, formData, {
//     headers: { 'Content-Type': 'multipart/form-data' },
//   });
//   return response.data;
// };

// // ✅ New function: fetch list of tables from ClickHouse
// export const fetchClickHouseTables = async (config) => {
//   const response = await axios.post(`${API_BASE_URL}/connect/tables`, config);
//   return response.data;
// };

// // ✅ New function: fetch columns from selected ClickHouse tables
// export const fetchClickHouseColumns = async (config, tables) => {
//   const response = await axios.post(`${API_BASE_URL}/connect/columns`, {
//     ...config,
//     tables,
//   });
//   return response.data;
// };

// // ✅ New function: fetch preview of records from ClickHouse
// export const fetchClickHousePreview = async (config, tables, columns) => {
//   const response = await axios.post(`${API_BASE_URL}/connect/preview`, {
//     ...config,
//     tables,
//     columns,
//   });
//   return response.data;
// };

import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080';

export const connectClickHouse = async (config) => {
  const response = await axios.post(`${API_BASE_URL}/connect/clickhouse`, config);
  return response.data;
};

export const uploadFlatFile = async (file) => {
  const formData = new FormData();
  formData.append('file', file);
  const response = await axios.post(`${API_BASE_URL}/upload/flatfile`, formData, {
    headers: { 'Content-Type': 'multipart/form-data' },
  });
  return response.data;
};

export const fetchClickHouseTables = async (config) => {
  const response = await axios.post(`${API_BASE_URL}/connect/tables`, config);
  return response.data.tables;
};

export const fetchClickHouseColumns = async (tables) => {
  const response = await axios.post(`${API_BASE_URL}/connect/columns`, { tables });
  return response.data.columns;
};

export const fetchClickHousePreview = async (joinQuery) => {
  const response = await axios.post(`${API_BASE_URL}/connect/preview`, { joinQuery });
  return response.data.preview;
};
