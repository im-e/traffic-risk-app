import React from 'react';
import { useLocation } from 'react-router-dom';
import { Card, List } from 'semantic-ui-react';
import './Summary.css';

const Summary = () => {
    const location = useLocation();
    const { formData } = location.state || {};

    if (!formData) {
        return <div>No data available</div>;
    }

    return (
        <div className="summary">
            <h2>Summary of Your Input</h2>
            <Card fluid>
                <Card.Content>
                    <List>
                        <List.Item>
                            <List.Header>Start Journey Location</List.Header>
                            {formData.startZip}, {formData.startCountry}
                        </List.Item>
                        <List.Item>
                            <List.Header>Number of Days</List.Header>
                            {formData.numberOfDays}
                        </List.Item>
                        <List.Item>
                            <List.Header>Average Distance (miles)</List.Header>
                            {formData.averageDistance}
                        </List.Item>
                        <List.Item>
                            <List.Header>Driving Experience (years)</List.Header>
                            {formData.drivingExperience}
                        </List.Item>
                        <List.Item>
                            <List.Header>Age (years)</List.Header>
                            {formData.age}
                        </List.Item>
                    </List>
                </Card.Content>
            </Card>
        </div>
    );
};

export default Summary;