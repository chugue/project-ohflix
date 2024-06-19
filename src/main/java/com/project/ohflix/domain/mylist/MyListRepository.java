package com.project.ohflix.domain.mylist;

import com.project.ohflix.domain.content.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyListRepository extends JpaRepository<Content, Integer> {

}
