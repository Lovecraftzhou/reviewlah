import web from "@/utils/request/index";

export async function getMerchantRecommendList() {
    return web.request({
        url: '/merchant/merchantList', method: "get"
    });
}

export async function getMerchantDeatail(userId: string){
    return web.request({
        url: '/merchant/merchantList/merchantPage', method: "post", data: {
            user_id: userId,
        }
    });
}
