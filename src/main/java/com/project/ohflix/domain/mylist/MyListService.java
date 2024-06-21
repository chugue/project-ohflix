package com.project.ohflix.domain.mylist;

import com.project.ohflix.domain.profileIcon.ProfileIcon;
import com.project.ohflix.domain.profileIcon.ProfileIconResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyListService {

    private final MyListRepository myListRepository;

    public List<MyListResponse.MyFavoriteListDTO> findMyListById(Integer sessionUserId) {
        List<MyList> myFavoriteList = myListRepository.findMyListByUserId(sessionUserId);

        System.out.println("승호승호승호승호승호승호");
        System.out.println(myFavoriteList);
        return myFavoriteList.stream().map(myList -> new MyListResponse.MyFavoriteListDTO(myList)).toList();
    }
}





