package com.github.kayjamlang.exampleLib;

import com.github.kayjamlang.core.Argument;
import com.github.kayjamlang.core.Type;
import com.github.kayjamlang.executor.libs.Library;

import java.util.HashMap;

public class ExampleLib extends Library {
    public ExampleLib() throws Exception {
        // Main constructor
        System.out.println("ExampleLib loading!");

        // Simple global function "foo"
        functions.add(new LibFunction("foo", ((mainContext, context) -> {
            System.out.println(context.variables.get("str"));
            return "Test String";
        }), new Argument(Type.STRING, "str")));

        // Simple class "simple"
        classes.put("simple", new LibClass("simple", clazz -> {

            // Simple container
            // Creates a value, preferably for non-primitive types

            // context.parentContext - this class
            // Don't use clazz variable
            clazz.addConstructor(new LibConstructor((mainContext, context) ->
                    context.parentContext.variables.put("o", new HashMap<>())));

            // Simple companion
            clazz.setCompanion(new LibObject(object -> {
                // Simple static function "Hi"
                object.addFunction(new LibFunction("Hi", (mainContext, context) -> {
                    System.out.println("Hi!");
                    return null;
                }));
            }));

            // Simple no static variable "i"
            // Use this for primitive types only!
            clazz.addVariable("i", 0);

            // Simple no static function "foo"
            clazz.addFunction(new LibFunction("foo", (mainContext, context) -> {
                System.out.println("i = " + context.parentContext.variables.get("i"));
                return null;
            }));

            // Simple no static function "fooThis" returns this class
            clazz.addFunction(new LibFunction("foo", (mainContext, context) -> {
                System.out.println("i = " + context.parentContext.variables.get("i"));
                return context.parentContext;
            }));
        }));
    }
}