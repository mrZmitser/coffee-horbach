package com.example.lab5_ultimate.command.tag;

import java.io.IOException;
import java.util.List;

import com.example.lab5_ultimate.entity.DrinkEntity;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

public class DrinksTag extends TagSupport {
    private List<DrinkEntity> drinks;
    private String role;
    private String editForm = " <form method=\"post\" action=\"coffee-servlet\">\n" +
            "                    <input name=\"action\" value=\"editPrice\" style=\"visibility: hidden\"/>\n" +
            "                    <input name=\"id\" value=\"%d\" style=\"visibility: hidden\"/>\n" +
            "                    <input name=\"new_price\"/>\n" +
            "                    <button type=\"submit\" class=\"small-button\">OK</button>\n" +
            "                </form>";


    public void setDrinks(List<DrinkEntity> drinks) {
        this.drinks = drinks;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int doStartTag() throws JspTagException {
        try {
            pageContext.getOut().write(
                    "<TABLE BORDER=\"1\" width=\"50%\">"
            );
            for (DrinkEntity drink : drinks) {
                pageContext.getOut().write("<TR><TD>");
                pageContext.getOut().write(drink.getName());
                pageContext.getOut().write("</TD><TD>");
                pageContext.getOut().write("<p id=\"price_" + drink.getId() + "\">" + drink.getCost() + "</p>");
                if (role.equals("admin")) {
                    pageContext.getOut().write("</TD><TD>");
                    pageContext.getOut().write(String.format(editForm, drink.getId()));
                }
                pageContext.getOut().write("</TD></TR>");
            }
            pageContext.getOut().write("</TABLE>");
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return SKIP_BODY;
    }

}
