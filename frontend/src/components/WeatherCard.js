import React, {useState, useEffect} from 'react';
import {Card, Image, Loader, Icon} from 'semantic-ui-react';
import axios from 'axios';

const WeatherCard = () => {
    const [weather, setWeather] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [zip, setZip] = useState('');
    const [countryCode, setCountryCode] = useState('');

    const fetchWeather = async (zip, countryCode) => {
        try {
            setLoading(true);
            const response = await axios.get(`/weather?zip=${zip}&countryCode=${countryCode}`);
            setWeather(response.data);
            setLoading(false);
        } catch (err) {
            console.error('Error details:', err.response ? err.response.data : err.message);
            setError('Failed to fetch weather data: ' + (err.response ? err.response.data : err.message));
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchWeather('90251', 'US');
    }, []);

    const handleSubmit = (e) => {
        e.preventDefault();
        if (zip && countryCode) {
            fetchWeather(zip, countryCode);
        }
    };

    return (
        <div>
            {loading && <p>Loading weather data...</p>}
            {error && <p>{error}</p>}
            {weather && (

                <Card>
                    <Card.Content>
                        <Card.Header>Weather Information</Card.Header>
                    </Card.Content>
                    <Card.Content>
                        <Card.Header>{weather.location.name}, {weather.location.country}</Card.Header>
                        <Card.Meta>
                            <span className='date'>{weather.location.localtime}</span>
                        </Card.Meta>
                        <Card.Description>
                            <p>
                                Condition: {weather.current.condition.text}
                                <Image src={weather.current.condition.icon} size='mini' spaced='left'/>
                            </p>
                            <p>Temperature: {weather.current.temp_c}°C / {weather.current.temp_f}°F</p>
                            <p>Wind: {weather.current.wind_kph} km/h, {weather.current.wind_dir}</p>
                            <p>Humidity: {weather.current.humidity}%</p>
                            <p>Feels like: {weather.current.feelslike_c}°C / {weather.current.feelslike_f}°F</p>
                        </Card.Description>
                    </Card.Content>
                    <Card.Content extra>
                        <Icon name='cloud'/> {weather.current.cloud} % cloud cover
                    </Card.Content>
                </Card>
            )}
        </div>
    );
};

export default WeatherCard;