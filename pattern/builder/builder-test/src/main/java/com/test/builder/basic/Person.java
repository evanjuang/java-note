package com.test.builder.basic;

public class Person {
    private final String name;
    private final Address address;

    private Person(String newName, Address newAddress) {
        this.name = newName;
        this.address = newAddress;
    }

    public String getName() {
        return this.name;
    }

    public Address getAddress() {
        return this.address;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return this.name + ", " + this.address;
    }

    public static class Builder {
        private String nestedName;
        private Address nestedAddress;

        public Builder name(String newName) {
            this.nestedName = newName;
            return this;
        }

        public Builder address(Address newAddress) {
            this.nestedAddress = newAddress;
            return this;
        }

        public Person build() {
            return new Person(nestedName, nestedAddress);
        }
    }
}
