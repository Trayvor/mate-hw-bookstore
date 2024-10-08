package org.example.bookstore.mapper;

import org.example.bookstore.config.MapperConfig;
import org.example.bookstore.dto.cart.item.CartItemDto;
import org.example.bookstore.dto.cart.item.CreateCartItemRequestDto;
import org.example.bookstore.dto.cart.item.UpdateCartItemRequestDto;
import org.example.bookstore.model.Book;
import org.example.bookstore.model.CartItem;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface CartItemMapper {
    CartItem toModel(CreateCartItemRequestDto createCartItemRequestDto);

    CartItem toModel(CartItemDto cartItemDto);

    @AfterMapping
    default void setModelValues(@MappingTarget CartItem cartItem,
                                CreateCartItemRequestDto createCartItemRequestDto) {
        Book book = new Book();
        book.setId(createCartItemRequestDto.getBookId());
        cartItem.setBook(book);
    }

    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "book.title", target = "bookTitle")
    CartItemDto toDto(CartItem cartItem);

    void updateCartItemFromRequestDto(UpdateCartItemRequestDto updateCartItemRequestDto,
                                      @MappingTarget CartItem cartItem);
}
