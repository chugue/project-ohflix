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

        return myFavoriteList.stream().map(myList -> new MyListResponse.MyFavoriteListDTO(myList)).toList();
    }
}





