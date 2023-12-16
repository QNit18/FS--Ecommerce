import React from 'react';
import AliceCarousel from 'react-alice-carousel';
import 'react-alice-carousel/lib/alice-carousel.css';
import { MainCarouselData } from './MainCarouselData';


const items = MainCarouselData.map(item =>
    <img className='cursor-pointer -z-10' role='presentation' src={item.image} alt='' />)

const MainCarousel = () => (
    <AliceCarousel
        items={items}
        controlsStrategy="alternate"
        autoPlay
        autoPlayInterval={3000}
        disableButtonsControls
    />
);

export default MainCarousel;