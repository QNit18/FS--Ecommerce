import Button from '@mui/material/Button';
import { Grid, TextField } from '@mui/material'
import React, { useEffect, useState } from 'react'
import { Navigate, useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { getUser, register } from '../../State/Auth/Action';
import { store } from '../../State/store';

const RegisterForm = () => {
  const navigate = useNavigate();  
  const dispatch = useDispatch();
  const jwt = localStorage.getItem("jwt")
  const {auth} = useSelector(store => store)
  const [validEmail, setValidEmail] = useState(true);

  useEffect(() => {
    if (jwt) {
      dispatch(getUser(jwt))
    }
  }, [jwt, auth.jwt])

  const handleSubmit = (event) => {
    event.preventDefault()

    const data = new FormData(event.currentTarget);
    
    const userData = {
      firstName : data.get("firstName"),
      lastName : data.get("lastName"),
      email : data.get("email"),
      password : data.get("password")
    }

    // Check for valid email
    if (!validateEmail(userData.email)) {
      setValidEmail(false);
      return;
    }
    
    dispatch(register(userData));
    console.log("userData", userData);
  }

  const validateEmail = (email) => {
    // Định dạng email cơ bản
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(String(email).toLowerCase());
  }
  
  return (
    <div>
      <form onSubmit={handleSubmit}>
        <Grid container spacing={3}>
          <Grid item xs={12} sm={6}>
            <TextField id='firstName'
              name='firstName'
              label='First Name'
              fullWidth
              autoComplete='given-name' required />
          </Grid>
          <Grid item xs={12} sm={6}>
            <TextField id='lastName'
              name='lastName'
              label='Last Name'
              fullWidth
              autoComplete='given-name' required />
          </Grid>
          <Grid item xs={12}>
            <TextField id='email'
              name='email'
              label='Email'
              fullWidth
              autoComplete='email'
              error={!validEmail} // Set error state based on email validation
              helperText={!validEmail ? 'Email không đúng định dạng vui lòng nhập lại' : ''}
              required />
          </Grid>
          <Grid item xs={12}>
            <TextField id='password'
              name='password'
              label='Password'
              fullWidth
              autoComplete='password' required />
          </Grid>
          <Grid item xs={12}>
            <Button variant="contained"
              className='w-full'
              type='submit' size='large'
              sx={{ padding: ".8rem 0", bgcolor: "#9115FD" }}>
              Register
            </Button>
          </Grid>
        </Grid>
      </form>
      <div className='flex justify-center flex-col items-center'>
        <div className='py-3 flex items-center'>
          <p>If you have already account ?</p>
          <Button onClick={() => navigate("/login")} className='ml-5' size='small'>
            Login
          </Button>
        </div>
      </div>
    </div>
  )
}

export default RegisterForm