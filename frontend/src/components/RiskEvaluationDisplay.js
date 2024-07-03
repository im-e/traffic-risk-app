import React, {useState, useEffect} from 'react';
import {useLocation} from 'react-router-dom';
import {Container, Header, Segment, Grid, Label, Message, Image} from 'semantic-ui-react';
import WeatherCard from './WeatherCard';
import LocationImage from './LocationImage';
import TrafficIncidents from "./TrafficIncidents";
import axios from 'axios';
import spartaLogo from "../images/sparta-black-text-white-bg.png";

const RiskEvaluationDisplay = () => {
    const location = useLocation();
    const {formData} = location.state || {};
    const [riskData, setRiskData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        if (formData) {
            axios.get('http://localhost:8080/risk', {
                params: {
                    zip: formData.startZip,
                    milesPerDay: formData.averageDistance,
                    days: formData.numberOfDays,
                    yearsExp: formData.drivingExperience,
                    age: formData.age
                }
            })
                .then(response => {
                    setRiskData(response.data);
                    setLoading(false);
                })
                .catch(error => {
                    setLoading(false);
                    if (error.response && error.response.status === 400) {
                        setError(error.response.data.message);
                    } else {
                        setError("An unexpected error occurred. Please try again later.");
                    }
                });
        } else {
            setLoading(false);
        }
    }, [formData]);

    if (!formData) {
        return (
            <Container>
                <Header as='h2'>No data available. Please submit the form first.</Header>
            </Container>
        );
    }

    const getRiskColor = (riskText) => {
        switch (riskText) {
            case 'Low Premium':
                return 'green';
            case 'High Premium':
                return 'yellow';
            case 'Medium Premium':
                return 'orange';
            default:
                return 'golden';
        }
    };

    return (
        <Container>
            <div className="title">
                <Image src={spartaLogo} alt="Sparta Global Logo" size='small' className="logo"/>
            </div>
            <Header as='h1' textAlign='center' style={{marginTop: '2em', marginBottom: '1em'}}>
                Risk Evaluation Results
            </Header>

            <Grid columns={2} stackable>
                <Grid.Row>
                    <Grid.Column>
                        <WeatherCard zipCode={formData.startZip}/>
                    </Grid.Column>
                    <Grid.Column>
                        <LocationImage zip={formData.startZip} milesPerDay={formData.averageDistance}/>
                    </Grid.Column>
                </Grid.Row>

                <Grid.Row>
                    <Grid.Column width={16}>
                        <TrafficIncidents zip={formData.startZip} milesPerDay={formData.averageDistance}/>
                    </Grid.Column>
                </Grid.Row>
            </Grid>

            <Segment style={{marginTop: '2em'}}>
                <Header as='h3'>Risk Assessment Summary</Header>
                <p>Based on the information provided:</p>
                <ul>
                    <li>Starting Location: {formData.startZip}, US</li>
                    <li>Rental Duration: {formData.numberOfDays} days</li>
                    <li>Average Daily Distance: {formData.averageDistance} miles</li>
                    <li>Driving Experience: {formData.drivingExperience} years</li>
                    <li>Age: {formData.age} years old</li>
                </ul>
                {loading ? (
                    <p>Loading risk data...</p>
                ) : error ? (
                    <Message negative>
                        <Message.Header>Error</Message.Header>
                        <p>{error}</p>
                    </Message>
                ) : riskData ? (
                    <Segment raised padded style={{
                        backgroundColor: '#f8f8f8',
                        borderRadius: '10px',
                        boxShadow: '0 0 10px rgba(0,0,0,0.1)'
                    }}>
                        <p style={{fontSize: '1.2em', fontWeight: 'bold', color: '#000306'}}>
                            We determined that your Area Risk Value is <span
                            style={{color: '#222223'}}>{riskData.areaRiskValue}</span>.
                            This is considered <span style={{color: 'rgba(70,1,1,0.7)'}}>{riskData.areaRiskText}</span>.
                        </p>

                        <p style={{fontSize: '1.2em', fontWeight: 'bold', color: '#000306'}}>
                            Furthermore, your Driver Risk Value is <span
                            style={{color: '#222223'}}>{riskData.customerRiskValue}</span>.
                            This is considered <span style={{color: 'rgba(70,1,1,0.7)'}}>{riskData.customerRiskText}</span>.
                        </p>

                        <p style={{fontSize: '1.3em', fontWeight: 'bold', color: '#000306'}}>
                            Based on our calculations, the overall Risk Score is <span
                            style={{color: '#222223'}}>{riskData.overallRiskValue}</span>.
                            This entitles you to our {' '}
                            <Label size='large' color={getRiskColor(riskData.overallRiskText)}
                                   style={{marginLeft: '5px'}}>
                                {riskData.overallRiskText.toUpperCase()}
                            </Label>
                            {' '}
                        </p>
                    </Segment>
                ) : (
                    <p>Unable to fetch risk data. Please try again later.</p>
                )}
            </Segment>

        </Container>
    );
};

export default RiskEvaluationDisplay;
