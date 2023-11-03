import web from "@/utils/request/index";

export async function getComments(userId: any) {
    return web.request({
        url: '/merchant/diningComment/showAllForMerchant', method: "post", data: {
            user_id: userId,
        }
    });
}



