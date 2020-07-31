package com.example.socialnet.mappers

import com.example.socialnet.data.User
import com.example.socialnet.data.UserGetResponse
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper
interface UserMapper {

    @Mapping(target = "userId", source = "id")
    fun userGetResponseToUser(userGetResponse: UserGetResponse) : User
}