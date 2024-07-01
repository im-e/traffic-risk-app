import React, { useState } from 'react';
import { useNavigate} from "react-router-dom";
import { Card, Form, Button } from 'semantic-ui-react';
import './RentalRiskForm.css';
import WeatherDisplay from "./WeatherDisplay";
import LocationImage from "./LocationImage";
import WeatherCard from "./WeatherCard";

const RentalRiskForm = () => {
    const [formData, setFormData] = useState({
        startZip: '',
        startCountry: '',
        numberOfDays: '',
        averageDistance: '',
        drivingExperience: '',
        age: ''
    });
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const [showResults, setShowResults] = useState(false);
    const navigate = useNavigate();

    const handleChange = (e, { name, value }) => {
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);

        try {
            // Here I would typically send the data to your backend
            console.log('Form submitted:', formData);
            // Simulating an API call
            await new Promise(resolve => setTimeout(resolve, 1000));
            setLoading(false);
            //setShowResults(true);
            // Redirect to the summary page with form data
            navigate('/risk-evaluation', { state: { formData } });
        } catch (err) {
            setError('Failed to submit form: ' + err.message);
            setLoading(false);
        }
    };

    return (
        <div className="rental-form-wrapper">
            <h2>First, we need some information...</h2>
            <Card fluid>
                <Card.Content>
                    <Form onSubmit={handleSubmit}>
                        <Form.Group widths='equal'>
                            <Form.Input
                                fluid
                                label='Start Journey Location (Zip Code)'
                                placeholder='Enter ZIP code'
                                name='startZip'
                                value={formData.startZip}
                                onChange={handleChange}
                            />
                            <Form.Input
                                fluid
                                label='Country Code'
                                placeholder='Enter country code (e.g., US)'
                                name='startCountry'
                                value={formData.startCountry}
                                onChange={handleChange}
                            />
                        </Form.Group>
                        <Form.Input
                            label='Rental required (no. of days)'
                            placeholder='Enter number of days'
                            type='number'
                            name='numberOfDays'
                            value={formData.numberOfDays}
                            onChange={handleChange}
                        />
                        <Form.Input
                            label='Average distance per day (no. of miles)'
                            placeholder='Enter average distance'
                            type='number'
                            name='averageDistance'
                            value={formData.averageDistance}
                            onChange={handleChange}
                        />
                        <h3>Your Details</h3>
                        <Form.Input
                            label='Driving experience (no. of years)'
                            placeholder='Enter years of driving experience'
                            type='number'
                            name='drivingExperience'
                            value={formData.drivingExperience}
                            onChange={handleChange}
                        />
                        <Form.Input
                            label='Age (no. of years)'
                            placeholder='Enter your age'
                            type='number'
                            name='age'
                            value={formData.age}
                            onChange={handleChange}
                        />
                        <Button type='submit' primary loading={loading}>Submit</Button>
                    </Form>
                </Card.Content>
            </Card>
            {error && <p className="error-message">{error}</p>}
            {showResults && (
                <div>
                    <WeatherCard zip={formData.startZip} country={formData.startCountry} />
                    <LocationImage zip={formData.startZip} milesPerDay={formData.averageDistance} />
                </div>
            )}
        </div>
    );
};

export default RentalRiskForm;