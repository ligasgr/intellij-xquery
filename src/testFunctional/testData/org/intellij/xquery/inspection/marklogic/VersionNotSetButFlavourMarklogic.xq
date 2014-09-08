declare private variable $var := 'var';
try {
    $var/namespace::*, binary {$var/binary()},
    validate as xs:boolean {fn:false()}
} catch ($e) {

}