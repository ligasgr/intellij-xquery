declare <error descr="MarkLogic extended syntax used when version is not set to 'MarkLogic extended'.">private</error> variable $var := 'var';
try {
    $var/<error descr="MarkLogic extended syntax used when version is not set to 'MarkLogic extended'.">namespace::*</error>, <error descr="MarkLogic extended syntax used when version is not set to 'MarkLogic extended'.">binary {$var/<error descr="MarkLogic extended syntax used when version is not set to 'MarkLogic extended'.">binary()</error>}</error>,
    validate <error descr="MarkLogic extended syntax used when version is not set to 'MarkLogic extended'.">as xs:boolean</error> {fn:false()}
} catch <error descr="MarkLogic extended syntax used when version is not set to 'MarkLogic extended'.">($e)</error> <error descr="MarkLogic extended syntax used when version is not set to 'MarkLogic extended'.">{

}</error>