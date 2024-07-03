import React from 'react';
import RentalRiskForm from './RentalRiskForm';
import {Container, Header, Image, Segment} from 'semantic-ui-react';
import spartaLogo from "../images/sparta-black-text-white-bg.png";
import "./EvaluationDisplay.css"

const EvaluationDisplay = () => {
    return (
        <div className="evaluation-container">
            <Container>
                <div className="title">
                    <Header as='h1'>Risk Evaluation</Header>
                    <Image src={spartaLogo} alt="Sparta Global Logo" size='small' className="logo"/>
                </div>

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

                <RentalRiskForm/>

                <Segment style={{marginTop: '2em'}}>
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
        </div>
    );
};

export default EvaluationDisplay;