import React from 'react';
import { Menu, Container, Icon } from 'semantic-ui-react';
import 'semantic-ui-css/semantic.min.css';
import './NavigationBar.css';

const NavigationBar = () => {
    return (
        <Menu fixed='top' inverted>
            <Container>
                <Menu.Item as='a' header> Some Logo </Menu.Item>

                <div className="desktop-menu">
                    <Menu.Item as='a' active>Home</Menu.Item>
                    <Menu.Item as='a'>About</Menu.Item>
                    <Menu.Item as='a'>Services</Menu.Item>
                    <Menu.Item as='a'>Contact</Menu.Item>
                </div>

                <div className="mobile-menu">
                    <Menu.Item as='a'>
                        <Icon name='bars' />
                    </Menu.Item>
                </div>
            </Container>
        </Menu>
    );
};

export default NavigationBar;