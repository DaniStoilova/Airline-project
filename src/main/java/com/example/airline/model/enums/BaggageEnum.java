package com.example.airline.model.enums;

public enum BaggageEnum {

    Small("40 x 30 x 20 cm*- for free"),
    Cabin("10kg"),
    CheckedIn("20KG");



    private final String baggage;

    BaggageEnum(String baggage) {
        this.baggage = baggage;
    }
}
