import React from 'react';
import { useLocation } from 'react-router-dom';
import { Container, Header, Segment, Grid } from 'semantic-ui-react';
import WeatherCard from './WeatherCard';
import LocationImage from './LocationImage';
import TrafficIncidents from "./TrafficIncidents";

const RiskEvaluationDisplay = () => {
    const location = useLocation();
    const { formData } = location.state || {};

    if (!formData) {
        return <Container><Header as='h2'>No data available. Please submit the form first.</Header></Container>;
    }

    return (
        <Container>
            <Header as='h1' textAlign='center' style={{ marginTop: '2em', marginBottom: '1em' }}>
                Risk Evaluation Results
            </Header>

            <Grid columns={2} stackable>
                <Grid.Row>
                    <Grid.Column>
                        <WeatherCard zip={formData.startZip} country={formData.startCountry} />
                    </Grid.Column>
                    <Grid.Column>
                        <LocationImage zip={formData.startZip} milesPerDay={formData.averageDistance} />
                    </Grid.Column>
                </Grid.Row>

                <Grid.Row>
                    <Grid.Column width={16}>
                        <TrafficIncidents zip={formData.startZip} milesPerDay={formData.averageDistance} />
                    </Grid.Column>
                </Grid.Row>

            </Grid>

            <Segment style={{ marginTop: '2em' }}>
                <Header as='h3'>Risk Assessment Summary</Header>
                <p>Based on the information provided:</p>
                <ul>
                    <li>Starting Location: {formData.startZip}, {formData.startCountry}</li>
                    <li>Rental Duration: {formData.numberOfDays} days</li>
                    <li>Average Daily Distance: {formData.averageDistance} miles</li>
                    <li>Driving Experience: {formData.drivingExperience} years</li>
                    <li>Age: {formData.age} years</li>
                </ul>
                <p>
                    [Add risk assessment logic and display the results]
                </p>
            </Segment>
        </Container>
    );
};

export default RiskEvaluationDisplay;