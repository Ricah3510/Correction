package com.example.correction.dto;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class Other {
    public static boolean eval(double diff, String operateur, double seuil) {

        ExpressionParser parser = new SpelExpressionParser();

        String expression = diff + " " + operateur + " " + seuil;

        Expression exp = parser.parseExpression(expression);

        return exp.getValue(Boolean.class);
    }
}
