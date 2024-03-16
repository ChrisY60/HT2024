import React, { useState } from 'react';
import {useNavigate, useParams} from 'react-router-dom';
import axios from 'axios';
import { useAuth } from './Auth';

const AddMaterial = () => {
    const navigate = useNavigate();
    const queryParams = new URLSearchParams(window.location.search);
    const id = queryParams.get('id');
    const { token } = useAuth();
    const [materialName, setMaterialName] = useState('');
    const [description, setDescription] = useState('');
    const [files, setFiles] = useState([]);

    const handleFileChange = (e) => {
        const selectedFiles = Array.from(e.target.files);
        setFiles(prevFiles => [...prevFiles, ...selectedFiles]);
    };
    const handleAddMaterial = async (e) => {
        e.preventDefault();
        const formData = new FormData();
        files.forEach(file => {
            formData.append('files', file);
        });

        try {
            const headers = { Authorization: `Bearer ${token}`};
            const response = await axios.post('http://localhost:8080/api/v1/storage/files_temp', formData, { headers });
            await axios.post(`http://localhost:8080/api/v1/subjects/${id}/materials/temp`, {
                name: materialName,
                description: description,
                fileIds: response.data
            }, {headers:{ Authorization: `Bearer ${token}`, ContentType: 'application/json' }})
            console.log('Files uploaded successfully');
        } catch (error) {
            console.error('Error uploading files:', error);
        }
    };

    return (
        <div className="container mt-5">
            <h1 className="text-center mb-4">Add Material</h1>
            <form>
                <div className="mb-3">
                    <label className="form-label">Material Name:</label>
                    <input
                        type="text"
                        value={materialName}
                        onChange={(e) => setMaterialName(e.target.value)}
                        className="form-control"
                    />
                </div>
                <div className="mb-3">
                    <label className="form-label">Description:</label>
                    <textarea
                        value={description}
                        onChange={(e) => setDescription(e.target.value)}
                        className="form-control"
                    />
                </div>
                <div className="row mb-3">
                    <div className="col-12">
                        <label htmlFor="fileInput" className="form-label">Attach File:</label>
                    </div>
                    <div className="col-12">
                        <input type="file" id="fileInput" className="form-control-file" onChange={handleFileChange} multiple />
                    </div>
                    <div className="mt-3">
                        {files.map((file, index) => (
                            <div key={index} className="d-flex align-items-center">
                                <img src="https://cdn-icons-png.flaticon.com/512/2246/2246713.png" alt="file icon" style={{ width: '24px', height: '24px', marginRight: '8px' }} />
                                <span>{file.name}</span>
                            </div>
                        ))}
                    </div>
                </div>
                <button
                    type="button"
                    onClick={handleAddMaterial}
                    className="btn btn-success"
                >
                    Add Material
                </button>
            </form>
        </div>
    );
};

export default AddMaterial;
