package com.example.epamcourse.controller.tag;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class CustomTag extends TagSupport {
    private static final String COMPANY_NAME = "Programmer's world";


    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.print(COMPANY_NAME);
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }
}