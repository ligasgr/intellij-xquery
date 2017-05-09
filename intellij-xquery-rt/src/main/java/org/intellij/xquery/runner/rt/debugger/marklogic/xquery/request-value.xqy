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
: Time: 3:22 PM
:)

xquery version '1.0-ml';

declare variable $id external;
declare variable $expr external;

declare function local:desc-type ($val)
{
	typeswitch ($val)
	case empty-sequence() return "empty-sequence()"
	case text() return "text()"
	case attribute() return "attribute()"
	case comment() return "comment()"
	case processing-instruction() return "processing-instruction()"
	case document-node() return fn:concat ("document-node() { ", local:desc-type ($val/element()), " }")
	case element() return fn:concat ("element()/", xdmp:element-content-type ($val))
	case xs:anyAtomicType return fn:concat ("xs:", xdmp:type ($val))
	case item()+ return "sequence"
	default return "unrecognized type"
};

let $value := dbg:value ($id, $expr)
return (xdmp:describe ($value), local:desc-type ($value))
