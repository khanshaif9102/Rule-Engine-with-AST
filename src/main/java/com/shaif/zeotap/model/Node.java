package com.shaif.zeotap.model;


import jakarta.persistence.*;

@Entity
public class Node {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;  // operator or operand
    private String value; // value for operand nodes

    @ManyToOne(cascade = CascadeType.ALL)
    private Node left;

    @ManyToOne(cascade = CascadeType.ALL)
    private Node right;

    // Manually define getter and setter for 'type'
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Manually define getter and setter for 'value'
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
