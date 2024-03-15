import React, { useState } from 'react';

const Assignments = ({ title, subject, description, dueDate }) => {
    const assignment = {
        title: 'Math Assignment',
        subject: 'Mathematics',
        description: 'Complete exercises 1-10 from Chapter 3',
        dueDate: '2024-03-20'
    };

    const [file, setFile] = useState(null);

    const handleFileChange = (e) => {
        setFile(e.target.files[0]);
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log('File submitted:', file);
    };

    return (
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
                            <input type="file" id="fileInput" className="form-control-file" onChange={handleFileChange} />
                        </div>
                    </div>
                    <button type="submit" className="btn btn-success btn-lighten">Hand In</button>
                </form>
            </div>
        </div>
    );
};

export default Assignments;
