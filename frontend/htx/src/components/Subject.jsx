import React, {useEffect, useState} from 'react';
import { Link } from 'react-router-dom';
import {useAuth} from "./Auth";
import * as jose from "jose";

const Subject = () => {
    const [displayAssignments, setDisplayAssignments] = useState(true);
    const [user, setUser] = useState('');
    const [assignmentString, setAssignmentString] = useState("");
    const { token, isTokenExpired } = useAuth();
    const subjectData = {
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
    };

    useEffect(() => {
        getCurrentUser();
    }, [user]);

    const getCurrentUser = async () => {
        const decodedToken = jose.decodeJwt(token);
        if (decodedToken && decodedToken.role) {
            const userRole = decodedToken.role;
            console.log(userRole);
            const headers = {Authorization: `Bearer ${token}`};
            setUser(userRole);
        } else {
            console.error('Unable to retrieve user ID from JWT token.');
        }
    }
    const handleToggle = () => {
        setDisplayAssignments(!displayAssignments);
    };

    const passAssignment = (assignment) => {
        console.log(assignment);
        const queryString = `id=${assignment.id}&title=${encodeURIComponent(assignment.title)}&endDate=${assignment.endDate}`;
        setAssignmentString(queryString);
    }

    return (
        <div className="subject text-center">
            <h2>{subjectData.name}</h2>
            <p>Teacher: {subjectData.teacher}</p>
            <div className="mb-3 d-flex justify-content-center">
                <div className="btn-group" role="group">
                    <button
                        className={`btn btn-sm ${displayAssignments ? 'btn-secondary' : 'btn-success'}`}
                        onClick={handleToggle}
                    >
                        Assignments
                    </button>
                    <button
                        className={`btn btn-sm ${displayAssignments ? 'btn-success' : 'btn-secondary'}`}
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
                            {subjectData.assignments.map((assignment, index) => (
                                <React.Fragment key={index}>
                                    {user === "STUDENT" ? (
                                        <a href={`/assignment`} className="card mb-3 text-decoration-none">
                                            <div className="card-body">
                                                <h5 className="card-title">Title: {assignment.title}</h5>
                                                <p className="card-text">End Date: {assignment.endDate}</p>
                                            </div>
                                        </a>
                                    ) : user === "TEACHER" ? (
                                            <a href={`/students?${assignmentString}`} onClick={() => passAssignment(assignment)} className="card mb-3 text-decoration-none">
                                            <div className="card-body">
                                                <h5 className="card-title">Title: {assignment.title}</h5>
                                                <p className="card-text">End Date: {assignment.endDate}</p>
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
                            {subjectData.materials.map((material, index) => (
                                <div className="card mb-3" key={index}>
                                    <div className="card-body">
                                        <h5 className="card-title">Title: {material.title}</h5>
                                        <p className="card-text">Date Published: {material.datePublished}</p>
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
