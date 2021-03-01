package com.github.kayjamlang.exampleLib;

import com.github.kayjamlang.core.Type;
import com.github.kayjamlang.core.containers.Function;
import com.github.kayjamlang.executor.libs.Library;

public class ExampleLib extends Library {
    public ExampleLib() throws Exception {
        // Main constructor
        System.out.println("ExampleLib loading!");

        // Simple global function "foo"
        functions.add(new LibFunction("foo", ((mainContext, context) -> {
            System.out.println(context.variables.get("str"));
            return "Test String";
        }), new Function.Argument(Type.STRING, "str")));

        // Simple class "clss"
        classes.put("clss", new LibClass("clss", clazz -> {
            // Simple companion
            clazz.setCompanion(new LibObject(object -> {
                // Simple static function "Hi"
                object.addFunction(new LibFunction("Hi", (mainContext, context) -> {
                    System.out.println("Hi!");
                    return null;
                }));
            }));
            // Simple no static variable "i"
            clazz.addVariable("i", 0);
            // Simple no static function "foo"
            clazz.addFunction(new LibFunction("foo", (mainContext, context) -> {
                System.out.println("i = " + context.parentContext.variables.get("i"));
                return null;
            }));
        }));
    }
}