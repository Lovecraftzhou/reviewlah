import axios, { AxiosInstance, AxiosRequestConfig } from 'axios';
// import type { AxiosInstance , AxiosResponse } from 'axios';

class Request {
    private instance: AxiosInstance | undefined

    constructor(requeseConfig: AxiosRequestConfig) {
        this.instance = axios.create(requeseConfig)

        // 全局请求拦截
        this.instance.interceptors.request.use(
            (config) => {
                console.log("全局请求拦截的", config);
                return config
            },
            (error) => {
                console.log("全局请求拦截失败", error);
            },
        )

        // 全局响应拦截
        this.instance.interceptors.response.use(
            (res) => {
                // res 为AxiosResponse 类型，含有conig\data\headers\request\status\statusText属性
                console.log("全局响应拦截的", res);
                return res.data
                // 只需要返回data即可
            },
            (error) => {
                console.log("全局响应失败拦截");
                console.log(error.request);
                console.log(error.response);
                return error
            },
        )
    }

    request<T>(config: AxiosRequestConfig<T>): Promise<T> {
        return new Promise<T>((resolve, reject) => {
            /* eslint-disable */
            this.instance?.request<any, T>(config)
                .then((res) => {
                    resolve(res)
                })
                .catch((err) => {
                    reject(err)
                })
        })
    }

    get(url: string) {
        return new Promise((resolve, reject) => {
            this.instance?.post(url)
                .then((res) => {
                    resolve(res.data)
                })
                .catch((err) => {
                    reject(err.data)
                })
        })
    }

    post(url: string, data = {}) {
        return new Promise((resolve, reject) => {
            this.instance?.post(url, data)
                .then((res) => {
                    resolve(res.data)
                })
                .catch((err) => {
                    reject(err.data)
                })
        })
    }

}

export default Request
