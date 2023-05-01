package org.example.util;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public final class EntityUtil {
    private EntityUtil() {
        throw new AssertionError();
    }

    public static <S, T> List<T> listToListDtoConverter(ModelMapper modelMapper, List<S> source, Class<T> target) {
        return source.stream().map(sourceElement -> modelMapper.map(sourceElement, target)).collect(Collectors.toList());
    }
}
