import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './WeatherDisplay.css';

const WeatherDisplay = () => {
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
            setError('Failed to fetch weather data');
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchWeather('10001', 'US'); // change this to any default location
    }, []);

    const handleSubmit = (e) => {
        e.preventDefault();
        if (zip && countryCode) {
            fetchWeather(zip, countryCode);
        }
    };

    return (
        <div className ="weather-display">
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    value={zip}
                    onChange={(e) => setZip(e.target.value)}
                    placeholder="Enter location"
                />
                <button type="submit">Get Weather</button>
            </form>

            <h2>Weather Information</h2>
            {loading && <p>Loading weather data...</p>}
            {error && <p>{error}</p>}
            {weather && (
                <div>
                    <h3>{weather.location.name}, {weather.location.country}</h3>
                    <p>Temperature: {weather.current.temp_c}°C / {weather.current.temp_f}°F</p>
                    <p>Condition: {weather.current.condition.text}</p>
                    <img src={weather.current.condition.icon} alt="Weather condition"/>
                    <p>Wind: {weather.current.wind_kph} km/h, {weather.current.wind_dir}</p>
                    <p>Humidity: {weather.current.humidity}%</p>
                    <p>Feels like: {weather.current.feelslike_c}°C / {weather.current.feelslike_f}°F</p>
                </div>
            )}
        </div>
    );
};

export default WeatherDisplay;