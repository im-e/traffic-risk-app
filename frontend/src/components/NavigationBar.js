import React from 'react';
import { Menu, Container, Icon, Image } from 'semantic-ui-react';
import 'semantic-ui-css/semantic.min.css';
import './NavigationBar.css';
import spartaLogo from '/Users/dianag/Developer/traffic-risk-app/frontend/src/Sparta-Global-logo.png';

import { Link } from 'react-router-dom';

const NavigationBar = () => {
    return (
        <Menu fixed='top' inverted>
            <Container>
                <Menu.Item as='a' header>
                    <Image src={spartaLogo} alt="Sparta Global Logo" size='small' className="logo" />
                </Menu.Item>

                <div className="desktop-menu">
                    <Menu.Item as={Link} to='/' active>Home</Menu.Item>
                    <Menu.Item as={Link} to ='/about'>About</Menu.Item>
                    <Menu.Item as={Link} to='/evaluation'>Evaluation</Menu.Item>
                    <Menu.Item as={Link} to='/contact'>Contact</Menu.Item>
                </div>

                {/*<div className="mobile-menu">*/}
                {/*    <Menu.Item as='a'>*/}
                {/*        <Icon name='bars' />*/}
                {/*    </Menu.Item>*/}
                {/*</div>*/}
            </Container>
        </Menu>
    );
};

export default NavigationBar;