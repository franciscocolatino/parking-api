package com.poo.parking_api.domain.user;

public record RequestUser(String name, String email, String password, Integer role) {
}
