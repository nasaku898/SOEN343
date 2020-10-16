package com.soen343.shs.dal.service.exceptions.user;

public class UserIdDoesntExist extends RuntimeException {
    private static final long serialVersionUID = 1092408746088563215L;

    public UserIdDoesntExist(final String msg) {
        super(msg);
    }
}
