module namespace this = "foo-bar";

import module namespace x = "ns-x";

import <fold text='...'>module namespace y = "ns-y";
import module namespace z = "ns-z";</fold>

declare function aaa($ab, $cd) <fold text='{...}'>{

}</fold>
