module namespace moduleNamespace = "my://sample/namespace/ModuleNamespaceDifferentThanDefaultFunctionNamespace";

declare default function namespace <warning descr="Default function namespace should be same as module namespace">"my://sample/other/namespace/someOtherNamespace"</warning>;


declare function someFunction() {
  fn:true()
};
