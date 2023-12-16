import { Avatar, Box, Grid, Rating } from '@mui/material'
import React from 'react'

const ProductReviewCards = () => {
  return (
    <div>
        <Grid container spacing={2} gap={3}>
            <Grid item xs={1}>
                <Box>
                    <Avatar className='text-white' sx={{width:56, height:56, bgcolor:"#ba0fa6"}}>
                        VQ
                    </Avatar>
                </Box>
            </Grid>

            <Grid item xs={9}>
                <div className='space-y-2'>
                    <div>
                        <p className='font-semibold text-lg'>Raam</p>
                        <p className='opacity-70'>Thang 4, 2023</p>
                    </div>
                </div>

                <Rating value={4.5} name='half-rating' readOnly precision={.5}></Rating>
                <p>Nhan xet o day</p>
            </Grid>
        </Grid>
    </div>
  )
}

export default ProductReviewCards