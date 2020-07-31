package com.example.socialnet.mappers

import com.example.socialnet.data.Comment
import com.example.socialnet.data.CommentGetResponse
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface CommentMapper {

    @Mapping(target = "commentId", source = "id")
    fun commentGetResponseToComment(commentGetResponse: CommentGetResponse) : Comment
}