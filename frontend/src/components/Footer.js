import React from 'react';
import {Container, Grid, Segment, List, Header} from 'semantic-ui-react';
import './Footer.css';

const Footer = () => (
    <Segment inverted vertical className="footer">
        <Container>
            <Grid divided inverted stackable>
                <Grid.Row>
                    <Grid.Column width={3}>
                        <h4 className="ui inverted header">About Us</h4>
                        <List link inverted>
                            <List.Item as='a'>Sitemap</List.Item>
                            <List.Item as='a'>Contact Us</List.Item>
                            <List.Item as='a'>Link</List.Item>
                            <List.Item as='a'>Other Link</List.Item>
                        </List>
                    </Grid.Column>
                    <Grid.Column width={3}>
                        <h4 className="ui inverted header">Services</h4>
                        <List link inverted>
                            <List.Item as='a'>Developments</List.Item>
                            <List.Item as='a'>FAQ</List.Item>
                            <List.Item as='a'>How To Access</List.Item>
                            <List.Item as='a'>Support us</List.Item>
                        </List>
                    </Grid.Column>
                    <Grid.Column width={7}>
                        <h3 className="ui inverted header"> Strength in Every Mile  </h3>
                        <p>Sparta Insurance</p>
                        <p>125 London Wall, Barbican</p>
                        <p>London, EC2Y 5AS, United Kingdom</p>

                    </Grid.Column>
                </Grid.Row>
            </Grid>
        </Container>
    </Segment>
);

export default Footer;
