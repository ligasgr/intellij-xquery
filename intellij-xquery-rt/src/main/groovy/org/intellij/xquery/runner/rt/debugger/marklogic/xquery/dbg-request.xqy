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

declare variable $mode external;
declare variable $root external;
declare variable $argument external;
declare variable $variables external;
(: ToDo: handle options :)

declare function local:gen-vars() as item()* {
    for $var in xdmp:unquote ($variables)/variables/variable
    let $qname := fn:QName ($var/@ns/fn:data(), $var/@name)
    return ($qname, $var/fn:data())
};

declare variable $vars := local:gen-vars();

let $id :=
    switch ($mode)
    case 'adhoc' return dbg:eval ($argument, $vars)
    case 'invoke' return dbg:invoke ($argument, $vars)
    default return fn:error (xs:QName ("dbg:bad-run-mode"), "Unexpected MarkLogic Debugger Run Mode: " || $mode)

let $status := dbg:status ($id)
(:let $_ := xdmp:log ($status):)
return
    if ($status/d:request/error:error/error:format-string/fn:string())
    then fn:error (xs:QName ($status/d:request/error:error/error:name/fn:string()), $status/d:request/error:error/error:format-string/fn:string())
    else $id


