import React, { useState, useEffect, useRef } from 'react';

const Grades = ({ grades }) => {
    const dummyGrades = [
        { subject: 'Math', grades: [{ value: 6, description: 'Algebra', date: '2023-05-10' }, { value: 4, description: 'Geometry', date: '2023-05-15' }, { value: 6, description: 'Calculus', date: '2023-06-01' }] },
        { subject: 'Science', grades: [{ value: 5, description: 'Physics', date: '2023-04-20' }, { value: 2, description: 'Chemistry', date: '2023-04-25' }, { value: 6, description: 'Biology', date: '2023-05-05' }] },
        { subject: 'History', grades: [{ value: 4, description: 'Ancient History', date: '2023-03-15' }, { value: 3, description: 'Modern History', date: '2023-03-20' }, { value: 2, description: 'World History', date: '2023-04-01' }] },
        { subject: 'English', grades: [{ value: 6, description: 'Literature', date: '2023-02-10' }] },
        { subject: 'Art', grades: [{ value: 5, description: 'Painting', date: '2023-01-20' }, { value: 6, description: 'Sculpture', date: '2023-01-25' }] },
    ];

    const [clickedGrade, setClickedGrade] = useState(null);
    const [clickedGradePosition, setClickedGradePosition] = useState({ x: 0, y: 0 });

    const tooltipRef = useRef(null);

    useEffect(() => {
        const handleClickOutside = (event) => {
            if (tooltipRef.current && !tooltipRef.current.contains(event.target)) {
                setClickedGrade(null);
            }
        };

        document.addEventListener('click', handleClickOutside);

        return () => {
            document.removeEventListener('click', handleClickOutside);
        };
    }, []);

    const getGradeColor = (gradeValue) => {
        const grade = gradeValue.value;
        switch (grade) {
            case 6:
                return '#4CBB17';
            case 5:
                return '#a7d620';
            case 4:
                return '#e6cc00';
            case 3:
                return '#fa7d28';
            case 2:
                return '#e61905';
            default:
                return 'black';
        }
    };

    const handleGradeClick = (gradeValue, event) => {
        event.stopPropagation();
        setClickedGrade(gradeValue);
        const gradeRect = event.target.getBoundingClientRect();
        setClickedGradePosition({ x: gradeRect.right, y: gradeRect.top });
    };

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
                        {dummyGrades.map((gradeData, index) => (
                            <tr key={index}>
                                <td>{gradeData.subject}</td>
                                <td>
                                    {gradeData.grades.map((gradeValue, i) => (
                                        <span
                                            key={i}
                                            className="grade-box"
                                            style={{ backgroundColor: getGradeColor(gradeValue) }}
                                            onClick={(event) => handleGradeClick(gradeValue, event)}
                                        >
                                            {gradeValue.value}
                                        </span>
                                    ))}
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
            {clickedGrade && (
                <div ref={tooltipRef} className="clicked-grade" style={{ position: 'absolute', top: clickedGradePosition.y, left: clickedGradePosition.x, transform: 'translate(-100%, -100%)' }}>
                    <p>Grade: {clickedGrade.value}</p> 
                    <p>Description: {clickedGrade.description}</p>
                    <p>Date: {clickedGrade.date}</p>
                </div>
            )}
        </div>
    );
};

export default Grades;
