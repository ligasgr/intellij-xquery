import module namespace local = "http://module.namespace" at "FunctionReferencedFile.xq";

declare function local:accessible($xxx) {
    $xxx
};

local:acc<caret>essible("yeah")