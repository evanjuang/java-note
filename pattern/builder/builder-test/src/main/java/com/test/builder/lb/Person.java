package com.test.builder.lb;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Person {
    private final String name;
    private final Address address;
}
