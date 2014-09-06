xdmp:hmac-sha1(binary { xs:hexBinary(xs:base64Binary($secret)) } , $message,"base64")
