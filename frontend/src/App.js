//import logo from './logo.svg';
import './App.css';
import WeatherDisplay from './components/WeatherDisplay';
import NavigationBar from "./components/NavigationBar";
import LocationImage from './components/LocationImage';
import TrafficIncidents from './components/TrafficIncidents';
import {Container} from "semantic-ui-react";

function App() {
  return (
      <Container className="App">
        <NavigationBar/>
        <WeatherDisplay/>
          <LocationImage zip="10001" countryCode="US" />
          <TrafficIncidents zip="10001" countryCode="US" distance={10} />
      </Container>
  );
}

export default App;
