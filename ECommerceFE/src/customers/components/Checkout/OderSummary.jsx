import React, { useEffect } from 'react'
import AddressCard from '../AddressCard/AddressCard'
import { Button } from '@mui/material'
import CartItem from '../Cart/CartItem'
import { useLocation, useNavigate } from 'react-router-dom'
import { useDispatch, useSelector } from 'react-redux'
import { getOrderById } from '../../../State/Order/Action'
import { createPayment } from '../../../State/Payment/Action'

const OderSummary = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const dispatch = useDispatch();
  const searchParams = new URLSearchParams(location.search);

  const orderId = searchParams.get("order_id");
  const jwt = localStorage.getItem("jwt");
  const { order } = useSelector(store => store);


  useEffect(() => {
    dispatch(getOrderById(orderId))
  }, [orderId])

  const handleCheckout = () => {
    dispatch(createPayment(orderId))
  }

  return (
    <div>
      <div className='p-5 shadow-lg rounded-s-sm border'>
        <AddressCard address={order.order?.shippingAddress}/>
      </div>

      <div>
        <div className='lg:grid grid-cols-3 relative'>
          <div className='col-span-2'>
            {order.order?.orderItems.map(item => 
            <CartItem item={item} />
            )}
          </div>
          <div className='px-5 sticky top-0 h-[100vh] mt-5 lg:mt-0'>
            <div className='border p-5 shadow-lg rounded-md mt-2'>
              <p className='uppercase font-bold opacity-60 pb-4'>Price details</p>
              <hr />
              <div className='space-y-3 mb-10'>
                <div className='flex justify-between pt-3 text-black'>
                  <span>Giá ({order.order?.totalItem})</span>
                  <span>${order.order?.totalPrice}</span>
                </div>
                <div className='flex justify-between pt-3'>
                  <span>Discount</span>
                  <span className='text-green-600'>-${order.order?.discounte}</span>
                </div>
                <div className='flex justify-between pt-3'>
                  <span>Delivery</span>
                  <span className='text-green-600'>Free</span>
                </div>
                <div className='flex justify-between pt-3 font-semibold '>
                  <span>Total</span>
                  <span className='text-green'>${order.order?.totalDiscountedPrice}</span>
                </div>
              </div>
              <Button variant='contained'
                className='w-full mt-5'
                sx={{ px: "2.5rem", py: ".7rem", bgcolor: "#1d0be0" }}
                onClick={handleCheckout}
                >
                Checkout
              </Button>
            </div>

          </div>
        </div>
      </div>
    </div>
  )
}

export default OderSummary