package com.example.socialnet.mappers

import com.example.socialnet.data.Post
import com.example.socialnet.data.PostGetResponse
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface PostMapper {

    @Mapping(target = "postId", source = "id")
    fun postGetResponseToPost(postGetResponse: PostGetResponse) : Post
}