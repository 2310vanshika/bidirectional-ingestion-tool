// import React, { useState } from 'react';

// const FlatFileConfig = ({ setStatus, setColumns }) => {
//   const [file, setFile] = useState(null);

//   const handleLoad = () => {
//     setStatus('Reading Flat File...');
//     setTimeout(() => {
//       setColumns(['column1', 'column2', 'column3']);
//       setStatus('File read. Columns loaded.');
//     }, 1000);
//   };

//   return (
//     <div>
//       <h2>Flat File Configuration</h2>
//       <input type="file" onChange={(e) => setFile(e.target.files[0])} />
//       <button onClick={handleLoad}>Load File</button>
//     </div>
//   );
// };

// export default FlatFileConfig;
import React, { useState } from 'react';
import { uploadFlatFile } from '../api';

const FlatFileConfig = ({ setStatus, setColumns }) => {
  const [file, setFile] = useState(null);

  const handleLoad = async () => {
    if (!file) {
      setStatus('Please select a file.');
      return;
    }

    setStatus('Uploading file...');
    try {
      const data = await uploadFlatFile(file);
      setColumns(data.columns); // assuming backend returns { columns: [...] }
      setStatus('File uploaded. Columns loaded.');
    } catch (error) {
      setStatus(`Error: ${error.message}`);
    }
  };

  return (
    <div>
      <h2>Flat File Configuration</h2>
      <input type="file" onChange={(e) => setFile(e.target.files[0])} />
      <button onClick={handleLoad}>Load File</button>
    </div>
  );
};

export default FlatFileConfig;
