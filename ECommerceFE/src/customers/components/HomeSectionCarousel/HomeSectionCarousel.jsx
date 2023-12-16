import React, { useState, useEffect } from 'react';
import AliceCarousel from 'react-alice-carousel';
import HomeSectionCard from '../HomeSectionCard/HomeSectionCard';
import KeyboardArrowLeftIcon from '@mui/icons-material/KeyboardArrowLeft';
import KeyboardArrowRightIcon from '@mui/icons-material/KeyboardArrowRight';
import { Button } from '@mui/material';

const HomeSectionCarousel = ({ data, sectionName }) => {
    const [activeIndex, setActiveIndex] = useState(0);
    const [items, setItems] = useState([]);

    const responsive = {
        0: { items: 1 },
        720: { items: 3 },
        1024: { items: 5.5 },
    };

    useEffect(() => {
        const slicedItems = data.slice(activeIndex, activeIndex + 10);
        setItems(slicedItems);
    }, [activeIndex, data]);

    const slidePrev = () => setActiveIndex(activeIndex - 1 < 0 ? 0 : activeIndex - 1);
    const slideNext = () => setActiveIndex(activeIndex + 1 >= data.length ? activeIndex : activeIndex + 1);

    return (
        <div className="border">
            <h2 style={{ fontSize: '1.5rem', fontWeight: '800', color: '#333', padding: '1rem 0'}}>{sectionName}</h2>
            <div className="relative p-5">
                <AliceCarousel
                    items={items.map(item => <HomeSectionCard key={item.id} product={item} />)}
                    controlsStrategy="alternate"
                    responsive={responsive}
                    disableButtonsControls
                    disableDotsControls
                    activeIndex={activeIndex}
                />
                {activeIndex !== 0 &&
                    <Button
                        variant="contained"
                        className="z-50"
                        onClick={slidePrev}
                        sx={{
                            position: "absolute",
                            top: "8rem",
                            left: "0rem",
                            transform: "translateX(-50%) rotate(90deg)",
                            bgcolor: "white",
                        }}
                        aria-label="prev"
                    >
                        <KeyboardArrowRightIcon
                            sx={{ transform: "rotate(90deg)", color: "black" }}
                        />
                    </Button>
                }
                {activeIndex + 10 < data.length &&
                    <Button
                        variant="contained"
                        className="z-50"
                        onClick={slideNext}
                        sx={{
                            position: "absolute",
                            top: "8rem",
                            right: "0rem",
                            transform: "translateX(50%) rotate(90deg)",
                            bgcolor: "white",
                        }}
                        aria-label="next"
                    >
                        <KeyboardArrowLeftIcon
                            sx={{ transform: "rotate(90deg)", color: "black" }}
                        />
                    </Button>
                }
            </div>
        </div>
    );
}

export default HomeSectionCarousel;
