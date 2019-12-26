package ru.vkr.service.rest;

public @interface WithoutAuth {
    boolean offAuth() default false;
}
