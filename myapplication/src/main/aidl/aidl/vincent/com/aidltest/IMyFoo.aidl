// Aldl2.aidl
package aidl.vincent.com.aidltest;

// Declare any non-default types here with import statements

interface IMyFoo {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    byte[] foo(in byte[] bs);
    String getThreadInfo();
}
