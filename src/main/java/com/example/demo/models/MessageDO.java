package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
/**
 * Websocket信息格式
 * **/
@Data
@AllArgsConstructor
public class MessageDO<T> {
    private String type;
    private T data;
}
