xquery version '1.0-ml';
declare private variable $var := 'var';
try {
    $var/namespace::*
} catch ($e) {
    ''
}