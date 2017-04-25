(: Copyright 2017 OverStory Ltd <copyright@overstory.co.uk> and other contributors
* (see the CONTRIBUTORS file).
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
:)

(:~
: User: ron
: Date: 4/23/17
: Time: 3:09 PM
:)

xquery version '1.0-ml';
declare variable $__query__ external;
declare variable $__variables__ external;

declare function local:value ($var as element()) as xs:untypedAtomic
{
    switch ($var/type)
    case 'xs:integer' return xs:integer ($var/data)
    case 'xs:int' return xs:int ($var/data)
    case 'xs:unsignedInt' return xs:unsignedInt ($var/data)
    case 'xs:unsignedLong' return xs:unsignedLong ($var/data)
    case 'xs:unsignedShort' return xs:unsignedShort ($var/data)
    case 'xs:decimal' return xs:decimal ($var/data)
    default return $var/fn:string()
};

declare function local:gen-vars() as item()* {
    for $var in xdmp:unquote ($__variables__)/variable
    let $qname := fn:QName ($var/@ns/fn:data(), $var/@name)
    return ($qname, local:value ($var))
};

dbg:eval ($__query__, local:gen-vars())
