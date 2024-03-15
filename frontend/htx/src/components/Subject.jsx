import React, { useEffect, useState } from 'react';
import {Link, useNavigate, useParams} from 'react-router-dom';
import { useAuth } from "./Auth";
import * as jose from "jose";
import axios from "axios";

const Subject = () => {
    const [displayAssignments, setDisplayAssignments] = useState(true);
    const [user, setUser] = useState('');
    const [assignmentString, setAssignmentString] = useState("");
    const { token, isTokenExpired } = useAuth();
    const { id } = useParams();
    const [subjectData, setSubjects] = useState([]);
    /*const subjectData = {
        name: 'Mathematics',
        teacher: 'Mr. Smith',
        assignments: [
            { id: 1, title: "Algebra Homework", endDate: '2024-03-20' },
            { id: 2, title: 'Geometry Quiz', endDate: '2024-03-25' },
            { id: 3, title: 'Calculus Project', endDate: '2024-04-05' }
        ],
        materials: [
            { title: 'Mathematics Textbook', datePublished: '2024-03-01' },
            { title: 'Additional Practice Sheets', datePublished: '2024-03-05' },
            { title: 'Video Lecture Series', datePublished: '2024-03-10' }
        ]
    };*/

    const navigate = useNavigate(); // Initialize navigate hook

    useEffect(() => {
        getCurrentUser();
        const headers = {Authorization: `Bearer ${token}`};
        axios.get(`http://localhost:8080/api/v1/subjects/${id}/assignments`, {headers})
            .then((res) => {
                console.log(res.data);
                const subjectData = res.data;
                setSubjects(subjectData);
            })
            .catch((err) => {
                console.error('Error fetching subjects:', err);
            });
    }, [user]);

    const getCurrentUser = async () => {
        try {
            const decodedToken = jose.decodeJwt(token);
            if (decodedToken && decodedToken.role) {
                const userRole = decodedToken.role;
                setUser(userRole);
            } else {
                console.error('Unable to retrieve user ID from JWT token.');
                navigate('/login');
            }
        } catch (error) {
            console.error('Error decoding JWT token:', error);

            navigate('/login');
        }
    };

    const handleToggle = () => {
        setDisplayAssignments(!displayAssignments);
        const headers = {Authorization: `Bearer ${token}`};
        if(displayAssignments){
            axios.get(`http://localhost:8080/api/v1/subjects/${id}/materials`, {headers})
                .then((res) => {
                    console.log(res.data);
                    const subjectData = res.data;
                    setSubjects(subjectData);
                })
                .catch((err) => {
                    console.error('Error fetching subjects:', err);
                });
        } else {
            axios.get(`http://localhost:8080/api/v1/subjects/${id}/assignments`, {headers})
                .then((res) => {
                    console.log(res.data);
                    const subjectData = res.data;
                    setSubjects(subjectData);
                })
                .catch((err) => {
                    console.error('Error fetching subjects:', err);
                });
        }
    };

    const passAssignment = (assignment) => {
        console.log(assignment);
        const queryString = `id=${assignment.id}&title=${encodeURIComponent(assignment.title)}&endDate=${assignment.endDate}`;
        setAssignmentString(queryString);
    };

    return (
        <div className="subject text-center">
            <h2>{subjectData.name}</h2>
            <p>Teacher: {subjectData.teacher}</p>
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
