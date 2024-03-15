import React, {useEffect, useState} from 'react';
import { Link } from 'react-router-dom';
import axios from "axios";
import {useAuth} from "./Auth";

const StudentAssignments = () => {
    const [receivedData, setReceivedData] = useState({});
    const [studentsData, setStudentsData] = useState([]);
    const { token, isTokenExpired } = useAuth();


    useEffect(() => {
        const headers = {Authorization: `Bearer ${token}`};
        const queryParams = new URLSearchParams(window.location.search);
        const id = queryParams.get('id');
        const title = queryParams.get('name');
        const endDate = queryParams.get('deadline');

        console.log({id, title, endDate});
        setReceivedData({ id, title, endDate });

        axios.get(`http://localhost:8080/api/v1/assignments/${id}/students`, {headers})
            .then((res) => {
                console.log(res.data);
                const students = res.data;
                const names = students.map((student) => student.firstName + ' ' + student.middleName + ' ' + student.lastName);
                setStudentsData(names);
            })
            .catch((err) => {
                console.error('Error fetching subjects:', err);
            });
    }, []);
    /*const studentsData = [
        { id: 1, name: "John Doe" },
        { id: 2, name: "Jane Smith" },
        { id: 3, name: "Alice Johnson" },
        { id: 4, name: "Bob Brown" },
        { id: 5, name: "Emily Davis" }
    ];*/

    return (
        <div className="container">
            <h3 className="mt-4">Students Related to Assignment: {receivedData.title}</h3>
            <ul className="list-group mt-3">
                {studentsData.map((student, index) => (
                    <li key={index} className="list-group-item ">
                        <Link to="/assignment" className="text-decoration-none text-dark">
                            {student}
                        </Link>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default StudentAssignments;
