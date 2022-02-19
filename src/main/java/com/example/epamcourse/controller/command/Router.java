package com.example.epamcourse.controller.command;

/**
 * class Router
 *
 * @author M.Shubelko
 */
public class Router {
    private RouterType type = RouterType.FORWARD;
    private String page;

    /**
     * The enum RouterType
     */
    public enum RouterType {
        REDIRECT,
        FORWARD

    }

    /**
     * Instantiates a new router.
     *
     * @param page the page
     */
    public Router (String page) {
        this.page = page;
    }

    /**
     * Gets the router type
     *
     * @return the type
     */
    public RouterType getType() {
        return type;
    }

    /**
     * Sets the router type
     */
    public void setType(RouterType type) {
        this.type = type;
    }

    /**
     * Gets the page path
     *
     * @return the page path
     */
    public String getPage() {
        return page;
    }

    /**
     * Sets the page path
     */
    public void setPage(String page) {
        this.page = page;
    }
}