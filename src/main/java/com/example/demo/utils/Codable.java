package com.example.demo.utils;

import java.util.Objects;

public interface Codable<S> {
    S getCode();

    /**
     * Выполняет поиск значения в перечислении по коду.
     *
     * @param codableSet класс - перечисление
     * @param code       код
     * @param <T>        перечисления
     * @return найденное значение перечилсения
     * @throws IllegalArgumentException если значение с данным кодом не найдено
     */
    static <T extends Enum<T> & Codable<S>, S> T find(Class<T> codableSet, S code) {
        for (T codable : codableSet.getEnumConstants()) {
            if (Objects.equals(codable.getCode(), code)) {
                return codable;
            }
        }
        throw new IllegalArgumentException(
                String.format("%s type not found for code = %s", codableSet.getName(), code));
    }

}
