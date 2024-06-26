import logo from './logo.svg';
import './App.css';
import WeatherDisplay from './components/WeatherDisplay';
import NavigationBar from "./components/NavigationBar";

function App() {
  return (
      <div className="App">
        <NavigationBar/>
        {/*<h1>Weather App</h1>*/}
        <WeatherDisplay/>
      </div>
  );
}

export default App;
