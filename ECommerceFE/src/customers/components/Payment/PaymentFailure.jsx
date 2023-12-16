import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Alert, AlertTitle, Button, Grid } from "@mui/material";
import { useParams } from "react-router-dom";

const PaymentFail = () => {
    const { orderId } = useParams();
    console.log("Order", orderId);

    return (
        <div className="px-2 lg:px-36">
            <div className="flex flex-col justify-center items-center">
                <div style={{ marginBottom: '10px' }}>
                    <Alert
                        variant="filled"
                        severity="error"
                        sx={{ width: "fit-content" }}
                    >
                        <AlertTitle>Thanh toán thất bại</AlertTitle>
                        Mời bạn lựa chọn
                    </Alert>
                </div>
                <div style={{ display: 'flex', gap: '50px' }} className="mt-5">
                    <div className="md-5">
                        <Button variant="contained" color="primary" href="/">
                            Trang chủ
                        </Button>
                    </div>
                    <div>
                        <Button className="ml-1" variant="contained" color="secondary" href={`/cart`}>
                            Thanh toán
                        </Button>
                    </div>
                </div>
                <img src= {`https://i.imgur.com/puIEpfB.png`}/>
            </div>
        </div>
    );
};

export default PaymentFail;
