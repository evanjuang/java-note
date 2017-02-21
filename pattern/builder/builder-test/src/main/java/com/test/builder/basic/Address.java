package com.test.builder.basic;

public class Address {
    private final String streetAddress;
    private final String city;
    private final String state;

    private Address(String newStreetAddress, String newCity, String newState) {
        this.streetAddress = newStreetAddress;
        this.city = newCity;
        this.state = newState;
    }

    public String getStreetAddress() {
        return this.streetAddress;
    }

    public String getCity() {
        return this.city;
    }

    public String getState() {
        return this.state;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return this.streetAddress + ", " + this.city + ", " + this.state;
    }

    public static class Builder {
        private String nestedStreetAddress;
        private String nestedCity;
        private String nestedState;

        public Builder streetAddress(String newStreetAddress) {
            this.nestedStreetAddress = newStreetAddress;
            return this;
        }

        public Builder city(String newCity) {
            this.nestedCity = newCity;
            return this;
        }

        public Builder state(String newState) {
            this.nestedState = newState;
            return this;
        }

        public Address build() {
            return new Address(nestedStreetAddress, nestedCity, nestedState);
        }
    }
}
