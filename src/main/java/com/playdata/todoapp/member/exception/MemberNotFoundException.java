package com.playdata.todoapp.member.exception;

public class MemberNotFoundException extends NullPointerException{

    public MemberNotFoundException(String s) {
        super(s);
    }

    public MemberNotFoundException() {
        super("MEMBERS_NOT_FOUND");
    }
}
