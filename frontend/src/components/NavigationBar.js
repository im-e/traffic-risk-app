import React from 'react';
import { Menu, Container, Icon, Image } from 'semantic-ui-react';
import 'semantic-ui-css/semantic.min.css';
import './NavigationBar.css';
import spartaLogo from '../images/sparta-white-text.png'


import { Link } from 'react-router-dom';

const NavigationBar = () => {
    return (
        <Menu fixed='top' inverted>
            <Container>
                <div className="desktop-menu">
                    <Menu.Item as={Link} to='/' className="logo-item">
                        <Image src={spartaLogo} alt="Sparta Global Logo" size='small' className="logo" />
                    </Menu.Item>
                    <Menu.Item as={Link} to='/'>Home</Menu.Item>
                    <Menu.Item as={Link} to='/evaluation'>Evaluation</Menu.Item>
                    <Menu.Item as={Link} to='/data-visualiser'>Data</Menu.Item>
                    <Menu.Item as={Link} to='/contact'>Contact</Menu.Item>
                </div>

            </Container>
        </Menu>
    );
};

export default NavigationBar;
