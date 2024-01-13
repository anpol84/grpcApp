package com.example.datageneratorgrpcmicroservice.web.mapper;

import org.springframework.stereotype.Component;

import java.util.List;

public interface Mappable<E, D>{
    E toEntity(D d);
    List<E> toEntity(List<D> d);

    D toDto(E e);
    List<D> toDto(List<E> e);
}
