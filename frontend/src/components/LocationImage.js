import React, { useState, useEffect, useCallback } from 'react';
import { Card, Image, Loader } from 'semantic-ui-react';
import axios from 'axios';
import './LocationImage.css';

const LocationImage = ({ zip, milesPerDay }) => {
    const [imageUrl, setImageUrl] = useState(null);
    const [loading, setLoading] = useState(true);
    const [imageLoaded, setImageLoaded] = useState(false);
    const [error, setError] = useState(null);

    const fetchImage = useCallback(async () => {
        try {
            setLoading(true);
            setImageLoaded(false);
            setError(null);
            const response = await axios.get(`/image?zip=${zip}&milesPerDay=${milesPerDay}`, {
                responseType: 'blob'
            });
            const url = URL.createObjectURL(response.data);
            setImageUrl(url);
        } catch (err) {
            console.error('Error fetching image:', err);
            setError(`Failed to load image: ${err.message}`);
            setLoading(false);
        }
    }, [zip, milesPerDay]);

    useEffect(() => {
        fetchImage();

        return () => {
            if (imageUrl) {
                URL.revokeObjectURL(imageUrl);
            }
        };
    }, [fetchImage]);

    const handleImageLoad = () => {
        setImageLoaded(true);
        setLoading(false);
    };

    const handleImageError = () => {
        setError('Failed to load image');
        setLoading(false);
    };

    return (
        <div className="location-image-wrapper">
            <Card className="location-image-card">
                <Card.Content>
                    <Card.Header className="header">Location Map</Card.Header>
                    <Card.Description className="content">
                        {loading && !imageLoaded && <Loader active inline='centered'/>}
                        {error && <p className="error">{error}</p>}
                        {imageUrl && (
                            <Image
                                src={imageUrl}
                                alt={`Map of ${zip}, ${milesPerDay}`}
                                fluid
                                onLoad={handleImageLoad}
                                onError={handleImageError}
                                style={{display: imageLoaded ? 'block' : 'none'}}
                            />
                        )}
                    </Card.Description>
                </Card.Content>
                <Card.Content extra className="extra">
                    ZIP: {zip}, miles: {milesPerDay}
                </Card.Content>
            </Card>
        </div>
    );
};

export default LocationImage;