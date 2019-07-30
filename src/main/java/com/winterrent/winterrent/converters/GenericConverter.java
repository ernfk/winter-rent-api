package com.winterrent.winterrent.converters;

import java.util.List;

public interface GenericConverter<E, D> {
    D createFromEntity(E entity);

    E createFromDTO(D dto);

    List<D> createListFromEntities(List<E> entities);
}