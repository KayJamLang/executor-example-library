package com.github.kayjamlang.exampleLib;

import com.github.kayjamlang.executor.Executor;

public class Main {
    // Simple lib test
    public static void main(String[] args) throws Exception {
        Executor executor = new Executor();

        // Load ExampleLib
        System.out.println("\n\nLib loading!");
        executor.addLibrary(new ExampleLib());

        // Global function "foo" test
        System.out.println("\n\nCall function \"foo\"!");
        System.out.println(executor.execute("{return foo(\"Hello string!\")}"));

        // Class "clss" test
        System.out.println("\n\nTest class \"class\"!");
        // Companion test
        executor.execute("{clss::Hi()}");
        // Normal class test
        executor.execute(
                "{var obj = clss();" +
                        "obj.i = 12;" +
                        "obj.foo()}"
        );
    }
}
