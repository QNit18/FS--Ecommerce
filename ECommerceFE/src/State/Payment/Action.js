import { api } from "../../config/apiConfig";
import { CREATE_ORDER_FAILURE } from "../Order/ActionType";
import {
    CREATE_PAYMENT_REQUEST,
    UPDATE_PAYMENT_FAILURE,
    UPDATE_PAYMENT_REQUEST,
    UPDATE_PAYMENT_SUCCESS,
} from "./ActionType";

export const createPayment = (orderId) => async (dispatch) => {
    dispatch({ type: CREATE_PAYMENT_REQUEST });
    try {
        const { data } = await api.get(`/api/payments/${orderId}`);

        if (data.url) {
            window.location.href = data.url;
        }
    } catch (error) {
        dispatch({ type: CREATE_ORDER_FAILURE, payload: error.message });
    }
};

export const updatePayment = (reqData) => async (dispatch) => {
    dispatch({ type: UPDATE_PAYMENT_REQUEST });
    try {
        if (reqData.paymentStatus == "00") {
            const { data } = await api.get(`/api/payments/update/${reqData.paymentId}`);
            dispatch({ type: UPDATE_PAYMENT_SUCCESS });
        } else {
            window.location.href = "/payment/2/fail";
        }
    } catch (error) {
        dispatch({ type: UPDATE_PAYMENT_FAILURE, payload: error.message });
    }
};
