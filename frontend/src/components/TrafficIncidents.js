import React, { useState, useEffect } from 'react';
import { Card, List, Message, Loader } from 'semantic-ui-react';
import axios from 'axios';

const TrafficIncidents = ({ zip, countryCode, distance }) => {
    const [incidents, setIncidents] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchIncidents = async () => {
            try {
                setLoading(true);
                setError(null);
                const response = await axios.get('/incidents', {
                    params: { zip, countryCode, distance }
                });
                setIncidents(response.data);
                setLoading(false);
            } catch (err) {
                console.error('Error fetching incidents:', err);
                setError('Failed to load traffic incidents');
                setLoading(false);
            }
        };

        fetchIncidents();
    }, [zip, countryCode, distance]);

    return (
        <Card fluid>
            <Card.Content>
                <Card.Header>Traffic Incidents</Card.Header>
                <Card.Meta>
                    Within {distance} km of {zip}, {countryCode}
                </Card.Meta>
                <Card.Description>
                    {loading && <Loader active inline='centered'>Loading</Loader>}
                    {error && <Message negative>{error}</Message>}
                    {!loading && !error && (
                        incidents.length > 0 ? (
                            <List divided relaxed>
                                {incidents.map((incident, index) => (
                                    <List.Item key={index}>
                                        <List.Icon name='warning sign' size='large' verticalAlign='middle' />
                                        <List.Content>
                                            <List.Header>{incident.type}</List.Header>
                                            <List.Description>{incident.description}</List.Description>
                                        </List.Content>
                                    </List.Item>
                                ))}
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