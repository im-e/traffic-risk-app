import React from 'react';
import {Container, Header} from 'semantic-ui-react'
import {Link} from "react-router-dom";

const About = () => {

    return (
        <div>
            <Container fluid>
                <Header as='h2'>About Us</Header>
                <p>At Sparta Insurance, we’re committed to providing personalised, flexible temporary car insurance
                    solutions across California.

                    Our mission is to offer comprehensive, reliable, and affordable insurance products that ensure peace
                    of mind and safety for both car rental businesses and their customers. With a dedicated team of
                    insurance experts and a customer-centric approach, we strive to be the most trusted car rental
                    insurance provider in the Golden State. </p>
                <Header as='h2'>Our services</Header>
                <p>
                    This rating calculator is intended to be used by Internal Teams.

                    Our agents will input customer details into the system.

                    <Header as='h3'>
                        Rating Calculation</Header> Our smart algorithms assess risk based on age, rental duration,
                    location, mileage and more.
                    <Header as='h3'>
                        Risk Rating </Header> Customers are rated with two scores, their Area Risk and Driver Risk.
                    These combined, make the overall Risk Rating.
                    <Header as='h3'>
                        Tailored Recommendations</Header> Based on their Risk Rating, we recommend suitable coverage
                    options to our users (Gold, Silver, or Bronze).
                    You can visit the <Link to="/evaluation"> Evaluation</Link> tab to find out more now.
                </p>

            </Container>
        </div>
    );
};

export default About;
