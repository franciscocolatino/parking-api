package com.poo.parking_api.domain.user;

public record RequestUser(String id, String name, String email, String password, UserRole role) {
}
