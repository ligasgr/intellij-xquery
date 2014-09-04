declare <error descr="MarkLogic extended syntax used when version is not set to 'MarkLogic extended'.">private</error> variable $var := 'var';
try {
    $var/<error descr="MarkLogic extended syntax used when version is not set to 'MarkLogic extended'.">namespace::*</error>
} catch <error descr="MarkLogic extended syntax used when version is not set to 'MarkLogic extended'.">($e)</error> {
    ''
}