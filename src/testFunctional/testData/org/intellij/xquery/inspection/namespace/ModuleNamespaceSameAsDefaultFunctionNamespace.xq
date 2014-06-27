module namespace moduleNamespaceSameAsDefault = "my://sample/namespace/moduleNamespaceSameAsDefault";

declare default function namespace "my://sample/namespace/moduleNamespaceSameAsDefault";

declare function someFunction() {
  fn:true()
};
