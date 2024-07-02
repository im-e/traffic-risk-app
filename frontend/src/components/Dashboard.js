import image1 from '../dashboard/image-1 Temperature.jpeg'
import image2 from '../dashboard/image-2 Hourly.jpeg'
import image3 from '../dashboard/image-3 Road Feature.jpeg'
import image4 from '../dashboard/image-4 Monthly.jpeg'
import image5 from '../dashboard/image-5 Weather.jpeg'
import image6 from '../dashboard/image-6 Cities.jpeg'
import React, { useState } from 'react';
import { Card, Image, Pagination } from 'semantic-ui-react';

const imageData = [
    { id: 1, imageUrl: image1, header: 'Analysis', description: 'Accidents most commonly take place in moderate temperatures.' },
    { id: 2, imageUrl: image2, header: 'Analysis', description: 'The number of traffic accidents peaks around rush hours: 8am and 5pm.' },
    { id: 3, imageUrl: image3, header: 'Analysis', description: 'The highest number of accidents occurred on streets with junctions, traffic lights, crossings and stop signs.' },
    { id: 4, imageUrl: image4, header: 'Analysis', description: 'The highest number of accidents occurred in the winter months.' },
    { id: 5, imageUrl: image5, header: 'Analysis', description: 'Traffic accidents occurred most frequently in mild weather conditions.' },
    { id: 6, imageUrl: image6, header: 'Analysis', description: 'Cities with the highest number of traffic accidents in California.' },
];

const Dashboard = () => {
    const [activePage, setActivePage] = useState(1);
    const itemsPerPage = 3;

    // Calculate pagination boundaries
    const startIndex = (activePage - 1) * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;
    const slicedData = imageData.slice(startIndex, endIndex);

    // Handle pagination click event
    const handlePaginationChange = (e, { activePage }) => {
        setActivePage(activePage);
    };

    return (
        <div>
            <Card.Group itemsPerRow={1}>
                {slicedData.map((item) => (
                    <Card key={item.id}>
                        <Image src={item.imageUrl} wrapped ui={false} />
                        <Card.Content>
                            <Card.Header>{item.header}</Card.Header>
                            <Card.Meta>{item.meta}</Card.Meta>
                            <Card.Description>{item.description}</Card.Description>
                        </Card.Content>
                    </Card>
                ))}
            </Card.Group>

            {/* Pagination */}
            <Pagination
                activePage={activePage}
                onPageChange={handlePaginationChange}
                totalPages={Math.ceil(imageData.length / itemsPerPage)}
            />
        </div>
    );
};

export default Dashboard;
