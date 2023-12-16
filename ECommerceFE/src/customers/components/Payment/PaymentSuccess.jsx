import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useParams, useSearchParams } from "react-router-dom";
import { store } from "../../../State/store";
import { getOrderById } from "../../../State/Order/Action";
import { updatePayment } from "../../../State/Payment/Action";
import { Alert, AlertTitle, Grid } from "@mui/material";
import OrderTracker from "../Order/OrderTracker";
import AddressCard from "../AddressCard/AddressCard";

const PaymentSuccess = () => {
    const [paymentId, setPaymentId] = useState(null);
    const [paymentStatus, setPaymentStatus] = useState(null);
    const { orderId } = useParams();
    const dispatch = useDispatch();
    const { order } = useSelector((store) => store);

    useEffect(() => {
        const urlParams = new URLSearchParams(window.location.search);
        const vnp_OrderInfo = urlParams.get("vnp_OrderInfo");
        // Lấy phần tử đầu tiên của vnp_OrderInfo và gán vào paymentId
        if (vnp_OrderInfo) {
            const firstCharacter = vnp_OrderInfo.charAt(0);
            setPaymentId(firstCharacter);
        }

        setPaymentStatus(urlParams.get("vnp_ResponseCode"));
    }, []);

    console.log(paymentId, paymentStatus);

    useEffect(() => {
        // Chỉ dispatch khi có paymentId và paymentStatus đã được cập nhật
        if (paymentId && paymentStatus) {
            const data = { paymentId, paymentStatus };
            dispatch(getOrderById(data.paymentId));
            dispatch(updatePayment(data));
        }
    }, [dispatch, paymentId, paymentStatus]);

    return (
        <div className="px-2 lg:px-36">
            <div className="flex flex-col justify-center items-center">
                <Alert
                    variant="filled"
                    severity="success"
                    sx={{ mb: 6, width: "fit-content" }}
                >
                    <AlertTitle>Payment Success</AlertTitle>
                    Congratulation Your Order Get Placed
                </Alert>
            </div>

            <OrderTracker activeStep={1} />

            <Grid container className="space-y-5 py-5 pt-20">
                {order.order?.orderItems.map((item) => (
                    <Grid
                        container
                        item
                        className="shadow-xl rounded-md p-5 border"
                        sx={{
                            alignItems: "center",
                            justifyContent: "space-between",
                        }}
                    >
                        <Grid item xs={6}>
                            {" "}
                            <div className="flex  items-center ">
                                <img
                                    className="w-[5rem] h-[5rem] object-cover object-top"
                                    src={item?.product.imageUrl}
                                    alt=""
                                />
                                <div className="ml-5 space-y-2">
                                    <p className="">{item.product.title}</p>
                                    <p className="opacity-50 text-xs font-semibold space-x-5">
                                        <span>Color: {item.product.color}</span>{" "}
                                        <span>Size: {item.size}</span>
                                    </p>
                                    <p>Seller: {item.product.brand}</p>
                                    <p>${item.product.discountedPrice}</p>
                                </div>
                            </div>
                        </Grid>
                        <Grid item>
                            <AddressCard
                                address={order.order?.shippingAddress}
                            />
                        </Grid>
                    </Grid>
                ))}
            </Grid>
        </div>
    );
};

export default PaymentSuccess;
