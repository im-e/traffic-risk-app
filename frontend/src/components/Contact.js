import React from 'react';

import {Segment, Grid, Header, Button, Form, Image} from "semantic-ui-react";

import './Contact.css'
import spartaLogo from "../images/sparta-black-text-white-bg.png";


const Contact = () => {
    return (
        <div className="contact-container">
            <div className="title">
                <Header as='h1'>Contact Us</Header>
                <Image src={spartaLogo} alt="Sparta Global Logo" size='small' className="logo"/>
            </div>

            <div className="text">
                <Grid textAlign='center'>
                    <p>We'd love to hear from you! Whether you have a question about features, services, need a demo, or
                        anything else, our team is ready to answer all your questions.</p>
                </Grid>
            </div>

            <div className="content">
                {/*<p>This is the Contact page.</p>*/}
                <Grid columns={2} relaxed='very' stackable>
                    <Grid.Column>
                        <Header as='h3'>Our Office</Header>
                        <p>125 London Wall, Barbican</p>
                        <p>London, EC2Y 5AS</p>
                        <p>United Kingdom</p>
                    </Grid.Column>
                    <Grid.Column>
                        <Header as='h3'>Contact Details</Header>
                        <p> For General enquiries:</p>
                        <p><strong>Phone:</strong> +44 (0) 207 048 4022</p>
                        <p><strong>Email:</strong> contact@spartaglobal.com</p>
                        <p> Our team:</p>
                        <p><strong>Emma Craig: </strong> ecraig@spartaglobal.com</p>
                        <p><strong>Eve Burton: </strong> eburton@spartaglobal.com</p>
                        <p><strong>Gabriella Sanchez: </strong> gsanchez@spartaglobal.com</p>
                        <p><strong>Imogen Burgham: </strong> iburgham@spartaglobal.com</p>
                        <p><strong>Irina Diana Gall: </strong> igall@spartaglobal.com</p>
                        <p><strong>Morgan Stewart:</strong> mstewart@spartaglobal.com</p>
                        <p><strong>Sadie Joelson-White:</strong> sjoelson-white@spartaglobal.com</p>

                    </Grid.Column>
                </Grid>

                <div className="form">
                    <Form>
                        <Form.Input
                            label='Your Name'
                            placeholder='Name'
                            required
                        />
                        <Form.Input
                            label='Your Email'
                            placeholder='Email'
                            type='email'
                            required
                        />
                        <Form.TextArea
                            label='Your Message'
                            placeholder='Type your message here...'
                            required
                        />
                        <Form.Button primary>Submit</Form.Button>
                    </Form>


                    <Segment textAlign='center' padded='very'>
                        <Header as='h4'>Follow Us</Header>
                        <p>Stay connected with us through social media!</p>
                        <Button circular color='facebook' icon='facebook' />
                        <Button circular color='twitter' icon='twitter' />
                        <Button circular color='linkedin' icon='linkedin' />
                        <Button circular color='instagram' icon='instagram' />
                    </Segment>
                </div>

            </div>

        </div>
    );
};

export default Contact;
