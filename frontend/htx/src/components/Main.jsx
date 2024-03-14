import React from 'react';

const Main = () => {
    const subjects = [
        {
            title: 'Mathematics',
            description: 'Explore the world of numbers and equations.',
            image: 'math.jpg',
            link: '/subject'
        },
        {
            title: 'Science',
            description: 'Discover the wonders of the natural world.',
            image: 'science.jpg',
            link: '/subject'
        },
        {
            title: 'History',
            description: 'Journey through the past and learn about our ancestors.',
            image: 'history.jpg',
            link: '/subject'
        },
        {
            title: 'Literature',
            description: 'Dive into the realm of words and stories.',
            image: 'literature.jpg',
            link: '/subjectx'
        }
    ];

    return (
        <div className="container mt-5">
            <h1 className="text-center mb-4">Explore Subjects</h1>
            <div className="row">
                {subjects.map((subject, index) => (
                    <div key={index} className="col-lg-6 mb-4">
                        <div className="card">
                            <div className="card-body">
                                <h5 className="card-title">{subject.title}</h5>
                                <p className="card-text">{subject.description}</p>
                                <a href={subject.link} className="btn btn-success">Explore {subject.title}</a>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default Main;
