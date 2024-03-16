import React, { useState, useEffect, useRef } from 'react';
import axios from "axios";
import {useAuth} from "./Auth";
import {getCurrentUser} from "./authUtils";

const Grades = () => {
    const [grades, setGrades] = useState([]);
    const [clickedGrade, setClickedGrade] = useState(null);
    const [clickedGradePosition, setClickedGradePosition] = useState({ x: 0, y: 0 });
    const { token, isTokenExpired } = useAuth();
    const [user, setUser] = useState('');

    const tooltipRef = useRef(null);

    useEffect(() => {
        const userRole = getCurrentUser(token);
        setUser(userRole);
        const headers = {Authorization: `Bearer ${token}`};
        axios.get(`http://localhost:8080/api/v1/grades`, { headers }).then((res) => {
            setGrades(res.data);
        });
        const handleClickOutside = (event) => {
            if (tooltipRef.current && !tooltipRef.current.contains(event.target)) {
                setClickedGrade(null);
            }
        };

        document.addEventListener('click', handleClickOutside);

        return () => {
            document.removeEventListener('click', handleClickOutside);
        };
    }, [user]);

    const getGradeColor = (gradeValue) => {
        const grade = gradeValue.grade;
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
                        {grades.map((gradeData, index) => (
                            <tr key={index}>
                                <td>{gradeData.name}</td>
                                <td>
                                    {gradeData.grades.map((gradeValue, i) => (
                                        <span
                                            key={i}
                                            className="grade-box"
                                            style={{ backgroundColor: getGradeColor(gradeValue) }}
                                            onClick={(event) => handleGradeClick(gradeValue, event)}
                                        >
                                            {gradeValue.grade}
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
                    <p>Grade: {clickedGrade.grade}</p>
                    <p>Description: {clickedGrade.comment}</p>
                    <p>Date: {clickedGrade.gradedDate}</p>
                </div>
            )}
        </div>
    );
};

export default Grades;
