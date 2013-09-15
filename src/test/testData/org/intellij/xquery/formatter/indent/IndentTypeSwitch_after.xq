typeswitch ($customer/billing-address)
    case $a as element(*, USAddress)
        return $a/state
    default
        return "unknown"