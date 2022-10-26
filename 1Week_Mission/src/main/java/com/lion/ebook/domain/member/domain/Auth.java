package com.lion.ebook.domain.member.domain;

public enum Auth {
    GENERAL(1),
    WRITER(2),
    MANAGER(5);

    private final int authLevel;
    Auth(int authLevel) {
        this.authLevel = authLevel;
    }
    public int getAuthLevel() {
        return authLevel;
    }
}
