import React, {useEffect, useState} from 'react';
import axios from "axios";
import {useAuth} from "./Auth";

const StudentAssignments = () => {
    const [receivedData, setReceivedData] = useState({});
    const [studentsData, setStudentsData] = useState([]);
    const { token, isTokenExpired } = useAuth();
    const [assignmentId, setAssignmentId] = useState('');
    const [name, setName] = useState('');
    const [deadline, setDeadline] = useState('');

    useEffect(() => {
        const headers = {Authorization: `Bearer ${token}`};
        const queryParams = new URLSearchParams(window.location.search);
        const id = queryParams.get('id');
        setAssignmentId(id);
        const title = queryParams.get('name');
        setName(title);
        const endDate = queryParams.get('deadline');
        setDeadline(endDate);

        console.log({id, title, endDate});
        setReceivedData({ id, title, endDate });

        axios.get(`http://localhost:8080/api/v1/assignments/${id}/students`, {headers})
            .then((res) => {
                console.log(res.data);
                const students = res.data;
                const studentIdName = students.map((student) => {
                    return {id: student.id, name: student.firstName + ' ' + student.middleName + ' ' + student.lastName};
                });
                console.log(studentIdName);
                setStudentsData(studentIdName);
            })
            .catch((err) => {
                console.error('Error fetching subjects:', err);
            });
    }, [token]);
    return (
        <div className="container">
            <h3 className="mt-4">Students Related to Assignment: {receivedData.title}</h3>
            <ul className="list-group mt-3">
                {studentsData.map((student, index) => (
                    <li key={index} className="list-group-item ">
                        <a href={`/review-assignment?assignmentId=${assignmentId}&studentId=${student.id}&title=${name}&deadline=${deadline}`} className="text-decoration-none text-dark">
                            {student.name}
                        </a>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default StudentAssignments;
