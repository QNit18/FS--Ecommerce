import { Box, Modal, Typography } from '@mui/material'
import React from 'react'
import RegisterForm from './RegisterForm';
import { useLocation } from 'react-router-dom';
import { Login } from '@mui/icons-material';
import LoginForm from './LoginForm';


const style = {
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 500,
  bgcolor: 'background.paper',
  border: '2px solid #000', // outline : "none",
  boxShadow: 24,
  p: 4,
};


const AuthModel = ({ handleClose, open }) => {

  const location = useLocation(); // lấy vị trí hiện tại

  return (
    <div>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          {location.pathname === "/login" ?
            <LoginForm/> 
            :
            <RegisterForm />
          }
        </Box>
      </Modal>
    </div>
  )
}

export default AuthModel