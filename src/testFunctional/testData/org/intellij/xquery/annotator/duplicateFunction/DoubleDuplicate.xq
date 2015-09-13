<error descr="'test#2' is already defined in 'DoubleDuplicate.xq'">declare function local:test($a, $b)</error> {
    "anyString"
};

<error descr="'test#2' is already defined in 'DoubleDuplicate.xq'">declare function local:test($c, $d)</error> {
    "anyString"
};

<error descr="'test' is already defined in 'DoubleDuplicate.xq'">declare function local:test()</error> {
    "anyString"
};

<error descr="'test' is already defined in 'DoubleDuplicate.xq'">declare function local:test()</error> {
    "anyString"
};

()
