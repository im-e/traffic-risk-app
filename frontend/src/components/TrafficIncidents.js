import React, { useState, useEffect } from 'react';
import { Card, List, Message, Loader } from 'semantic-ui-react';
import axios from 'axios';

const TrafficIncidents = ({ zip, milesPerDay }) => {
    const [incidents, setIncidents] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchIncidents = async () => {
            try {
                setLoading(true);
                setError(null);

                // Log request parameters
                console.log('Requesting incidents for:', { zip, milesPerDay });
                const response = await axios.get('/incidents', {
                    params: { zip, milesPerDay }
                });
                // Log response data
                console.log('Response data:', response.data);
                // Access the incidents property from the response
                setIncidents(response.data.incidents);
                setLoading(false);
            } catch (err) {
                console.error('Error fetching incidents:', err);
                setError('Failed to load traffic incidents');
                setLoading(false);
            }
        };

        fetchIncidents();
    }, [zip,milesPerDay]);

    return (
        <Card fluid>
            <Card.Content>
                <Card.Header>Traffic Incidents</Card.Header>
                <Card.Meta>
                    Within the {zip} zipcode, based on a {milesPerDay} miles per day average
                </Card.Meta>
                <Card.Description>
                    {loading && <Loader active inline='centered'>Loading</Loader>}
                    {error && <Message negative>{error}</Message>}
                    {!loading && !error && (
                        incidents.length > 0 ? (
                            <List divided relaxed>
                                {/*{incidents.map((incident, index) => (*/}
                                {/*    <List.Item key={index}>*/}
                                {/*        <List.Icon name='warning sign' size='large' verticalAlign='middle' />*/}
                                {/*        <List.Content>*/}
                                {/*            <List.Header>{incident.type}</List.Header>*/}
                                {/*            <List.Description>{incident.description}</List.Description>*/}
                                {/*        </List.Content>*/}
                                {/*    </List.Item>*/}
                                {/*))}*/}
                            </List>
                        ) : (
                            <Message info>No traffic incidents reported in this area.</Message>
                        )
                    )}
                </Card.Description>
            </Card.Content>
            <Card.Content extra>
                Total incidents: {incidents.length}
            </Card.Content>
        </Card>
    );
};

export default TrafficIncidents;