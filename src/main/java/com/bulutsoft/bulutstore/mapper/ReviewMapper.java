package com.bulutsoft.bulutstore.mapper;

import com.bulutsoft.bulutstore.entity.Review;
import com.bulutsoft.bulutstore.request.ReviewRequest;
import com.bulutsoft.bulutstore.response.ReviewResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

/**
 * Review <-> ReviewDto dönüşümlerini sağlayan MapStruct mapper arayüzü.
 * Nested mapping için AppMapper ve UserMapper kullanılır.
 */
@Mapper(componentModel = "spring", uses = {AppMapper.class, UserMapper.class})
public interface ReviewMapper {
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "appId", source = "app.id")
    ReviewResponse toResponse(Review review);
    List<ReviewResponse> toResponseList(List<Review> reviews);
    Review toEntity(ReviewRequest request);
}
