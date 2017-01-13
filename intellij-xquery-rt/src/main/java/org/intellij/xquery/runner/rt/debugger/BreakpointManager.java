/*
 * Copyright 2013-2017 Grzegorz Ligas <ligasgr@gmail.com> and other contributors
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
 */

package org.intellij.xquery.runner.rt.debugger;

import com.codnos.dbgp.api.Breakpoint;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static com.codnos.dbgp.api.Breakpoint.aCopyOf;
import static org.intellij.xquery.runner.rt.debugger.LogUtil.log;

public class BreakpointManager {
    private final Map<Integer, Map<String, Breakpoint>> breakpoints = new HashMap<>();
    private final Map<String, Breakpoint> breakpointIdToBreakpoints = new HashMap<>();

    public Breakpoint getBreakpoint(String uri, int lineNumber) {
        final Map<String, Breakpoint> s = breakpoints.get(lineNumber);
        if (s != null) {
            return s.get(normalizeUri(uri));
        }
        return null;
    }

    public Breakpoint getBreakpoint(String breakpointId) {
        return breakpointIdToBreakpoints.get(breakpointId);
    }

    public Optional<Breakpoint> removeBreakpoint(String breakpointId) {
        Breakpoint breakpointToRemove = breakpointIdToBreakpoints.remove(breakpointId);
        if (breakpointToRemove != null) {
            Integer line = breakpointToRemove.getLineNumber().get();
            String uri = breakpointToRemove.getFileURL().get();
            final Map<String, Breakpoint> s = breakpoints.get(line);
            if (s != null) {
                s.remove(normalizeUri(uri));
            }
            return Optional.of(breakpointToRemove);
        } else {
            return Optional.empty();
        }
    }

    public Breakpoint setBreakpoint(Breakpoint breakpoint) {
        String breakpointId = UUID.randomUUID().toString();
        return setBreakpoint(breakpoint, breakpointId);
    }

    public void updateBreakpoint(Breakpoint breakpoint) {
        setBreakpoint(breakpoint, breakpoint.getBreakpointId());
    }

    private Breakpoint setBreakpoint(Breakpoint breakpoint, String breakpointId) {
        String uri = normalizeUri(breakpoint.getFileURL().get());
        Integer line = breakpoint.getLineNumber().get();
        log("setting breakpoint " + uri + " " + line);
        final Map<String, Breakpoint> s = breakpoints.get(line);
        final Breakpoint breakpointThatWasSet = aCopyOf(breakpoint).withFileUri(uri).withBreakpointId(breakpointId).build();
        if (s == null) {
            final HashMap<String, Breakpoint> map = new HashMap<String, Breakpoint>();
            map.put(uri, breakpointThatWasSet);
            breakpoints.put(line, map);
        } else {
            s.put(uri, breakpointThatWasSet);
        }
        breakpointIdToBreakpoints.put(breakpointId, breakpointThatWasSet);
        return breakpointThatWasSet;
    }

    private static String normalizeUri(String uri) {
        try {
            try {
                uri = uri.replaceAll(" ", "%20");
                return new File(new URI(uri)).toURI().toASCIIString();
            } catch (IllegalArgumentException e) {
                return new URI(uri).normalize().toASCIIString();
            }
        } catch (URISyntaxException e) {
            log("Failed to parse <" + uri + ">: " + e);
            return uri;
        }
    }
}
