import React from 'react';

const Grades = ({ grades }) => {
    // Dummy data for testing
    const dummyGrades = [
        { subject: 'Math', grade: [6] },
        { subject: 'Science', grade: [5] },
        { subject: 'History', grade: [4] },
        { subject: 'English', grade: [6] },
        { subject: 'Art', grade: [5, 6] },
    ];

    return (
        <div className="card shadow p-3 mb-5 mt-5 bg-white rounded">
            <div className="card-body">
                <h5 className="card-title">Grades</h5>
                <table className="table">
                    <thead>
                    <tr>
                        <th>Subject</th>
                        <th>Grades</th>
                    </tr>
                    </thead>
                    <tbody>
                    {dummyGrades.map((grade, index) => (
                        <tr key={index}>
                            <td>{grade.subject}</td>
                            <td>{grade.grade.join(', ')}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default Grades;
