import web from "@/utils/request/index";

export async function getPosts(userId: string) {
    return web.request({
        url: '/post/homepage', method: "post", data: {
            user_id: userId,
        }
    });
}

export async function getPostDetail(postId: string) {
    return web.request({
        url: '/post/detail', method: "post", data: {
            post_id: postId,
        }
    });
}

export async function createNewPost(user_id: number, title: string, content: string, pic_post: string) {
    return web.request({
        url: '/post/insert', method: "post", data: {
            user_id: user_id, title: title, content: content, pic_post: pic_post
        }
    });
}



