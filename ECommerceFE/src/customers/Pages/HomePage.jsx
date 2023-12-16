import React from 'react'
import MainCarousel from '../components/HomeCarousel/MainCarousel';
import HomeSectionCarousel from '../components/HomeSectionCarousel/HomeSectionCarousel';
import { men_shirts } from '../../dataTest/MenShirt';

const HomePage = () => {
  return (
    <div>
        <MainCarousel/>

        <div className='space-y-10 py-2 flex flex-col justify-center px-5 lg:px-10'>
            <HomeSectionCarousel data={men_shirts} sectionName={"Men"}/>
            <HomeSectionCarousel data={men_shirts} sectionName={"Women"}/>
            <HomeSectionCarousel data={men_shirts} sectionName={"Shoes"}/>
            <HomeSectionCarousel data={men_shirts} sectionName={"Wallet"}/>
            <HomeSectionCarousel data={men_shirts} sectionName={"Kurta"}/>
            
        </div>
    </div>
  )
}

export default HomePage; 