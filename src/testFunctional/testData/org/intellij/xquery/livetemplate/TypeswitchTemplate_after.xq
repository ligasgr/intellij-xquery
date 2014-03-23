xquery version "3.0";

typeswitch ('value')
    case xs:string return 'xs:string'
    default return 'unknown'