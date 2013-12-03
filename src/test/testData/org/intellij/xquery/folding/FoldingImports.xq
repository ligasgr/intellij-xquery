module namespace this = "foo-bar";

import <fold text='...'>module namespace a = "ns-a";
import module namespace b = "ns-b";</fold>
declare default function namespace "ns-fun";
import <fold text='...'>module namespace c = "ns-c";
import module namespace d = "ns-d";</fold>
