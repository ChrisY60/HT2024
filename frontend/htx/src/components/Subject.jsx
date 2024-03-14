import React, { useState } from 'react';
import { Link } from 'react-router-dom';

const Subject = () => {
    const [displayAssignments, setDisplayAssignments] = useState(true);

    const subjectData = {
        name: 'Mathematics',
        teacher: 'Mr. Smith',
        assignments: [
            { title: 'Algebra Homework', endDate: '2024-03-20' },
            { title: 'Geometry Quiz', endDate: '2024-03-25' },
            { title: 'Calculus Project', endDate: '2024-04-05' }
        ],
        materials: [
            { title: 'Mathematics Textbook', datePublished: '2024-03-01' },
            { title: 'Additional Practice Sheets', datePublished: '2024-03-05' },
            { title: 'Video Lecture Series', datePublished: '2024-03-10' }
        ]
    };

    const handleToggle = () => {
        setDisplayAssignments(!displayAssignments);
    };

    return (
        <div className="subject text-center">
            <h2>{subjectData.name}</h2>
            <p>Teacher: {subjectData.teacher}</p>
            <div className="mb-3 d-flex justify-content-center">
                <div className="btn-group" role="group">
                    <button
                        className={`btn btn-sm ${displayAssignments ? 'btn-primary' : 'btn-success'}`}
                        onClick={handleToggle}
                    >
                        Assignments
                    </button>
                    <button
                        className={`btn btn-sm ${displayAssignments ? 'btn-success' : 'btn-primary'}`}
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
                                <Link to="/assignment" key={index} className="card mb-3 text-decoration-none">
                                    <div className="card-body">
                                        <h5 className="card-title">Title: {assignment.title}</h5>
                                        <p className="card-text">End Date: {assignment.endDate}</p>
                                    </div>
                                </Link>
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
