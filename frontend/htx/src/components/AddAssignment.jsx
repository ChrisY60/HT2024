import React, { useState } from 'react';
import {useNavigate, useParams} from 'react-router-dom';
import axios from 'axios';
import { useAuth } from './Auth';

const AddAssignment = () => {
    const navigate = useNavigate();
    const queryParams = new URLSearchParams(window.location.search);
    const id = queryParams.get('id');
    const { token } = useAuth();
    const [assignmentName, setAssignmentName] = useState('');
    const [deadline, setDeadline] = useState('');
    const [description, setDescription] = useState('');

    const handleAddAssignment = async () => {
        try {
            const headers = { Authorization: `Bearer ${token}` };
            const response = await axios.post(
                `http://192.168.199.73:8080/api/v1/subjects/${id}/assignments`,
                {
                    name: assignmentName,
                    deadline: deadline,
                    description: description,
                },
                { headers }
            );
            console.log('Assignment added successfully:', response.data);
            navigate(`/subject/${id}`); // Redirect to the subject page after adding the assignment
        } catch (error) {
            console.error('Error adding assignment:', error);
        }
    };

    return (
        <div className="container mt-5">
            <h1 className="text-center mb-4">Add Assignment</h1>
            <form>
                <div className="mb-3">
                    <label className="form-label">Assignment Name:</label>
                    <input
                        type="text"
                        value={assignmentName}
                        onChange={(e) => setAssignmentName(e.target.value)}
                        className="form-control"
                    />
                </div>
                <div className="mb-3">
                    <label className="form-label">Deadline:</label>
                    <input
                        type="date"
                        value={deadline}
                        onChange={(e) => setDeadline(e.target.value)}
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
                <button
                    type="button"
                    onClick={handleAddAssignment}
                    className="btn btn-success"
                >
                    Add Assignment
                </button>
            </form>
        </div>
    );
};

export default AddAssignment;
