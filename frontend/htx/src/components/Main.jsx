import React, {useEffect, useState} from 'react';
import { Link } from 'react-router-dom';
import {useAuth} from "./Auth";
import axios from "axios";

const Main = () => {
    const [subjects, setSubjects] = useState([]);
    const { token } = useAuth();

    useEffect(() => {
        const headers = {Authorization: `Bearer ${token}`};
        axios.get(`http://localhost:8080/api/v1/subjects`, {headers})
            .then((res) => {
                const subjectData = res.data;
                setSubjects(subjectData);
            })
            .catch((err) => {
                console.error('Error fetching subjects:', err);
            });
    },[]);

    return (
        <div className="container mt-5">
            <h1 className="text-center mb-4">Explore Subjects</h1>
            <div className="row">
                {subjects.map((subject, index) => (
                    <div key={index} className="col-lg-6 mb-4">
                        <Link to={`/subject/${subject.id}`} className="card d-flex flex-column justify-content-center align-items-center" style={{ height: '20vh', width: '40vw', overflow: 'hidden', paddingRight: 0, paddingLeft: 0, textDecoration: 'none' }}>
                            <img src="https://d3nl1jd9up78ug.cloudfront.net/wages/how-to-become-a-mathematician-484-detail.jpg" className="card-img-top" alt={subject.name} style={{ height: '50%', objectFit: 'cover' }} />
                            <div className="card-body text-center">
                                <h5 className="card-title">{subject.name}</h5>
                            </div>
                        </Link>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default Main;
