package com.poo.parking_api.domain.vacancy;

public enum VacancyType {
    CARRO(10.0f),
    MOTO(5.0f),
    BICICLETA(2.0f),
    CAMINHÃO(15.0f);

    private final float priceInHour;

    VacancyType(float priceInHour) {
        this.priceInHour = priceInHour;
    }

    public float getPriceInHour() {
        return this.priceInHour;
    }
}
