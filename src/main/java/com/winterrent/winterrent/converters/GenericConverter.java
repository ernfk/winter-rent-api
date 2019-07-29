package com.winterrent.winterrent.converters;

public interface GenericConverter<E, D> {
    D createFromEntity(E entity);

    E createFromDTO(D dto);
}