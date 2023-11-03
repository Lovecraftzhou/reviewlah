import web from "@/utils/request/index";

export async function getPosts(userId: string) {
    return web.request({
        url: '/post/homepage', method: "post", data: {
            user_id: userId,
        }
    });
}

export async function getPostDetail(postId: string){
    return web.request({
        url: '/post/detail', method: "post", data: {
            post_id: postId,
        }
    });
}



