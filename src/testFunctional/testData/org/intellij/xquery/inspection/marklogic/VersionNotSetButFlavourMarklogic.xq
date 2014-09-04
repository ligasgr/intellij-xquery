declare private variable $var := 'var';
try {
    $var/namespace::*
} catch ($e) {
    ''
}