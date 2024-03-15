import React, {useEffect, useState} from 'react';
import { Link } from 'react-router-dom';

const StudentAssignments = () => {
    const [receivedData, setReceivedData] = useState({});

    useEffect(() => {
        const queryParams = new URLSearchParams(window.location.search);
        const id = queryParams.get('id');
        const title = queryParams.get('title');
        const endDate = queryParams.get('endDate');

        console.log({id, title, endDate});
        setReceivedData({ id, title, endDate });
    }, []);
    const studentsData = [
        { id: 1, name: "John Doe" },
        { id: 2, name: "Jane Smith" },
        { id: 3, name: "Alice Johnson" },
        { id: 4, name: "Bob Brown" },
        { id: 5, name: "Emily Davis" }
    ];

    return (
        <div className="container">
            <h3 className="mt-4">Students Related to Assignment: {receivedData.title}</h3>
            <ul className="list-group mt-3">
                {studentsData.map((student, index) => (
                    <li key={index} className="list-group-item ">
                        <Link to="/assignment" className="text-decoration-none text-dark">
                            {student.name}
                        </Link>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default StudentAssignments;
