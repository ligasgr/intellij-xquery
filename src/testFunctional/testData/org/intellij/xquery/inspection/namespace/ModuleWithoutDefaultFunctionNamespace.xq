module namespace moduleWithoudDefaultNamespace = "my://sample/namespace/moduleWithoudDefaultNamespace";


declare function inDefaultNamespace() {
    fn:true()
};

declare function moduleWithoudDefaultNamespace:someFunction() {
  fn:true() and inDefaultNamespace()
};
