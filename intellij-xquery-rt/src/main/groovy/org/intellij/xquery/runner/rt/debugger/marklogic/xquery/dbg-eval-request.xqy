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

declare namespace d='http://marklogic.com/xdmp/debug';

declare variable $__query__ external;
declare variable $__variables__ external;

declare function local:gen-vars() as item()* {
    for $var in xdmp:unquote ($__variables__)/variables/variable
    let $qname := fn:QName ($var/@ns/fn:data(), $var/@name)
    return ($qname, $var/fn:data())
};

declare variable $vars := local:gen-vars();

let $id := dbg:eval ($__query__, $vars)
let $status := dbg:status ($id)
let $_ := xdmp:log ($status)
return
    if ($status/d:request/error:error/error:format-string/fn:string())
    then fn:error (xs:QName ($status/d:request/error:error/error:name/fn:string()), $status/d:request/error:error/error:format-string/fn:string())
    else $id


