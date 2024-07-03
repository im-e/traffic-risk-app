import React from 'react';
import {Button, Container, Header, Image} from 'semantic-ui-react'
import {Link} from "react-router-dom";
import spartaLogo from '../images/sparta-black-text-white-bg.png'
import carRentalLogo from '../images/carrental.jpg'
import './About.css';

const About = () => {

    return (
        <div className="about-container">
            <Container fluid>
                <div className="title">
                    <Header as='h1'>Sparta Insurance</Header>
                    <Image src={spartaLogo} alt="Sparta Global Logo" size='small' className="logo"/>
                </div>


                <Header as='h1'>Car Rental Evaluation Application</Header>
                <div className="introduction">
                    <Image src={carRentalLogo} alt="car Rental" className="logo"/>
                    <div className="spacer"></div>
                    <p className="intro-text">
                        Welcome to Sparta Insurance's Car Rental Evaluation Application portal.<br/>
                        A state-of-the-art platform for streamlining the premium selection process for car rental
                        providers by giving insightful risk assessments using real time statistical analysis.
                    </p>
                </div>

                <div className="assessment">
                    <p>
                        We create precise risk assessments by analyzing customer data, location, and usage patterns.
                        Our evaluations incorporate local traffic data, weather forecasts, geospatial information, and
                        statistical trends to ensure accuracy.<br/>
                        The process is quick and simple - start your evaluation in seconds by clicking the button.
                    </p>
                    <div className="spacer"></div>
                    <Link to="/evaluation">
                        <Button>Start Evaluation</Button>
                    </Link>
                </div>

                <div className="services">
                    <Header as='h2'>Our Services</Header>
                    <p>
                        This sophisticated rating calculator is an exclusive tool designed for use by our Internal
                        Teams.
                        Our highly trained agents utilize this system to input and process customer details, ensuring
                        accurate
                        and efficient risk assessment.
                    </p>

                    <Header as='h3'>Rating Calculation</Header>
                    <p>
                        Our advanced algorithms employ a multifaceted approach to risk assessment. We consider a
                        comprehensive
                        array of factors including, but not limited to, the driver's age, rental duration, geographical
                        location,
                        and projected mileage. This holistic evaluation allows us to generate a nuanced and accurate
                        risk profile.
                    </p>
                    <Header as='h3'>Risk Rating</Header>
                    <p>
                        Our innovative system generates two distinct scores for each customer: an Area Risk score and a
                        Driver Risk score.
                        The synthesis of these two metrics produces the overall Risk Rating, providing a comprehensive
                        view of the
                        insurance risk associated with each rental transaction.
                    </p>

                    <Header as='h3'>Tailored Recommendations</Header>
                    <p>
                        Based on the calculated Risk Rating, our system provides tailored premium recommendations
                        categorized as low,
                        medium, or high. This granular approach ensures that each customer receives a fair and
                        appropriate insurance premium.
                        For a more detailed exploration of our evaluation process, we encourage you to visit our
                        <Link to="/evaluation"> Evaluation</Link> page.
                    </p>
                </div>

                <div className="about-us">
                    <Header as='h2'>About Us</Header>
                    <p>
                        Sparta Insurance is a leading provider of customized, flexible temporary car insurance solutions
                        across California.
                        Our unwavering commitment is to deliver comprehensive, dependable, and cost-effective insurance
                        products that ensure
                        both peace of mind and enhanced safety for car rental businesses and their valued customers.
                        Leveraging our team of
                        seasoned insurance professionals and adopting a customer-centric approach, we continually strive
                        to establish ourselves
                        as the most trusted and preferred car rental insurance provider in the Golden State.
                    </p>
                </div>

            </Container>
        </div>
    );
};


export default About;
