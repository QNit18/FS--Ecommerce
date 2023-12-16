import './App.css';
import Navigation from './customers/components/Navigation/Navigation';
import Footer from './customers/components/Footer/Footer';
import Product from './customers/components/Product/Product';
import ProductDetails from './customers/components/ProductDetails/ProductDetails';
import Cart from './customers/components/Cart/Cart';
import Checkout from './customers/components/Checkout/Checkout';
import Order from './customers/components/Order/Order';
import OrderDetails from './customers/components/Order/OrderDetails';
import { Route, Routes } from 'react-router-dom';
import CustomRouters from './customers/components/Router/CustomRouters';

function App() {
  return (
    <div className="">

      <Routes>
        <Route path='/*' element={<CustomRouters/>}></Route>
      </Routes>
      
    </div>
  );
}

export default App;
