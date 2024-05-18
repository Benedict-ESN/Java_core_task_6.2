package ru.netology;
import java.util.Objects;

public class Address {
    private String country;
    private String city;

    public Address(String country, String city) {
        this.country = country;
        this.city = city;
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city);

    }

    @Override
    public boolean equals(Object obj) {
        Address o = (Address) obj;
        return this.country.equals(o.country) && this.city.equals(o.city);
    }

}
