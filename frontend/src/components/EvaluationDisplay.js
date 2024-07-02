import React from 'react';
import RentalRiskForm from './RentalRiskForm';
import { Container, Header, Segment } from 'semantic-ui-react';

const EvaluationDisplay = () => {
    return (
        <Container>
            <Header as='h1' textAlign='center'>
                Risk Evaluation
            </Header>

            <Segment>
                <p>
                    Welcome to our Rental Risk Evaluation service. This tool helps assess potential risks
                    associated with your car rental based on various factors such as location, duration,
                    and your driving experience.
                </p>
                <p>
                    Please fill out the form below to receive a personalized risk assessment.
                </p>
            </Segment>

            <RentalRiskForm />

            <Segment style={{ marginTop: '2em' }}>
                <p>
                    After submitting the form, you will be redirected to a summary page with your
                    risk assessment results. This evaluation takes into account factors such as:
                </p>
                <ul>
                    <li>Your starting location</li>
                    <li>The duration of your rental</li>
                    <li>Your average daily mileage</li>
                    <li>Your driving experience and age</li>
                </ul>
                <p>
                    Our algorithm uses this information along with historical data to provide you
                    with a comprehensive risk evaluation.
                </p>
            </Segment>
        </Container>
    );
};

export default EvaluationDisplay;