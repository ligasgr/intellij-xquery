module namespace LibraryModuleUsingPredeclaredNamespace = "LibraryModuleUsingPredeclaredNamespace";

declare function LibraryModuleUsingPredeclaredNamespace:fun() {
    (: This usage should not be found as it is in a different file :)
    fn:concat('as', 'ds')
};