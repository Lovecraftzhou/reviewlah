import web from "@/utils/request/index";

export async function getMerchantRecommendList(userId: string) {
    return web.request({
        url: '/merchant/merchantRecommendList', method: "post", data: {
            user_id: userId,
        }
    });
}

export async function getMerchantDeatail(userId: string){
    return web.request({
        url: '/merchant/merchantList/merchantPage', method: "post", data: {
            user_id: userId,
        }
    });
}

export async function searchMerchant(searchInput: string){
    return web.request({
        url: '/merchant/merchantList/selectByKeyword', method: "post", data: {
            keyword: searchInput,
        }
    });
}

export async function addAnnouncement(  user_id: string, content: string){
    return web.request({
        url: '/merchant/announcement/insert_announcement', method: "post", data: {
            user_id: user_id,
            content: content
        }
    });
}

export async function deleteAnnouncement(  announcement_id: string){
    return web.request({
        url: '/merchant/announcement/delete_announcement', method: "post", data: {
            announcement_id: announcement_id,
        }
    });
}
export async function updateAddress(  user_id: number,
    address_code: string,
    address_detail: string,
    unitnum: string){
    return web.request({
        url: '/merchant/address/update', method: "post", data: {
            user_id: user_id,
            address_code: address_code,
            address_detail: address_detail,
            unitnum: unitnum,
        }
    });
}
export async function updateCategory(  user_id: number,
                                       category:string[]
){
    return web.request({
        url: '/merchant/category/update', method: "post", data: {
            user_id: user_id,
            category: category,
        }
    });
}


