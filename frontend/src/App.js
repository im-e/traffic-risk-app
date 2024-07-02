// //import logo from './logo.svg';


import React from 'react';

import {BrowserRouter as Router, Routes, Route, Navigate} from 'react-router-dom';
import {Container} from 'semantic-ui-react';
import NavigationBar from './components/NavigationBar';
import EvaluationDisplay from "./components/EvaluationDisplay";
import Summary from './components/Summary';

import Contact from "./components/Contact";
import About from "./components/About";
import RiskEvaluationDisplay from "./components/RiskEvaluationDisplay";
import Footer from "./components/Footer"
import Dashboard from "./components/Dashboard";

function App() {
    return (
        <Router>
            <Container className="App main-container">
                <NavigationBar/>
                <Routes>
                    {/*<Route path='/' exact component={Home} />*/}
                    <Route path='/about' element={<About/>}/>
                    <Route path='/evaluation' element={<EvaluationDisplay/>}/>
                    <Route path='/data-visualiser' element={<Dashboard />} />
                    <Route path='/contact' element={<Contact/>}/>
                    {/* Replace Services with your services component */}

                    <Route path='/risk-evaluation' element={<RiskEvaluationDisplay/>}/>

                    <Route path="/" element={
                        <>
                            {/*<WeatherDisplay />*/}
                            {/*<LocationImage zip="90251" milesPerDay="33" />*/}
                            {/*<TrafficIncidents zip="90251" milesPerDay={33} />*/}
                        </>
                    }/>
                    <Route path="/summary" element={<Summary/>}/>
                </Routes>
            </Container>
            <div style={{ marginTop: '2em' }}></div> {/* Spacer div */}
            <Footer></Footer>
        </Router>
    );
}

export default App;