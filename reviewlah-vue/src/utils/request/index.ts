import Request from "./request";
/**
 * process.env.VUE_APP_BASE_URL 根据NODE_ENV变化而变化
 */

/* eslint-disable */
// const token = String(window.localStorage.getItem('token'))
const web: Request = new Request({
    baseURL: process.env.VUE_APP_BASE_URL,
    timeout: 10000,
    headers: {
        'Content-Type': 'application/json',
        'Accept': "application/json",
        // 'Authorization': token,
    },
})

export default web
