package com.project.ohflix;

import com.project.ohflix.domain.mylist.MyList;
import com.project.ohflix.domain.mylist.MyListRepository;
import com.project.ohflix.domain.mylist.MyListResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MyListRepositoryTest {
    @Autowired
    private MyListRepository myListRepository;


    @Test
    public void MyFavoriteList_test() {
        // given
        int id = 1;

        // when
        List<MyList> myLists = myListRepository.findMyListByUserId(id);

        // then
    }
}
