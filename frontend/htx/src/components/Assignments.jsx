import React, { useState } from 'react';
import axios from 'axios';
import { useAuth } from "./Auth";

const Assignments = ({ title, subject, description, dueDate }) => {
    const { token } = useAuth();
    const assignment = {
        title: 'Math Assignment',
        subject: 'Mathematics',
        description: 'Complete exercises 1-10 from Chapter 3',
        dueDate: '2024-03-20'
    };

    const [files, setFiles] = useState([]);

    const handleFileChange = (e) => {
        const selectedFiles = Array.from(e.target.files);
        console.log('Selected files:', selectedFiles);
        setFiles(prevFiles => [...prevFiles, ...selectedFiles]);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        console.log('Files submitted:', files);

        const formData = new FormData();
        files.forEach(file => {
            formData.append('files', file);
        });

        try {
            const headers = { Authorization: `Bearer ${token}`, 'Content-Type': 'multipart/form-data' };
            await axios.post('http://localhost:8080/api/v1/storage/files', formData, { headers });
            console.log('Files uploaded successfully');
        } catch (error) {
            console.error('Error uploading files:', error);
        }
    };

    return (
        <div className="assignment-card" style={{ width: '50vw', margin: 'auto' }}>
            <div className="card shadow p-3 mb-5 mt-5 bg-white rounded">
                <div className="card-body">
                    <h5 className="card-title">{assignment.title}</h5>
                    <p className="card-subtitle mb-2 text-muted">Subject: {assignment.subject}</p>
                    <p className="card-text">Description: {assignment.description}</p>
                    <p className="card-text"><small className="text-muted">Due Date: {assignment.dueDate}</small></p>
                    <hr />
                    <form onSubmit={handleSubmit}>
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
                        <button type="submit" className="btn btn-success btn-lighten">Hand In</button>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default Assignments;
