import web from "@/utils/request/index";
import qs from "qs";
// const API_PROXY_PREFIX=`/api`

export async function login(name: any, password: any) {
    return web.request({url:'/user/login',method:"post",data:{
        name: name,
        password: password,
    }});
}
export async function signup(name:string,
                             phone_number:string,
                             email:string,
                             password:string,
                             type:number,
                             avator:string,
                             address_code:string,
                             address_detail:string,
                             unitnum:string,
                             category:string[]) {
    return web.request({url:'/user/insert',method:"post",data:{
            name: name,
            password: password,
            phone_number:phone_number,
            email:email,
            type:type,
            avator:avator,
            address_code:address_code,
            address_detail:address_detail,
            unitnum:unitnum,
            category:category
        }});
}