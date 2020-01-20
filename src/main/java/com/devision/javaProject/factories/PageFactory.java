package com.devision.javaProject.factories;

import org.springframework.data.domain.PageRequest;

public interface PageFactory {

    PageRequest create (Integer page, Integer size, String sort);
}
