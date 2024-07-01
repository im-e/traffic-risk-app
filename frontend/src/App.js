// //import logo from './logo.svg';


import React from 'react';

import { BrowserRouter as Router, Routes, Route, Navigate} from 'react-router-dom';
import { Container } from 'semantic-ui-react';
import NavigationBar from './components/NavigationBar';
import RentalRiskForm from './components/RentalRiskForm';
import EvaluationDisplay from "./components/EvaluationDisplay";
import Summary from './components/Summary';
import WeatherDisplay from './components/WeatherDisplay';
import LocationImage from './components/LocationImage';
import TrafficIncidents from './components/TrafficIncidents';
import Contact from "./components/Contact";
import About from "./components/About";

function App() {
    return (
        <Router>
            <Container className="App main-container">
                <NavigationBar />
                <Routes>
                    {/*<Route path='/' exact component={Home} />*/}
                    <Route path='/about' element={<About/>} />
                    <Route path='/contact' element={<Contact/>} />
                    <Route path='/evaluation' element={<EvaluationDisplay/>} />
                    {/* Replace Services with your services component */}


                    <Route path="/" element={
                        <>
                            <WeatherDisplay />
                            <LocationImage zip="90251" milesPerDay="33" />
                            <TrafficIncidents zip="90251" milesPerDay={33} />
                        </>
                    } />
                    <Route path="/summary" element={<Summary />} />
                </Routes>
            </Container>
        </Router>
    );
}

export default App;