import web from "@/utils/request/index";
import qs from "qs";

// const API_PROXY_PREFIX=`/api`

export async function login(name: any, password: any) {
    return web.request({
        url: '/user/login', method: "post", data: {
            name: name,
            password: password,
        }
    });
}

export async function signup(name: string,
                             password: string,
                             phone_number: string,
                             email: string,
                             type: string,
                             avator: string,
                             address_code: string,
                             address_detail: string,
                             unitnum: string,
                             category: string[]) {
    let typeid = 1
    if (type == 'Merchant')
        typeid = 2
    return web.request({
        url: '/user/insert', method: "post", data: {
            name: name,
            password: password,
            phone_number: phone_number,
            email: email,
            type: typeid,
            avator: avator,
            address_code: address_code,
            address_detail: address_detail,
            unitnum: unitnum,
            category: category
        }
    });
}

export async function updateUserInfo(user_id: number,
                                     name = "",
                                     password="",
                                     phone_number="",
                                     email="",
                                     avator="") {
    return web.request({
        url: '/user/personalInfo/update', method: "post", data: {
            user_id:user_id,
            name: name,
            password: password,
            phone_number: phone_number,
            email: email,
            avator: avator,
        }
    });
}

export async function getUserInfo(user_id: number) {
    return web.request({
        url: '/user/get', method: "post", data: {
            user_id:user_id,
        }
    });
}

