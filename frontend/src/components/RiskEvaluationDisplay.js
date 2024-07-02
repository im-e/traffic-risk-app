// import React from 'react';
// import { useLocation } from 'react-router-dom';
// import { Container, Header, Segment, Grid } from 'semantic-ui-react';
// import WeatherCard from './WeatherCard';
// import LocationImage from './LocationImage';
// import TrafficIncidents from "./TrafficIncidents";
//
// const RiskEvaluationDisplay = () => {
//     const location = useLocation();
//     const { formData } = location.state || {};
//
//     if (!formData) {
//         return <Container><Header as='h2'>No data available. Please submit the form first.</Header></Container>;
//     }
//
//     return (
//         <Container>
//             <Header as='h1' textAlign='center' style={{ marginTop: '2em', marginBottom: '1em' }}>
//                 Risk Evaluation Results
//             </Header>
//
//             <Grid columns={2} stackable>
//                 <Grid.Row>
//                     <Grid.Column>
//                         <WeatherCard zip={formData.startZip} country={formData.startCountry} />
//                     </Grid.Column>
//                     <Grid.Column>
//                         <LocationImage zip={formData.startZip} milesPerDay={formData.averageDistance} />
//                     </Grid.Column>
//                 </Grid.Row>
//
//                 <Grid.Row>
//                     <Grid.Column width={16}>
//                         <TrafficIncidents zip={formData.startZip} milesPerDay={formData.averageDistance} />
//                     </Grid.Column>
//                 </Grid.Row>
//
//             </Grid>
//
//             <Segment style={{ marginTop: '2em' }}>
//                 <Header as='h3'>Risk Assessment Summary</Header>
//                 <p>Based on the information provided:</p>
//                 <ul>
//                     <li>Starting Location: {formData.startZip}, {formData.startCountry}</li>
//                     <li>Rental Duration: {formData.numberOfDays} days</li>
//                     <li>Average Daily Distance: {formData.averageDistance} miles</li>
//                     <li>Driving Experience: {formData.drivingExperience} years</li>
//                     <li>Age: {formData.age} years</li>
//                 </ul>
//                 <p>
//                     [Add risk assessment logic and display the results]
//                 </p>
//             </Segment>
//         </Container>
//     );
// };
//
// export default RiskEvaluationDisplay;



// VERSION 2 BREAKS BLANK PAGE
// import React, { useState, useEffect } from 'react';
// import { useLocation } from 'react-router-dom';
// import { Container, Header, Segment, Grid } from 'semantic-ui-react';
// import WeatherCard from './WeatherCard';
// import LocationImage from './LocationImage';
// import TrafficIncidents from "./TrafficIncidents";
// import axios from 'axios';
//
// const RiskEvaluationDisplay = () => {
//     const location = useLocation();
//     const { formData } = location.state || {};
//     const [riskData, setRiskData] = useState(null);
//
//     useEffect(() => {
//         if (formData) {
//             axios.get('http://localhost:8080/risk', {
//                 params: {
//                     zip: formData.startZip,
//                     milesPerDay: formData.averageDistance,
//                     days: formData.numberOfDays,
//                     yearsExp: formData.drivingExperience,
//                     age: formData.age
//                 }
//             })
//                 .then(response => {
//                     setRiskData(response.data);
//                 })
//                 .catch(error => {
//                     console.error("There was an error fetching the risk data!", error);
//                 });
//         }
//     }, [formData]);
//
//     if (!formData) {
//         return <Container><Header as='h2'>No data available. Please submit the form first.</Header></Container>;
//     }
//
//     if (!riskData) {
//         return <Container><Header as='h2'>Loading...</Header></Container>;
//     }
//
//     return (
//         <Container>
//             <Header as='h1' textAlign='center' style={{ marginTop: '2em', marginBottom: '1em' }}>
//                 Risk Evaluation Results
//             </Header>
//
//             <Grid columns={2} stackable>
//                 <Grid.Row>
//                     <Grid.Column>
//                         <WeatherCard zip={formData.startZip} country={formData.startCountry} />
//                     </Grid.Column>
//                     <Grid.Column>
//                         <LocationImage zip={formData.startZip} milesPerDay={formData.averageDistance} />
//                     </Grid.Column>
//                 </Grid.Row>
//
//                 <Grid.Row>
//                     <Grid.Column width={16}>
//                         <TrafficIncidents zip={formData.startZip} milesPerDay={formData.averageDistance} />
//                     </Grid.Column>
//                 </Grid.Row>
//             </Grid>
//
//             <Segment style={{ marginTop: '2em' }}>
//                 <Header as='h3'>Risk Assessment Summary</Header>
//                 <p>Based on the information provided:</p>
//                 <ul>
//                     <li>Starting Location: {formData.startZip}, {formData.startCountry}</li>
//                     <li>Rental Duration: {formData.numberOfDays} days</li>
//                     <li>Average Daily Distance: {formData.averageDistance} miles</li>
//                     <li>Driving Experience: {formData.drivingExperience} years</li>
//                     <li>Age: {formData.age} years</li>
//                 </ul>
//                 <p>We determined that your Area Risk Value is {riskData.areaRiskValue}. This is considered {riskData.areaRiskText}.</p>
//                 <p>Furthermore, your Driver Risk Value is {riskData.customerRiskValue}. This is considered {riskData.customerRiskText}.</p>
//                 <p>Based on our calculations, the overall Risk Score is {riskData.overallRiskValue}. This entitles you to our {riskData.overallRiskText} rates.</p>
//             </Segment>
//         </Container>
//     );
// };
//
// export default RiskEvaluationDisplay;











import React, { useState, useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import { Container, Header, Segment, Grid } from 'semantic-ui-react';
import WeatherCard from './WeatherCard';
import LocationImage from './LocationImage';
import TrafficIncidents from "./TrafficIncidents";
import axios from 'axios';

const RiskEvaluationDisplay = () => {
    const location = useLocation();
    const { formData } = location.state || {};
    const [riskData, setRiskData] = useState(null);
    const [loading, setLoading] = useState(true);

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
                    console.error("There was an error fetching the risk data!", error);
                    setLoading(false);
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
                {loading ? (
                    <p>Loading risk data...</p>
                ) : riskData ? (
                    <>
                        <p>We determined that your Area Risk Value is {riskData.areaRiskValue}. This is considered {riskData.areaRiskText}.</p>
                        <p>Furthermore, your Driver Risk Value is {riskData.customerRiskValue}. This is considered {riskData.customerRiskText}.</p>
                        <p>Based on our calculations, the overall Risk Score is {riskData.overallRiskValue}. This entitles you to our {riskData.overallRiskText} rates.</p>
                    </>
                ) : (
                    <p>Unable to fetch risk data. Please try again later.</p>
                )}
            </Segment>
        </Container>
    );
};

export default RiskEvaluationDisplay;
