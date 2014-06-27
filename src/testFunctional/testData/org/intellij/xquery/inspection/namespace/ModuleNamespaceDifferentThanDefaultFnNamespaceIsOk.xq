module namespace moduleNamespace = "my://sample/namespace/moduleNamespace";

declare default function namespace "http://www.w3.org/2005/xpath-functions";

declare function moduleNamespace:someFunction() {
  true()
};
