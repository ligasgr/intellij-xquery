module namespace <warning descr="Namespace prefix should be derived from file name part in URI">someOtherNamespace</warning> = "my://sample/namespace/ModuleNamespaceDifferentThanFilename.xq";


declare function someOtherNamespace:someFunction() {
  fn:true()
};
