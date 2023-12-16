import { Button, Grid, Link, Typography } from '@mui/material';
import React from 'react'

const Footer = () => {
    return (
        <div>
            <Grid className='bg-black text-white text-center mt-10'
                container
                sx={{ bgcolor: "black", color: "white", py: 3 }}>
                <Grid item xs={12} sm={6} md={3}>
                    <div>
                        <Typography className='pb-5' variant='h6'> Company </Typography>
                    </div>
                    <div>
                        <Button className='pb-5' variant='h6' gutterBottom> About </Button>
                    </div>
                    <div>
                        <Button className='pb-5' variant='h6' gutterBottom> BLog </Button>
                    </div>
                    <div>
                        <Button className='pb-5' variant='h6' gutterBottom> Press </Button>
                    </div>
                    <div>
                        <Button className='pb-5' variant='h6' gutterBottom> Partners </Button>
                    </div>
                    <div>
                        <Button className='pb-5' variant='h6' gutterBottom> Jobs </Button>
                    </div>
                </Grid>
                <Grid item xs={12} sm={6} md={3}>
                    <div>
                        <Typography className='pb-5' variant='h6'> Solutions </Typography>
                    </div>
                    <div>
                        <Button className='pb-5' variant='h6' gutterBottom> Marketing </Button>
                    </div>
                    <div>
                        <Button className='pb-5' variant='h6' gutterBottom> Analytics </Button>
                    </div>
                    <div>
                        <Button className='pb-5' variant='h6' gutterBottom> Commerce </Button>
                    </div>
                    <div>
                        <Button className='pb-5' variant='h6' gutterBottom> Insights </Button>
                    </div>
                    <div>
                        <Button className='pb-5' variant='h6' gutterBottom> Support </Button>
                    </div>
                </Grid>
                <Grid item xs={12} sm={6} md={3}>
                    <div>
                        <Typography className='pb-5' variant='h6'> Document </Typography>
                    </div>
                    <div>
                        <Button className='pb-5' variant='h6' gutterBottom> Guides </Button>
                    </div>
                    <div>
                        <Button className='pb-5' variant='h6' gutterBottom> Api </Button>
                    </div>
                </Grid>
                <Grid item xs={12} sm={6} md={3}>
                    <div>
                        <Typography className='pb-5' variant='h6'> Legal </Typography>
                    </div>
                    <div>
                        <Button className='pb-5' variant='h6' gutterBottom>Claim</Button>
                    </div>
                    <div>
                        <Button className='pb-5' variant='h6' gutterBottom> Privacy </Button>
                    </div>
                    <div>
                        <Button className='pb-5' variant='h6' gutterBottom> Terms </Button>
                    </div>
                </Grid>
                <Grid item xs={12} className='pt-20'>
                    <Typography variant='body2' component={"p"} align='center'>
                        &copy; 2023 My Company. ALl rights reserved.
                    </Typography>
                    <Typography variant='body2' component={"p"} align='center'>
                        Made with love by Me.
                    </Typography>
                    <Typography variant='body2' component={"p"} align='center'>
                        Icons made by{' '}
                        <Link href="https://www.freeik.com" color={"inherit"} underline='always'>
                            Freepik 
                        </Link>
                        {' '} from{' '}
                        <Link href="https://www.flaticon.com" color={"inherit"} underline='always'>
                            www.flaticon.com
                        </Link>
                    </Typography>
                </Grid>
            </Grid>
        </div>
    )
}

export default Footer;