import React from 'react';
import { Card, CardContent, CardHeader, CardDescription, Image } from 'semantic-ui-react';
import image1 from '../dashboard/image-1 Temperature.jpeg'
import image2 from '../dashboard/image-2 Hourly.jpeg'
import image3 from '../dashboard/image-3 Road Feature.jpeg'
import image4 from  '../dashboard/image-4 Monthly.jpeg'
import image5 from '../dashboard/image-5 Weather.jpeg'
import image6 from '../dashboard/image-6 Cities.jpeg'


const imageData = [
    {
        imageUrl: image1,
        header: 'Analysis',
        description: 'Accidents most commonly take place in moderate temperatures.',
    },
    {
        imageUrl:image2,
        header: 'Analysis',
        description: 'The number of traffic accidents peaks around rush hours: 8am and 5pm.',
    },
    {
        imageUrl: image3,
        header: 'Analysis',
        description: 'The highest number of accidents occurred on streets with junctions, traffic lights, crossings and stop signs.',
    },
    {
        imageUrl: image4,
        header: 'Analysis',
        description: 'The highest number of accidents occurred in the winter months.',
    },
    {
        imageUrl: image5,
        header: 'Analysis',
        description: 'Traffic accidents occurred most frequently in mild weather conditions.',
    },
    {
        imageUrl: image6,
        header: 'Analysis',
        description: 'Cities with the highest number of traffic accidents in California.',
    },
];

const Dashboard = () => (
    <Card.Group itemsPerRow={1}>
        {imageData.map((item, index) => (
            <Card key={index}>
                <Image src={item.imageUrl} wrapped ui={false} />
                <CardContent>
                    <CardHeader>{item.header}</CardHeader>
                    <CardDescription>{item.description}</CardDescription>
                </CardContent>
            </Card>
        ))}
    </Card.Group>
);

export default Dashboard;
