import React from 'react'
import { Route, Routes } from 'react-router-dom'
import HomePage from '../../Pages/HomePage';
import Cart from '../Cart/Cart';
import Navigation from '../Navigation/Navigation';
import Footer from '../Footer/Footer';
import Product from '../Product/Product';
import ProductDetails from '../ProductDetails/ProductDetails';
import Checkout from '../Checkout/Checkout';
import Order from '../Order/Order';
import OrderDetails from '../Order/OrderDetails';
import PaymentSuccess from '../Payment/PaymentSuccess';
import PaymentFail from '../Payment/PaymentFailure';


const CustomRouters = () => {
  return (
    <div>
      <div>
        <Navigation />
      </div>

      <div>
        <Routes>
          <Route path='/login' element={<HomePage/>}></Route>
          <Route path='/register' element={<HomePage/>}></Route>
          <Route path='/' element={<HomePage/>}></Route>
          <Route path='/cart' element={<Cart/>}></Route>
          <Route path='/:lavelOne/:lavelTwo/:lavelThree' element={<Product/>}></Route>
          <Route path='/product/:productId' element={<ProductDetails/>}></Route>
          <Route path='/checkout' element={<Checkout/>}></Route>
          <Route path='/account/order' element={<Order/>}></Route>
          <Route path='/account/order/:orderId' element={<OrderDetails/>}></Route>
          <Route path='/payment/:orderId' element={<PaymentSuccess/>}></Route>
          <Route path='/payment/:orderId/fail' element={<PaymentFail/>}></Route>



        </Routes>
      </div>

      <div>
        <Footer />
      </div>
    </div>
  )
}

export default CustomRouters

{/* <HomePage /> */}
{/* <Product/> */}
{/* <ProductDetails/> */}
{/* <Cart/> */}
{/* <Checkout/> */}
{/* <Order/> */}
{/* <OrderDetails/> */}