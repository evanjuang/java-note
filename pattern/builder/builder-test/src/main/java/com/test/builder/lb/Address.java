package com.test.builder.lb;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Address {
    private final String streetAddress;
    private final String city;
    private final String state;
}
