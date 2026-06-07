package com.shop.repository;

import com.shop.dto.ItemSearchDto;
import com.shop.entity.Item;
import com.shop.dto.MainItemDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

public interface ItemRepositoryCustom {

    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
//    상품 조건을 담고 있는 itemSearchDto 객체와 페이징 정보를 담고 있는 pageable 객체를 파라미터로 받는
//    getAdminItemPage 메소드를 정의, 반환 데이터로 Page<item> 객체를 반환

    Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
//    메인 페이지에 보여줄 상품 리스트를 가져오는 메소드 getMainItemPage() 메소드를 CustomImpl 클래스에 구현
}
