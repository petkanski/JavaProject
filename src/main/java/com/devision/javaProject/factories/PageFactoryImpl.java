package com.devision.javaProject.factories;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PageFactoryImpl implements PageFactory{

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_PAGE_SIZE = 20;
    private static final String SORT_PARAM_DELIMITER = ",";

    public PageRequest create (Integer page, Integer size, String sort){

        page = (page == null) ? DEFAULT_PAGE : page;
        size = (size == null) ? DEFAULT_PAGE_SIZE : size;

        if (sort == null){
            return PageRequest.of(page,size);
        }

        return PageRequest.of(page, size, getSortStrategy(sort));

    }

    private Sort getSortStrategy(String sort){

        String[] sortParams = sort.split(SORT_PARAM_DELIMITER);

        if(sortParams.length == 1){
            return Sort.by(sortParams[0]);
        }

        if(sortParams[1].toUpperCase().equals(Sort.Direction.DESC)){
            return Sort.by(sortParams[0]).descending();
        }else{
            return Sort.by(sortParams[0]).ascending();
        }

    }
}
