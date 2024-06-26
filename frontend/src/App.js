//import logo from './logo.svg';
import './App.css';
import WeatherDisplay from './components/WeatherDisplay';
import NavigationBar from "./components/NavigationBar";
import LocationImage from './components/LocationImage';
import {Container} from "semantic-ui-react";

function App() {
  return (
      <Container className="App">
        <NavigationBar/>
        <WeatherDisplay/>
          <LocationImage zip="10001" countryCode="US" />
      </Container>
  );
}

export default App;
