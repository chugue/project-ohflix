package com.project.ohflix._core.utils;


import java.beans.PropertyEditorSupport;

public class EnumEditor<T extends Enum<T>> extends PropertyEditorSupport {
    private final Class<T> type;

    public EnumEditor(Class<T> type) {
        this.type = type;
    }


    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        System.out.println("Converting text to enum: " + text); // 디버깅 메시지 추가
        T value = Enum.valueOf(type, text.toUpperCase().replace("-", "_"));
        setValue(value);
    }
}
