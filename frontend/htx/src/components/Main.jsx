import React from 'react';
import { Link } from 'react-router-dom';

const Main = () => {
    const subjects = [
        {
            title: 'Mathematics',
            description: 'Explore the world of numbers and equations.',
            imageURL: 'https://d3nl1jd9up78ug.cloudfront.net/wages/how-to-become-a-mathematician-484-detail.jpg',
            link: '/subject'
        },
        {
            title: 'Science',
            description: 'Discover the wonders of the natural world.',
            imageURL: 'https://d3nl1jd9up78ug.cloudfront.net/wages/how-to-become-a-mathematician-484-detail.jpg',
            link: '/subject'
        },
        {
            title: 'History',
            description: 'Journey through the past and learn about our ancestors.',
            imageURL: 'https://d3nl1jd9up78ug.cloudfront.net/wages/how-to-become-a-mathematician-484-detail.jpg',
            link: '/subject'
        },
        {
            title: 'Literature',
            description: 'Dive into the realm of words and stories.',
            imageURL: 'https://d3nl1jd9up78ug.cloudfront.net/wages/how-to-become-a-mathematician-484-detail.jpg',
            link: '/subjectx'
        }
    ];

    return (
        <div className="container mt-5">
            <h1 className="text-center mb-4">Explore Subjects</h1>
            <div className="row">
                {subjects.map((subject, index) => (
                    <div key={index} className="col-lg-6 mb-4">
                        <Link to={subject.link} className="card d-flex flex-column justify-content-center align-items-center" style={{ height: '20vh', width: '40vw', overflow: 'hidden', paddingRight: 0, paddingLeft: 0, textDecoration: 'none' }}>
                            <img src={subject.imageURL} className="card-img-top" alt={subject.title} style={{ height: '50%', objectFit: 'cover' }} />
                            <div className="card-body text-center">
                                <h5 className="card-title">{subject.title}</h5>
                                <p className="card-text">{subject.description}</p>
                            </div>
                        </Link>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default Main;
