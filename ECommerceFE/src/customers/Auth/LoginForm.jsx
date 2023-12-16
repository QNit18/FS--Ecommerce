import Button from '@mui/material/Button';
import { Grid, TextField } from '@mui/material'
import React, { useEffect } from 'react'
import { useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { getUser, login } from '../../State/Auth/Action';

const LoginForm = () => {

  const dispatch = useDispatch(); //
  const navigate = useNavigate(); // điều hướng và chuyển hướng URL

  const handleSubmit = (event) => {
    event.preventDefault() // đảm bảo trang không được tải lại khi gửi request đi

    const data = new FormData(event.currentTarget);

    const userData = {
      email: data.get("email"),
      password: data.get("password")
    }

    dispatch(login(userData))
    console.log("userData", userData);
  }
  return (
    <div>
      <form onSubmit={handleSubmit}>
        <Grid container spacing={3}>

          <Grid item xs={12}>
            <TextField id='email'
              name='email'
              label='Email'
              fullWidth
              autoComplete='email' required />
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
              Login
            </Button>
          </Grid>
        </Grid>
      </form>
      <div className='flex justify-center flex-col items-center'>
        <div className='py-3 flex items-center'>
          <p>If you don't already account ?</p>
          <Button onClick={() => navigate("/register")} className='ml-5' size='small'>
            Register
          </Button>
        </div>
      </div>
    </div>
  )
}

export default LoginForm