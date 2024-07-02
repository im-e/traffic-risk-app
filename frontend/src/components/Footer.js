import React from 'react';
import { Container, Grid, Segment, List } from 'semantic-ui-react';
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
                            <List.Item as='a'>Pre-Order</List.Item>
                            <List.Item as='a'>FAQ</List.Item>
                            <List.Item as='a'>How To Access</List.Item>
                            <List.Item as='a'>Future Developments</List.Item>
                        </List>
                    </Grid.Column>
                    <Grid.Column width={7}>
                        <h4 className="ui inverted header">Footer Header</h4>
                        <p>
                            Extra space inside the footer to help re-engage users.
                            Like a motto perhaps..
                        </p>
                    </Grid.Column>
                </Grid.Row>
            </Grid>
        </Container>
    </Segment>
);

export default Footer;
