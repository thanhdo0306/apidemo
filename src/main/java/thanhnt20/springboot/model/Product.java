package thanhnt20.springboot.model;

import jdk.jfr.Enabled;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Double price;
    private int year;

    public Product(String name, Double price, int year) {
        this.name = name;
        this.price = price;
        this.year = year;
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public int getYear() {
        return year;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Product [id=" + id +
                ", name=" + name +
                ", price=" + price +
                ", year=" + year;
    }
}
