import React, { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { useAuth } from "./Auth";
import { getCurrentUser } from "./authUtils";
import axios from "axios";
import { saveAs } from 'file-saver';

const Subject = () => {
    const [displayAssignments, setDisplayAssignments] = useState(true);
    const [user, setUser] = useState('');
    const [assignmentString, setAssignmentString] = useState("");
    const { token, isTokenExpired } = useAuth();
    const { id } = useParams();
    const [subjectData, setSubjects] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        const userRole = getCurrentUser(token);
        setUser(userRole);
        fetchData();
    }, []);

    const fetchData = () => {
        const headers = { Authorization: `Bearer ${token}` };
        axios.get(`http://localhost:8080/api/v1/subjects/${id}/assignments`, { headers })
            .then((res) => {
                setSubjects(res.data);
            })
            .catch((err) => {
                console.error('Error fetching subjects:', err);
            });
    };

    const handleToggle = () => {
        setDisplayAssignments(!displayAssignments);
        fetchData();
    };

    const passAssignment = (assignment) => {
        console.log(assignment);
        const queryString = `id=${assignment.id}&name=${encodeURIComponent(assignment.name)}&deadline=${assignment.deadline}`;
        setAssignmentString(queryString);
    };

    const exportGrades = () => {
        const headers = { Authorization: `Bearer ${token}` };
        axios.get(`http://localhost:8080/api/v1/export/excel/${id}`, { headers, responseType: 'blob' })
            .then((res) => {
                const blob = new Blob([res.data], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
                saveAs(blob, `grades_${id}.xlsx`);
            })
            .catch((err) => {
                console.error('Error exporting grades:', err);
            });
    };


    return (
        <div className="subject text-center">
            <h2>{subjectData.name}</h2>
            <p>Teacher: {subjectData.teacher}</p>
            <div className="mb-3 d-flex justify-content-center">
                {user === "TEACHER" ? (
                    <div>
                        <button style={{ marginRight: "1vw" }}
                            className="btn btn-sm btn-success"
                            onClick={() => navigate(`/add-assignment?id=${id}`)}
                        >
                            Add Assignment
                        </button>
                        <button style={{ marginRight: "1vw" }}
                            className="btn btn-sm btn-success"
                            onClick={() => navigate(`/add-material?id=${id}`)}
                        >
                            Add Material
                        </button>
                        <button 
                            className="btn btn-sm btn-success"
                            onClick={exportGrades}
                        >
                            Export All Grades
                        </button>
                    </div>
                ) : null
                }
            </div>
            <div className="mb-3 d-flex justify-content-center">
                <div className="btn-group" role="group">
                    <button
                        className={`btn btn-sm ${displayAssignments ? 'btn-success' : 'btn-secondary'}`}
                        onClick={handleToggle}
                    >
                        Assignments
                    </button>
                    <button
                        className={`btn btn-sm ${displayAssignments ? 'btn-secondary' : 'btn-success'}`}
                        onClick={handleToggle}
                    >
                        Materials
                    </button>
                </div>
            </div>
            {displayAssignments ? (
                <div>
                    <h3>Assignments:</h3>
                    <div className="d-flex justify-content-center">
                        <div className="card-deck">
                            {subjectData.map((assignment, index) => (
                                <React.Fragment key={index}>
                                    {user === "STUDENT" ? (
                                        <a href={`/assignment`} className="card mb-3 text-decoration-none">
                                            <div className="card-body">
                                                <h5 className="card-title">Title: {assignment.name}</h5>
                                                <p className="card-text">End Date: {assignment.deadline}</p>
                                            </div>
                                        </a>
                                    ) : user === "TEACHER" ? (
                                        <a href={`/students?${assignmentString}`} onClick={() => passAssignment(assignment)} className="card mb-3 text-decoration-none">
                                            <div className="card-body">
                                                <h5 className="card-title">Title: {assignment.name}</h5>
                                                <p className="card-text">End Date: {assignment.deadline}</p>
                                            </div>
                                        </a>
                                    ) : null}
                                </React.Fragment>
                            ))}
                        </div>
                    </div>
                </div>
            ) : (
                <div>
                    <h3>Materials:</h3>
                    <div className="d-flex justify-content-center">
                        <div className="card-deck">
                            {subjectData.map((material, index) => (
                                <div className="card mb-3" key={index}>
                                    <div className="card-body">
                                        <h5 className="card-title">Title: {material.name}</h5>
                                        <p className="card-text">Date Published: {material.date}</p>
                                    </div>
                                </div>
                            ))}
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
};

export default Subject;
